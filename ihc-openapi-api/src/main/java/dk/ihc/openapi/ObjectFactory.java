
package dk.ihc.openapi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dk.ihc.openapi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetProjectInfo1_QNAME = new QName("utcs", "getProjectInfo1");
    private final static QName _SetValues1_QNAME = new QName("utcs", "setValues1");
    private final static QName _GetFWVersion1_QNAME = new QName("utcs", "getFWVersion1");
    private final static QName _GetUptime1_QNAME = new QName("utcs", "getUptime1");
    private final static QName _SetValues2_QNAME = new QName("utcs", "setValues2");
    private final static QName _GetDatalineInputIDs1_QNAME = new QName("utcs", "getDatalineInputIDs1");
    private final static QName _DisableSubscription1_QNAME = new QName("utcs", "disableSubscription1");
    private final static QName _GetDatalineOutputIDs1_QNAME = new QName("utcs", "getDatalineOutputIDs1");
    private final static QName _GetAPIVersion1_QNAME = new QName("utcs", "getAPIVersion1");
    private final static QName _GetValues1_QNAME = new QName("utcs", "getValues1");
    private final static QName _GetValues2_QNAME = new QName("utcs", "getValues2");
    private final static QName _WaitForEvents2_QNAME = new QName("utcs", "waitForEvents2");
    private final static QName _WaitForEvents1_QNAME = new QName("utcs", "waitForEvents1");
    private final static QName _EnableSubscription1_QNAME = new QName("utcs", "enableSubscription1");
    private final static QName _Authenticate3_QNAME = new QName("utcs", "authenticate3");
    private final static QName _GetTime1_QNAME = new QName("utcs", "getTime1");
    private final static QName _Authenticate2_QNAME = new QName("utcs", "authenticate2");
    private final static QName _Authenticate1_QNAME = new QName("utcs", "authenticate1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dk.ihc.openapi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfint }
     * 
     */
    public ArrayOfint createArrayOfint() {
        return new ArrayOfint();
    }

    /**
     * Create an instance of {@link WSSceneDimmerValue }
     * 
     */
    public WSSceneDimmerValue createWSSceneDimmerValue() {
        return new WSSceneDimmerValue();
    }

    /**
     * Create an instance of {@link WSPhoneNumberValue }
     * 
     */
    public WSPhoneNumberValue createWSPhoneNumberValue() {
        return new WSPhoneNumberValue();
    }

    /**
     * Create an instance of {@link WSDateValue }
     * 
     */
    public WSDateValue createWSDateValue() {
        return new WSDateValue();
    }

    /**
     * Create an instance of {@link WSTimerValue }
     * 
     */
    public WSTimerValue createWSTimerValue() {
        return new WSTimerValue();
    }

    /**
     * Create an instance of {@link ArrayOfWSResourceValue }
     * 
     */
    public ArrayOfWSResourceValue createArrayOfWSResourceValue() {
        return new ArrayOfWSResourceValue();
    }

    /**
     * Create an instance of {@link WSTimeValue }
     * 
     */
    public WSTimeValue createWSTimeValue() {
        return new WSTimeValue();
    }

    /**
     * Create an instance of {@link WSBooleanValue }
     * 
     */
    public WSBooleanValue createWSBooleanValue() {
        return new WSBooleanValue();
    }

    /**
     * Create an instance of {@link WSSceneShutterSimpleValue }
     * 
     */
    public WSSceneShutterSimpleValue createWSSceneShutterSimpleValue() {
        return new WSSceneShutterSimpleValue();
    }

    /**
     * Create an instance of {@link WSFloatingPointValue }
     * 
     */
    public WSFloatingPointValue createWSFloatingPointValue() {
        return new WSFloatingPointValue();
    }

    /**
     * Create an instance of {@link WSIntegerValue }
     * 
     */
    public WSIntegerValue createWSIntegerValue() {
        return new WSIntegerValue();
    }

    /**
     * Create an instance of {@link WSEnumValue }
     * 
     */
    public WSEnumValue createWSEnumValue() {
        return new WSEnumValue();
    }

    /**
     * Create an instance of {@link WSSceneRelayValue }
     * 
     */
    public WSSceneRelayValue createWSSceneRelayValue() {
        return new WSSceneRelayValue();
    }

    /**
     * Create an instance of {@link WSWeekdayValue }
     * 
     */
    public WSWeekdayValue createWSWeekdayValue() {
        return new WSWeekdayValue();
    }

    /**
     * Create an instance of {@link WSResourceValue }
     * 
     */
    public WSResourceValue createWSResourceValue() {
        return new WSResourceValue();
    }

    /**
     * Create an instance of {@link WSDate }
     * 
     */
    public WSDate createWSDate() {
        return new WSDate();
    }

    /**
     * Create an instance of {@link WSEventPackage }
     * 
     */
    public WSEventPackage createWSEventPackage() {
        return new WSEventPackage();
    }

    /**
     * Create an instance of {@link ArrayOfWSDatalineResource }
     * 
     */
    public ArrayOfWSDatalineResource createArrayOfWSDatalineResource() {
        return new ArrayOfWSDatalineResource();
    }

    /**
     * Create an instance of {@link ArrayOfWSResourceValueEvent }
     * 
     */
    public ArrayOfWSResourceValueEvent createArrayOfWSResourceValueEvent() {
        return new ArrayOfWSResourceValueEvent();
    }

    /**
     * Create an instance of {@link WSProjectInfo }
     * 
     */
    public WSProjectInfo createWSProjectInfo() {
        return new WSProjectInfo();
    }

    /**
     * Create an instance of {@link WSVersionInfo }
     * 
     */
    public WSVersionInfo createWSVersionInfo() {
        return new WSVersionInfo();
    }

    /**
     * Create an instance of {@link WSResourceValueEvent }
     * 
     */
    public WSResourceValueEvent createWSResourceValueEvent() {
        return new WSResourceValueEvent();
    }

    /**
     * Create an instance of {@link WSDatalineResource }
     * 
     */
    public WSDatalineResource createWSDatalineResource() {
        return new WSDatalineResource();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSProjectInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getProjectInfo1")
    public JAXBElement<WSProjectInfo> createGetProjectInfo1(WSProjectInfo value) {
        return new JAXBElement<WSProjectInfo>(_GetProjectInfo1_QNAME, WSProjectInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfWSResourceValueEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "setValues1")
    public JAXBElement<ArrayOfWSResourceValueEvent> createSetValues1(ArrayOfWSResourceValueEvent value) {
        return new JAXBElement<ArrayOfWSResourceValueEvent>(_SetValues1_QNAME, ArrayOfWSResourceValueEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSVersionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getFWVersion1")
    public JAXBElement<WSVersionInfo> createGetFWVersion1(WSVersionInfo value) {
        return new JAXBElement<WSVersionInfo>(_GetFWVersion1_QNAME, WSVersionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getUptime1")
    public JAXBElement<Long> createGetUptime1(Long value) {
        return new JAXBElement<Long>(_GetUptime1_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "setValues2")
    public JAXBElement<Boolean> createSetValues2(Boolean value) {
        return new JAXBElement<Boolean>(_SetValues2_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfWSDatalineResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getDatalineInputIDs1")
    public JAXBElement<ArrayOfWSDatalineResource> createGetDatalineInputIDs1(ArrayOfWSDatalineResource value) {
        return new JAXBElement<ArrayOfWSDatalineResource>(_GetDatalineInputIDs1_QNAME, ArrayOfWSDatalineResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "disableSubscription1")
    public JAXBElement<ArrayOfint> createDisableSubscription1(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_DisableSubscription1_QNAME, ArrayOfint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfWSDatalineResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getDatalineOutputIDs1")
    public JAXBElement<ArrayOfWSDatalineResource> createGetDatalineOutputIDs1(ArrayOfWSDatalineResource value) {
        return new JAXBElement<ArrayOfWSDatalineResource>(_GetDatalineOutputIDs1_QNAME, ArrayOfWSDatalineResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getAPIVersion1")
    public JAXBElement<Integer> createGetAPIVersion1(Integer value) {
        return new JAXBElement<Integer>(_GetAPIVersion1_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getValues1")
    public JAXBElement<ArrayOfint> createGetValues1(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_GetValues1_QNAME, ArrayOfint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfWSResourceValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getValues2")
    public JAXBElement<ArrayOfWSResourceValue> createGetValues2(ArrayOfWSResourceValue value) {
        return new JAXBElement<ArrayOfWSResourceValue>(_GetValues2_QNAME, ArrayOfWSResourceValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSEventPackage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "waitForEvents2")
    public JAXBElement<WSEventPackage> createWaitForEvents2(WSEventPackage value) {
        return new JAXBElement<WSEventPackage>(_WaitForEvents2_QNAME, WSEventPackage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "waitForEvents1")
    public JAXBElement<Integer> createWaitForEvents1(Integer value) {
        return new JAXBElement<Integer>(_WaitForEvents1_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "enableSubscription1")
    public JAXBElement<ArrayOfint> createEnableSubscription1(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_EnableSubscription1_QNAME, ArrayOfint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "authenticate3")
    public JAXBElement<Boolean> createAuthenticate3(Boolean value) {
        return new JAXBElement<Boolean>(_Authenticate3_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "getTime1")
    public JAXBElement<WSDate> createGetTime1(WSDate value) {
        return new JAXBElement<WSDate>(_GetTime1_QNAME, WSDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "authenticate2")
    public JAXBElement<String> createAuthenticate2(String value) {
        return new JAXBElement<String>(_Authenticate2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "utcs", name = "authenticate1")
    public JAXBElement<String> createAuthenticate1(String value) {
        return new JAXBElement<String>(_Authenticate1_QNAME, String.class, null, value);
    }

}
