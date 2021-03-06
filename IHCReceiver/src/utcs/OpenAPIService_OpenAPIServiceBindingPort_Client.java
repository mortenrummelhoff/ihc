
package utcs;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.2
 * 2013-02-10T20:24:23.933+01:00
 * Generated source version: 2.7.2
 * 
 */
public final class OpenAPIService_OpenAPIServiceBindingPort_Client {

    private static final QName SERVICE_NAME = new QName("utcs", "OpenAPIServiceService");

    private OpenAPIService_OpenAPIServiceBindingPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = OpenAPIServiceService.WSDL_LOCATION;
    	//URL wsdlURL = new URL("http://192.168.0.55/OpenAPIService/");
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        OpenAPIServiceService ss = new OpenAPIServiceService(wsdlURL, SERVICE_NAME);
        OpenAPIService port = ss.getOpenAPIServiceBindingPort();  
        
        {
        System.out.println("Invoking enableSubscription...");
        org.w3._2001.xmlschema.ArrayOfint _enableSubscription_parameter2 = null;
        port.enableSubscription(_enableSubscription_parameter2);


        }
        {
        System.out.println("Invoking getProjectInfo...");
        utcs.WSProjectInfo _getProjectInfo__return = port.getProjectInfo();
        System.out.println("getProjectInfo.result=" + _getProjectInfo__return);


        }
        {
        System.out.println("Invoking ping...");
        port.ping();


        }
        {
        System.out.println("Invoking getUptime...");
        long _getUptime__return = port.getUptime();
        System.out.println("getUptime.result=" + _getUptime__return);


        }
        {
        System.out.println("Invoking getValues...");
        org.w3._2001.xmlschema.ArrayOfint _getValues_parameter4 = null;
        values.utcs.ArrayOfWSResourceValue _getValues__return = port.getValues(_getValues_parameter4);
        System.out.println("getValues.result=" + _getValues__return);


        }
        {
        System.out.println("Invoking getDatalineInputIDs...");
        utcs.ArrayOfWSDatalineResource _getDatalineInputIDs__return = port.getDatalineInputIDs();
        System.out.println("getDatalineInputIDs.result=" + _getDatalineInputIDs__return);


        }
        {
        System.out.println("Invoking getTime...");
        utcs.WSDate _getTime__return = port.getTime();
        System.out.println("getTime.result=" + _getTime__return);


        }
        {
        System.out.println("Invoking waitForEvents...");
        int _waitForEvents_parameter1 = 0;
        utcs.WSEventPackage _waitForEvents__return = port.waitForEvents(_waitForEvents_parameter1);
        System.out.println("waitForEvents.result=" + _waitForEvents__return);


        }
        {
        System.out.println("Invoking setValues...");
        utcs.ArrayOfWSResourceValueEvent _setValues_parameter7 = null;
        boolean _setValues__return = port.setValues(_setValues_parameter7);
        System.out.println("setValues.result=" + _setValues__return);


        }
        {
        System.out.println("Invoking authenticate...");
        java.lang.String _authenticate_parameter5 = "";
        java.lang.String _authenticate_parameter6 = "";
        boolean _authenticate__return = port.authenticate(_authenticate_parameter5, _authenticate_parameter6);
        System.out.println("authenticate.result=" + _authenticate__return);


        }
        {
        System.out.println("Invoking getFWVersion...");
        utcs.WSVersionInfo _getFWVersion__return = port.getFWVersion();
        System.out.println("getFWVersion.result=" + _getFWVersion__return);


        }
        {
        System.out.println("Invoking getDatalineOutputIDs...");
        utcs.ArrayOfWSDatalineResource _getDatalineOutputIDs__return = port.getDatalineOutputIDs();
        System.out.println("getDatalineOutputIDs.result=" + _getDatalineOutputIDs__return);


        }
        {
        System.out.println("Invoking getAPIVersion...");
        int _getAPIVersion__return = port.getAPIVersion();
        System.out.println("getAPIVersion.result=" + _getAPIVersion__return);


        }
        {
        System.out.println("Invoking disableSubscription...");
        org.w3._2001.xmlschema.ArrayOfint _disableSubscription_parameter3 = null;
        port.disableSubscription(_disableSubscription_parameter3);


        }

        System.exit(0);
    }

}
