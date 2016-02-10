package dk.mhr.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import dk.ihc.openapi.ArrayOfWSResourceValue;
import dk.ihc.openapi.ArrayOfWSResourceValueEvent;
import dk.ihc.openapi.ArrayOfint;
import dk.ihc.openapi.ObjectFactory;
import dk.ihc.openapi.OpenAPIService;
import dk.ihc.openapi.WSBooleanValue;
import dk.ihc.openapi.WSResourceValue;
import dk.ihc.openapi.WSResourceValueEvent;
import dk.ihc.receiver.service.ReceiverService;
import dk.mhr.pushhandler.service.PushInterface;

public class MhrServlet extends HttpServlet {
	private Logger aLog = Logger.getLogger(getClass());

	private ObjectFactory aOF = new ObjectFactory();

	private final static int KITCHEN_PUSH_UP_LEFT = 404060;
	private final static int KITCHEN_PUSH_UP_RIGHT = 404316;

	private final static int KITCHEN_DIMMER = 521490;

	private final static int ON_BUTTON_RESOUCE = 404572;
	private final static int OFF_BUTTON_RESOUCE = 404828;

	private final static int TEL_SWITCH_OUT = 20062;
	private final static int TEL_BUTTON_OFF = 755473;
	private final static int TEL_BUTTON_ON = 777489;


	JSONParser parser = new JSONParser();

	

	public MhrServlet() {

	
		
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (aLog.isDebugEnabled()) {
			aLog.debug("doGet Called, req: {}", req);
		}
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (aLog.isDebugEnabled()) {
			aLog.debug("doPost Called, req: {}", req);
		}

		process(req, resp);

	}


	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String requestURI = req.getRequestURI();
		aLog.debug("RequestURI: {}", requestURI);

		
		
		String[] uriTokens = requestURI.split("/");

		String serviceInterface = null; 
		String method = null;


		if (uriTokens != null && uriTokens.length > 2) {
			serviceInterface = uriTokens[1];
			method = uriTokens[2];
		}

		//read body JSON
		JSONObject reqJson = null;
		try {
			reqJson =JSONObject.class.cast(parser.parse(req.getReader()));
		} catch (ParseException e) {
			aLog.error(e);

			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

		aLog.debug("Stream: {}", reqJson);


		if ("register".equals(method)) {
			setRegId(reqJson.get("regId")+"");			
		}
		else if ("get_resource_value".equals(method)) {
			sendGetResourceValue(reqJson.get("resource")+"", resp);
		}
		else if ("set_tv_power".equals(method)) {
			boolean powerOn = Boolean.parseBoolean(reqJson.get("tvPower")+"");
			setTvPower(powerOn, resp);
		}
		else if ("set_resource_value".equals(method)) {
			String resource = reqJson.get("resource")+"";
			boolean set = Boolean.parseBoolean(reqJson.get("boolean")+"");
			sendSetResourceValue(resource, set, resp);
		}
		else if ("set_kitchen_light".equals(method)) {
			boolean	powerOn = Boolean.parseBoolean(reqJson.get("boolean")+"");
			setKitchenLight(powerOn, resp);
		}
		else if ("set_receiver_power".equals(method)) {
			boolean powerOn = Boolean.parseBoolean(reqJson.get("value")+"");
			ReceiverService service = getReceiverService();
			if (service != null) {
				service.setPower(powerOn);
			}
			else {
				aLog.warn("Unable to get ReceiverService..");
			}
		}
		else if ("get_receiver_value".equals(method)) {
			String resource = reqJson.get("resource")+"";
			ReceiverService service = getReceiverService();
			Map<String, Object> respMap = new HashMap<String, Object>();
			if (service != null) {
				respMap.put("Value", service.getValue(resource));
			}
			JSONObject.writeJSONString(respMap, resp.getWriter());
		}
		else if ("set_tuner_channel".equals(method)) {
			int channel = Integer.parseInt(reqJson.get("value")+"");
			ReceiverService service = getReceiverService();
			if (service != null) {
				service.setReceiverMode("tuner");
				service.setTunerChannel(channel);
			}
		}
		else if ("set_receiver_volumen".equals(method)) {
			int vol = Integer.parseInt(reqJson.get("value")+"");
			
			String response = "";
			
			ReceiverService service = getReceiverService();
			if (service != null) {
				service.setVolumen(vol);
			}
			
			resp.getWriter().write(JSONObject.toString("Value", response));
		}


	}



	private void setKitchenLight(boolean powerOn, HttpServletResponse rsp) {
		OpenAPIService ihcService = getIhcService();

		if (ihcService != null) {


			//set both buttons off
			ArrayOfWSResourceValueEvent rveList = aOF.createArrayOfWSResourceValueEvent();

			WSResourceValueEvent rveLeft = aOF.createWSResourceValueEvent();
			rveLeft.setMResourceID(KITCHEN_PUSH_UP_LEFT);

			WSResourceValueEvent rveRight = aOF.createWSResourceValueEvent();
			rveRight.setMResourceID(KITCHEN_PUSH_UP_RIGHT);

			WSBooleanValue bFalseValue = aOF.createWSBooleanValue();
			bFalseValue.setValue(false);
			rveLeft.setMValue(bFalseValue);
			rveRight.setMValue(bFalseValue);

			rveList.getArrayItem().add(rveLeft);
			rveList.getArrayItem().add(rveRight);

			Boolean result = ihcService.setValues(rveList);


			//set Poweron resource for 1 sec
			WSBooleanValue bTrueValue = aOF.createWSBooleanValue();
			bTrueValue.setValue(true);
			if (powerOn) {
				rveLeft.setMValue(bTrueValue);
			}
			else {
				rveRight.setMValue(bTrueValue);
			}

			ihcService.setValues(rveList);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Set both buttons to false again
			rveLeft.setMValue(bFalseValue);
			rveRight.setMValue(bFalseValue);

			ihcService.setValues(rveList);


			String value = "[RESULT]->[" + result + "]";
			aLog.debug("Value: {}", value);

			try {

				rsp.getWriter().write("Kitchen Light changed:" + value);
				rsp.flushBuffer();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private OpenAPIService getIhcService() {
		ServiceReference serviceReference = FrameworkUtil.getBundle(MhrServlet.class).
				getBundleContext().getServiceReference(OpenAPIService.class.getName());


		if (serviceReference != null) {
			OpenAPIService ihcService = (OpenAPIService) FrameworkUtil.getBundle(MhrServlet.class).
					getBundleContext().getService(serviceReference);

			return ihcService;
		}

		return null;
	}

	private void sendSetResourceValue(String resource, boolean set, HttpServletResponse rsp) {

		//check if resource is an integer
		try {
			Integer.parseInt(resource);
		} catch (NumberFormatException e) {
			//send some error response
			aLog.warn("Invalid resouce. Unable to setValue", e);
			return;
		}


		OpenAPIService ihcService = getIhcService();

		if (ihcService != null) {


			ArrayOfWSResourceValueEvent rveList = aOF.createArrayOfWSResourceValueEvent();

			WSResourceValueEvent rve = aOF.createWSResourceValueEvent();
			rve.setMResourceID(Integer.parseInt(resource));
			WSBooleanValue bValue = aOF.createWSBooleanValue();
			bValue.setValue(set);
			rve.setMValue(bValue);

			rveList.getArrayItem().add(rve);

			Boolean result = ihcService.setValues(rveList);

			aLog.debug("ihcService.setValue for resource: {} Returned: {} ", resource, result );

			String value = null;

			value = "[RESULT]->[" + result + "]";
			aLog.debug("Value: {}", value);

			try {

				rsp.getWriter().write("setResource: " + resource + "=" + value);
				rsp.flushBuffer();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void sendGetResourceValue(String resource, HttpServletResponse rsp) {

		Map<String, Object> respMap = new HashMap<String, Object>();
		
		//check if resource is an integer
		try {
			Integer.parseInt(resource);
		} catch (NumberFormatException e) {
			//send some error response
			aLog.warn("Invalid resouce. Unable to getValue", e);
			return;
		}

		OpenAPIService ihcService = getIhcService();

		if (ihcService != null) {

			ArrayOfint aoi = aOF.createArrayOfint();
			aoi.getArrayItem().add(Integer.parseInt(resource));

			ArrayOfWSResourceValue aoWSRV = ihcService.getValues(aoi);

			aLog.debug("ihcService.getValue for resource: {} Returned: {} ", resource, aoWSRV.getArrayItem() );

			String value = null;

			if (aoWSRV.getArrayItem() != null && aoWSRV.getArrayItem().size() > 0) {
				WSResourceValue wsRValue = aoWSRV.getArrayItem().get(0);

				if (wsRValue instanceof WSBooleanValue) {
					//value = "[BOOLEAN]->[" + WSBooleanValue.class.cast(wsRValue).isValue() +"]";
					//aLog.debug("Value: {}", value);
					
					respMap.put("resource", resource);
					respMap.put("value", WSBooleanValue.class.cast(wsRValue).isValue());
				}
			}


			try {

				JSONObject.writeJSONString(respMap, rsp.getWriter());
				//rsp.getWriter().write("Resource: " + resource + "=" + value);
				//rsp.flushBuffer();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setTvPower(boolean powerOn, HttpServletResponse rsp) {
		OpenAPIService ihcService = getIhcService();

		if (ihcService != null) {


			//set both buttons off
			ArrayOfWSResourceValueEvent rveList = aOF.createArrayOfWSResourceValueEvent();

			WSResourceValueEvent rveLeft = aOF.createWSResourceValueEvent();
			rveLeft.setMResourceID(ON_BUTTON_RESOUCE);

			WSResourceValueEvent rveRight = aOF.createWSResourceValueEvent();
			rveRight.setMResourceID(OFF_BUTTON_RESOUCE);

			WSBooleanValue bFalseValue = aOF.createWSBooleanValue();
			bFalseValue.setValue(false);
			rveLeft.setMValue(bFalseValue);
			rveRight.setMValue(bFalseValue);

			rveList.getArrayItem().add(rveLeft);
			rveList.getArrayItem().add(rveRight);

			Boolean result = ihcService.setValues(rveList);


			//set Poweron resource for 1 sec
			WSBooleanValue bTrueValue = aOF.createWSBooleanValue();
			bTrueValue.setValue(true);
			if (powerOn) {
				rveLeft.setMValue(bTrueValue);
			}
			else {
				rveRight.setMValue(bTrueValue);
			}

			ihcService.setValues(rveList);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Set both buttons to false again
			rveLeft.setMValue(bFalseValue);
			rveRight.setMValue(bFalseValue);

			ihcService.setValues(rveList);


			String value = "[RESULT]->[" + result + "]";
			aLog.debug("Value: {}", value);

			try {

				rsp.getWriter().write("Kitchen Light changed:" + value);
				rsp.flushBuffer();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private ReceiverService getReceiverService() {
		ServiceReference serviceReference = FrameworkUtil.getBundle(MhrServlet.class).
				getBundleContext().getServiceReference(ReceiverService.class.getName());

		aLog.debug("ServiceReference for ReceiverService: " + serviceReference);

		if (serviceReference != null) {
			ReceiverService service = (ReceiverService) FrameworkUtil.getBundle(MhrServlet.class).
					getBundleContext().getService(serviceReference);

			return service;
		}

		return null;
	}

	private void setRegId(String regId) {
		ServiceReference serviceReference = FrameworkUtil.getBundle(MhrServlet.class).
				getBundleContext().getServiceReference(PushInterface.class.getName());

		if (serviceReference != null) {
			PushInterface pushInterface = (PushInterface) FrameworkUtil.getBundle(MhrServlet.class).
					getBundleContext().getService(serviceReference);

			pushInterface.setRegId(regId);
		}
	}
}
