
package utcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSEventPackage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSEventPackage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resourceValueEvents" type="{utcs}ArrayOfWSResourceValueEvent"/>
 *         &lt;element name="controllerExecutionRunning" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="subscriptionAmount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSEventPackage", propOrder = {
    "resourceValueEvents",
    "controllerExecutionRunning",
    "subscriptionAmount"
})
public class WSEventPackage {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfWSResourceValueEvent resourceValueEvents;
    protected boolean controllerExecutionRunning;
    protected int subscriptionAmount;

    /**
     * Gets the value of the resourceValueEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSResourceValueEvent }
     *     
     */
    public ArrayOfWSResourceValueEvent getResourceValueEvents() {
        return resourceValueEvents;
    }

    /**
     * Sets the value of the resourceValueEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSResourceValueEvent }
     *     
     */
    public void setResourceValueEvents(ArrayOfWSResourceValueEvent value) {
        this.resourceValueEvents = value;
    }

    /**
     * Gets the value of the controllerExecutionRunning property.
     * 
     */
    public boolean isControllerExecutionRunning() {
        return controllerExecutionRunning;
    }

    /**
     * Sets the value of the controllerExecutionRunning property.
     * 
     */
    public void setControllerExecutionRunning(boolean value) {
        this.controllerExecutionRunning = value;
    }

    /**
     * Gets the value of the subscriptionAmount property.
     * 
     */
    public int getSubscriptionAmount() {
        return subscriptionAmount;
    }

    /**
     * Sets the value of the subscriptionAmount property.
     * 
     */
    public void setSubscriptionAmount(int value) {
        this.subscriptionAmount = value;
    }

}
