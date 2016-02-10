package utcs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.2
 * 2013-02-10T20:24:23.992+01:00
 * Generated source version: 2.7.2
 * 
 */
@WebService(targetNamespace = "utcs", name = "OpenAPIService")
@XmlSeeAlso({ObjectFactory.class, values.utcs.ObjectFactory.class, org.w3._2001.xmlschema.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OpenAPIService {

    @WebMethod(action = "enableSubscription")
    public void enableSubscription(
        @WebParam(partName = "parameter2", name = "enableSubscription1", targetNamespace = "utcs")
        org.w3._2001.xmlschema.ArrayOfint parameter2
    );

    @WebResult(name = "getProjectInfo1", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getProjectInfo")
    public WSProjectInfo getProjectInfo();

    @WebMethod(action = "ping")
    public void ping();

    @WebResult(name = "getUptime1", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getUptime")
    public long getUptime();

    @WebResult(name = "getValues2", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getValues")
    public values.utcs.ArrayOfWSResourceValue getValues(
        @WebParam(partName = "parameter4", name = "getValues1", targetNamespace = "utcs")
        org.w3._2001.xmlschema.ArrayOfint parameter4
    );

    @WebResult(name = "getDatalineInputIDs1", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getDatalineInputIDs")
    public ArrayOfWSDatalineResource getDatalineInputIDs();

    @WebResult(name = "getTime1", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getTime")
    public WSDate getTime();

    @WebResult(name = "waitForEvents2", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "waitForEvents")
    public WSEventPackage waitForEvents(
        @WebParam(partName = "parameter1", name = "waitForEvents1", targetNamespace = "utcs")
        int parameter1
    );

    @WebResult(name = "setValues2", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "setValues")
    public boolean setValues(
        @WebParam(partName = "parameter7", name = "setValues1", targetNamespace = "utcs")
        ArrayOfWSResourceValueEvent parameter7
    );

    @WebResult(name = "authenticate3", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "authenticate")
    public boolean authenticate(
        @WebParam(partName = "parameter5", name = "authenticate1", targetNamespace = "utcs")
        java.lang.String parameter5,
        @WebParam(partName = "parameter6", name = "authenticate2", targetNamespace = "utcs")
        java.lang.String parameter6
    );

    @WebResult(name = "getFWVersion1", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getFWVersion")
    public WSVersionInfo getFWVersion();

    @WebResult(name = "getDatalineOutputIDs1", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getDatalineOutputIDs")
    public ArrayOfWSDatalineResource getDatalineOutputIDs();

    @WebResult(name = "getAPIVersion1", targetNamespace = "utcs", partName = "return")
    @WebMethod(action = "getAPIVersion")
    public int getAPIVersion();

    @WebMethod(action = "disableSubscription")
    public void disableSubscription(
        @WebParam(partName = "parameter3", name = "disableSubscription1", targetNamespace = "utcs")
        org.w3._2001.xmlschema.ArrayOfint parameter3
    );
}
