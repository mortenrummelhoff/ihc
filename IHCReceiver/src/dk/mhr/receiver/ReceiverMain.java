package dk.mhr.receiver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceFeature;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.Cookie;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import org.w3._2001.xmlschema.ArrayOfint;

import com.sun.xml.internal.ws.api.WebServiceFeatureFactory;

import utcs.ArrayOfWSDatalineResource;
import utcs.ObjectFactory;
import utcs.OpenAPIService;
import utcs.OpenAPIServiceService;
import utcs.WSDatalineResource;
import utcs.WSEventPackage;
import utcs.WSResourceValueEvent;
import values.utcs.ArrayOfWSResourceValue;
import values.utcs.WSBooleanValue;
import values.utcs.WSResourceValue;

public class ReceiverMain {


	private OpenAPIService aService;

	private final static int ON_BUTTON_RESOUCE = 404572;
	private final static int OFF_BUTTON_RESOUCE = 404828;

	private final static String IHC_IP_WS = "http://192.168.0.55/ws/OpenAPIService";

	private TelnetManager telnetManager;
	
	public ReceiverMain() throws MalformedURLException {

		System.out.println("Starting ReceiverMain");

		telnetManager = new TelnetManager();
		

		OpenAPIServiceService serviceService = new OpenAPIServiceService();

		aService = serviceService.getOpenAPIServiceBindingPort();

		System.out.println("Service: " + aService);

		BindingProvider bp = (BindingProvider)aService;

		//set IHC ip
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, IHC_IP_WS);
		//enable SESSION Cookies
		bp.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);

		Client client = ClientProxy.getClient(aService);
		HTTPConduit conduit = (HTTPConduit) client.getConduit();

		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

		//httpClientPolicy.setConnectionTimeout(60*1000);
		httpClientPolicy.setAllowChunking(false);
		//httpClientPolicy.setReceiveTimeout(30*1000);

		conduit.setClient(httpClientPolicy);

		System.out.println("Cookie before request: " + conduit.getCookies());
		System.out.println("Firmware: " + aService.getFWVersion());
		System.out.println("Cookie after request: " + conduit.getCookies());
				
		
		Map<String, Cookie> cookieMap = conduit.getCookies();
		//put cookie into request calls
		for (Cookie cookie : cookieMap.values()) {
			
			System.out.println("Setting cookie: " + cookie.getName() + "=" + cookie.getValue());
			httpClientPolicy.setCookie(cookie.getName() + "=" + cookie.getValue() );	
		}
		conduit.setClient(httpClientPolicy);
		
				

		new Thread(new Runnable() {

			private boolean isRunning = true;
			org.w3._2001.xmlschema.ObjectFactory w3OF = new org.w3._2001.xmlschema.ObjectFactory();

			private boolean onButtonValue, offButtonValue;

			@Override
			public void run() {

				ArrayOfint aoi = w3OF.createArrayOfint();

				//3. button ON
				aoi.getArrayItem().add(ON_BUTTON_RESOUCE);
				//4. button OFF
				aoi.getArrayItem().add(OFF_BUTTON_RESOUCE);
				
				
				aService.enableSubscription(aoi);

				while (isRunning) {

					System.out.println("WaitforEvents begin " + Calendar.getInstance().getTime());
					WSEventPackage wsEventPackage = aService.waitForEvents(15);
					System.out.println("WaitforEvents end " + Calendar.getInstance().getTime());			


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
								
								if (resourceId == ON_BUTTON_RESOUCE && boolValue.isValue()) {
									
									telnetManager.startTuner();
									
								}
								
								System.out.println("Resource: " + resourceId + " has value: " + 
										boolValue.isValue() );
							}
							
							
						}
					}

					//3.button
					//WSResourceValueEvent wsResourceValueEvent = wsResourceValueEventList.get(0);
					//wsResourceValueEvent.getMResourceID()





					//					//get value
					//					ArrayOfWSResourceValue arrayOfWSResourceValues = aService.getValues(aoi);
					//					List<WSResourceValue> resourceValues = arrayOfWSResourceValues.getArrayItem();
					//					
					//					//3. button value
					//					WSResourceValue wsResourceValue = resourceValues.get(0);
					//					if (wsResourceValue instanceof WSBooleanValue) {						
					//						WSBooleanValue boolValue = WSBooleanValue.class.cast(wsResourceValue);
					//						
					//						//if (onButtonValue != boolValue.isValue()) {
					//							
					//							onButtonValue = boolValue.isValue();
					//							System.out.println("Value changed for button 3: " + onButtonValue);	
					//						//}						
					//					}
					//					
					//					//4. button value
					//					wsResourceValue = resourceValues.get(1);
					//					if (wsResourceValue instanceof WSBooleanValue) {						
					//						WSBooleanValue boolValue = WSBooleanValue.class.cast(wsResourceValue);
					//						
					//						//if (offButtonValue != boolValue.isValue()) {
					//							
					//							offButtonValue = boolValue.isValue();
					//							System.out.println("Value changed for button 4: " + offButtonValue);	
					//						//}						
					//					}
					//											

				}




			}

		}).start();


	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			new ReceiverMain();
			//new RE
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
