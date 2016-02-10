package dk.ihc.openapi;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.2
 * 2013-02-17T20:12:04.590+01:00
 * Generated source version: 2.7.2
 * 
 */
@WebServiceClient(name = "OpenAPIServiceService", 
                  wsdlLocation = "openapi.wsdl",
                  targetNamespace = "utcs") 
public class OpenAPIServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("utcs", "OpenAPIServiceService");
    public final static QName OpenAPIServiceBindingPort = new QName("utcs", "OpenAPIServiceBindingPort");
    static {
        URL url = OpenAPIServiceService.class.getResource("openapi.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(OpenAPIServiceService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "openapi.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public OpenAPIServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OpenAPIServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OpenAPIServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public OpenAPIServiceService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public OpenAPIServiceService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public OpenAPIServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns OpenAPIService
     */
    @WebEndpoint(name = "OpenAPIServiceBindingPort")
    public OpenAPIService getOpenAPIServiceBindingPort() {
        return super.getPort(OpenAPIServiceBindingPort, OpenAPIService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OpenAPIService
     */
    @WebEndpoint(name = "OpenAPIServiceBindingPort")
    public OpenAPIService getOpenAPIServiceBindingPort(WebServiceFeature... features) {
        return super.getPort(OpenAPIServiceBindingPort, OpenAPIService.class, features);
    }

}
