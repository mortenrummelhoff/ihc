
package utcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSDatalineResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSDatalineResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resourceID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="datalineNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDatalineResource", propOrder = {
    "resourceID",
    "datalineNumber"
})
public class WSDatalineResource {

    protected int resourceID;
    protected int datalineNumber;

    /**
     * Gets the value of the resourceID property.
     * 
     */
    public int getResourceID() {
        return resourceID;
    }

    /**
     * Sets the value of the resourceID property.
     * 
     */
    public void setResourceID(int value) {
        this.resourceID = value;
    }

    /**
     * Gets the value of the datalineNumber property.
     * 
     */
    public int getDatalineNumber() {
        return datalineNumber;
    }

    /**
     * Sets the value of the datalineNumber property.
     * 
     */
    public void setDatalineNumber(int value) {
        this.datalineNumber = value;
    }

}
