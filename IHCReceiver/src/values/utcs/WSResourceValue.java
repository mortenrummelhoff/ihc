
package values.utcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSResourceValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSResourceValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSResourceValue")
@XmlSeeAlso({
    WSSceneDimmerValue.class,
    WSPhoneNumberValue.class,
    WSDateValue.class,
    WSTimerValue.class,
    WSTimeValue.class,
    WSBooleanValue.class,
    WSSceneShutterSimpleValue.class,
    WSFloatingPointValue.class,
    WSIntegerValue.class,
    WSEnumValue.class,
    WSSceneRelayValue.class,
    WSWeekdayValue.class
})
public class WSResourceValue {


}
