package dk.ihc.receiver;

import java.util.List;

import org.apache.log4j.Logger;

import dk.ihc.openapi.ArrayOfint;
import dk.ihc.openapi.ObjectFactory;
import dk.ihc.openapi.OpenAPIService;
import dk.ihc.openapi.WSBooleanValue;
import dk.ihc.openapi.WSEventPackage;
import dk.ihc.openapi.WSResourceValue;
import dk.ihc.openapi.WSResourceValueEvent;
import dk.mhr.pushhandler.service.PushInterface;



public class ReceiverMain {


	private Logger aLog = Logger.getLogger(getClass());
	private IHCListenerThread IHC_THREAD;	

	private TelnetManager aTelnetManager;

	private PushInterface aPushInterface;

	private long lastUpTime;
	
	private MyReceiverConnectionListener connectionListener = new MyReceiverConnectionListener();
	
	public ReceiverMain(OpenAPIService openAPIService, PushInterface pushInterface) {

		aPushInterface = pushInterface;

		if (aLog.isDebugEnabled()) {
			aLog.debug("OpenAPI Service version={}", openAPIService.getAPIVersion());
		}

		if (aLog.isDebugEnabled()) {
			aLog.debug("Starting ListenerThread for userevent");
		}

		//setup listener for user event on ihc wireless combi
		IHC_THREAD = new IHCListenerThread(openAPIService);
		IHC_THREAD.start();

	}

	public void setTelnetManager(TelnetManager manager) {
		aTelnetManager = manager;
	}
	
	public TelnetManager getTelnetManager() {
		return aTelnetManager;
	}
	
	public void stop() {
		if (IHC_THREAD != null) {
			IHC_THREAD.stopListenerThread();
		}

		if (aTelnetManager != null) {
			aTelnetManager.closeTelnetConnection();
		}
	}


	private class IHCListenerThread extends Thread {

		private OpenAPIService aService;
		private ObjectFactory aOF = new ObjectFactory();

		private boolean isRunning = true;

		private ArrayOfint subscriptionList;


		private final static int KITCHEN_PUSH_UP_LEFT = 404060;
		private final static int KITCHEN_PUSH_UP_RIGHT = 404316;

		private final static int ON_BUTTON_RESOUCE = 404572;
		private final static int OFF_BUTTON_RESOUCE = 404828;

		private final static int TEL_SWITCH_OUT = 20062;
		private final static int TEL_BUTTON_OFF = 755473;
		private final static int TEL_BUTTON_ON = 777489;


		private IHCListenerThread(OpenAPIService service) {
			super("IHCListenerThread");
			aService = service;

			aService.enableSubscription(getSubscriptionList());

			lastUpTime = aService.getUptime();
		}

		private ArrayOfint getSubscriptionList() {
			if (subscriptionList == null) {
				subscriptionList = aOF.createArrayOfint();

				//3. button ON
				subscriptionList.getArrayItem().add(ON_BUTTON_RESOUCE);
				//4. button OFF
				subscriptionList.getArrayItem().add(OFF_BUTTON_RESOUCE);

				subscriptionList.getArrayItem().add(KITCHEN_PUSH_UP_LEFT);
				subscriptionList.getArrayItem().add(KITCHEN_PUSH_UP_RIGHT);
				subscriptionList.getArrayItem().add(TEL_SWITCH_OUT);

				subscriptionList.getArrayItem().add(TEL_BUTTON_OFF);
				subscriptionList.getArrayItem().add(TEL_BUTTON_ON);

			}
			return subscriptionList;
		}

		private void stopListenerThread() {
			isRunning = false;
			interrupt();
		}

		public void run() {			
			while(isRunning) {

				try {

					long uptime = aService.getUptime();

					if (uptime < lastUpTime) {
						aLog.warn("IHC Controller has been restarted. Enable subcriptions");
						aService.enableSubscription(getSubscriptionList());
					}
					else {
						lastUpTime = uptime;
					}

					//aLog.debug("WaitForEvent called start. Uptime {}", (lastUpTime/(1000*60)));
					WSEventPackage wsEventPackage = aService.waitForEvents(10);

					int subscriptionAmount = wsEventPackage.getSubscriptionAmount();
					//aLog.debug("SubscriptionAmount: {}", subscriptionAmount);

					if (subscriptionAmount == 0) {
						aService.enableSubscription(getSubscriptionList());
						
						//something happen in ihc. properly some timeout exception. 
						//Just call waitForEvent one more time to avoid fake event changes.
						aService.waitForEvents(1);
						
						//done look at changes, as this call will have fake event due to the enableSubscription
						continue;
						
					}

					List<WSResourceValueEvent> wsResourceValueEventList = wsEventPackage.getResourceValueEvents().getArrayItem();

					if (wsResourceValueEventList != null && wsResourceValueEventList.size() > 0) {

						for (WSResourceValueEvent wsResourceValueEvent : wsResourceValueEventList) {

							if (wsResourceValueEvent == null) {
								continue;
							}

							int resourceId = wsResourceValueEvent.getMResourceID();
							WSResourceValue wsResourceValue = wsResourceValueEvent.getMValue();


							if (wsResourceValue instanceof WSBooleanValue) {

								WSBooleanValue boolValue = WSBooleanValue.class.cast(wsResourceValue);

								if (resourceId == ON_BUTTON_RESOUCE) {

									if (boolValue.isValue()) {
										if (aLog.isDebugEnabled()) {
											aLog.debug("Resource {} has been pushed. Establishing connection to Pioneer Receiver.", resourceId);
										}		
										
										aTelnetManager.establishConnection();
										aTelnetManager.addConnectionListener(connectionListener);
																				
									}

								}
								else if (resourceId == OFF_BUTTON_RESOUCE) {
									if (boolValue.isValue()) {
										aTelnetManager.setPower(false);
										aTelnetManager.removeConnectionListener(connectionListener);
									}
								}


								if (resourceId == TEL_SWITCH_OUT) {
									//aPushInterface.push("Television Powered " + wsResourceValue);
									aPushInterface.push("Television Powered " + (boolValue.isValue() ? "On" : "Off" ));
								}
								else { 
									if (boolValue.isValue()) {
										//send push about Power switch has been pushed	
										aPushInterface.push("Power Switch Resource: " +  resourceId +" pushed");
									}
								}

							}
							else {
								aLog.debug("ResourceValue not a boolean type, was: {}, id: {}", wsResourceValue, resourceId);
							}
						}
					}

				} catch (Throwable e) {
					aLog.warn("Something went wrong in ServiceAPI Continue anyway", e);
					//aPushInterface.push("Error occoured: " + e.getMessage());
				}
			}
			if (aLog.isDebugEnabled()) {
				aLog.debug("IHC listener thread whileloop stopped..");
			}
		}
	}

	private class MyReceiverConnectionListener implements ReceiverConnectionListener {

		@Override
		public void connected() {
			
			aTelnetManager.setPower(true);
			aTelnetManager.setTunerChannel(3);
			
		}
		
	}
	
}
