
package dk.ihc.openapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSSceneRelayValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSSceneRelayValue">
 *   &lt;complexContent>
 *     &lt;extension base="{utcs.values}WSResourceValue">
 *       &lt;sequence>
 *         &lt;element name="delayTime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="relayValue" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSSceneRelayValue", propOrder = {
    "delayTime",
    "relayValue"
})
public class WSSceneRelayValue
    extends WSResourceValue
{

    protected int delayTime;
    protected boolean relayValue;

    /**
     * Gets the value of the delayTime property.
     * 
     */
    public int getDelayTime() {
        return delayTime;
    }

    /**
     * Sets the value of the delayTime property.
     * 
     */
    public void setDelayTime(int value) {
        this.delayTime = value;
    }

    /**
     * Gets the value of the relayValue property.
     * 
     */
    public boolean isRelayValue() {
        return relayValue;
    }

    /**
     * Sets the value of the relayValue property.
     * 
     */
    public void setRelayValue(boolean value) {
        this.relayValue = value;
    }

}