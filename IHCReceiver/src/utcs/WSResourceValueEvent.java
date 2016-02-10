
package utcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import values.utcs.WSResourceValue;


/**
 * <p>Java class for WSResourceValueEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSResourceValueEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="m_resourceID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="m_value" type="{utcs.values}WSResourceValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSResourceValueEvent", propOrder = {
    "mResourceID",
    "mValue"
})
public class WSResourceValueEvent {

    @XmlElement(name = "m_resourceID")
    protected int mResourceID;
    @XmlElement(name = "m_value", required = true, nillable = true)
    protected WSResourceValue mValue;

    /**
     * Gets the value of the mResourceID property.
     * 
     */
    public int getMResourceID() {
        return mResourceID;
    }

    /**
     * Sets the value of the mResourceID property.
     * 
     */
    public void setMResourceID(int value) {
        this.mResourceID = value;
    }

    /**
     * Gets the value of the mValue property.
     * 
     * @return
     *     possible object is
     *     {@link WSResourceValue }
     *     
     */
    public WSResourceValue getMValue() {
        return mValue;
    }

    /**
     * Sets the value of the mValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSResourceValue }
     *     
     */
    public void setMValue(WSResourceValue value) {
        this.mValue = value;
    }

}
