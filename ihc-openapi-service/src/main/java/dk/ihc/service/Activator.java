package dk.ihc.service;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import dk.ihc.openapi.OpenAPIService;
import dk.ihc.openapi.OpenAPIServiceService;

public class Activator implements BundleActivator, ManagedService {

	private List<ServiceRegistration> aRegistrationsList = new ArrayList<ServiceRegistration>();
	
	private static String IHC_IP_WS_PROPERTY = "dk.ihc.openapi.ws";
	
	private Dictionary aProperties;
	
	private Logger aLog = Logger.getLogger(getClass());
	
	@Override
	public void start(BundleContext context) throws Exception {
		if (aLog.isDebugEnabled()) {
			aLog.debug("Starting IHC OpenAPI Service");
		}
		
		OpenAPIServiceService serviceService = new OpenAPIServiceService();
		OpenAPIService service = serviceService.getOpenAPIServiceBindingPort();
		
		BindingProvider bp = (BindingProvider)service;
		
		//set IHC ip
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://192.168.0.55/ws/OpenAPIService");
		//enable SESSION Cookies
		bp.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);

		//set timeout to 3 min
		bp.getRequestContext().put("com.sun.xml.ws.request.timeout", 3 * 60 * 1000);
		
		Client client = ClientProxy.getClient(service);
		
		
		HTTPConduit conduit = (HTTPConduit) client.getConduit();

		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setAllowChunking(false);
		
		conduit.setClient(httpClientPolicy);

		
		//Register configurator
		Hashtable<String, Object> props = new Hashtable<String, Object>();
		props.put(Constants.SERVICE_PID, "dk.ihc.openapi");
		aRegistrationsList.add(context.registerService(ManagedService.class.getName(), this , props));
		
		//Register OpenAPI Service
		props = new Hashtable<String, Object>();
		props.put("description", "This is The IHC OpenAPI Service");
		aRegistrationsList.add(context.registerService(OpenAPIService.class.getName(), service, props));		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		for (ServiceRegistration sr : aRegistrationsList) {
			context.ungetService(sr.getReference());	
		}
		
	}

	@Override
	public void updated(Dictionary properties) throws ConfigurationException {
		if (aLog.isDebugEnabled()) {
			aLog.debug("updated called with properties: " + properties);
		}
		
		aProperties = properties;
		System.err.println("Updated: " + properties);
	}
	
	

}
