
package utcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSProjectInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSProjectInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="visualMinorVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="visualMajorVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="projectMajorRevision" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="projectMinorRevision" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastmodified" type="{utcs}WSDate"/>
 *         &lt;element name="projectNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="installerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSProjectInfo", propOrder = {
    "visualMinorVersion",
    "visualMajorVersion",
    "projectMajorRevision",
    "projectMinorRevision",
    "lastmodified",
    "projectNumber",
    "customerName",
    "installerName"
})
public class WSProjectInfo {

    protected int visualMinorVersion;
    protected int visualMajorVersion;
    protected int projectMajorRevision;
    protected int projectMinorRevision;
    @XmlElement(required = true, nillable = true)
    protected WSDate lastmodified;
    @XmlElement(required = true)
    protected String projectNumber;
    @XmlElement(required = true)
    protected String customerName;
    @XmlElement(required = true)
    protected String installerName;

    /**
     * Gets the value of the visualMinorVersion property.
     * 
     */
    public int getVisualMinorVersion() {
        return visualMinorVersion;
    }

    /**
     * Sets the value of the visualMinorVersion property.
     * 
     */
    public void setVisualMinorVersion(int value) {
        this.visualMinorVersion = value;
    }

    /**
     * Gets the value of the visualMajorVersion property.
     * 
     */
    public int getVisualMajorVersion() {
        return visualMajorVersion;
    }

    /**
     * Sets the value of the visualMajorVersion property.
     * 
     */
    public void setVisualMajorVersion(int value) {
        this.visualMajorVersion = value;
    }

    /**
     * Gets the value of the projectMajorRevision property.
     * 
     */
    public int getProjectMajorRevision() {
        return projectMajorRevision;
    }

    /**
     * Sets the value of the projectMajorRevision property.
     * 
     */
    public void setProjectMajorRevision(int value) {
        this.projectMajorRevision = value;
    }

    /**
     * Gets the value of the projectMinorRevision property.
     * 
     */
    public int getProjectMinorRevision() {
        return projectMinorRevision;
    }

    /**
     * Sets the value of the projectMinorRevision property.
     * 
     */
    public void setProjectMinorRevision(int value) {
        this.projectMinorRevision = value;
    }

    /**
     * Gets the value of the lastmodified property.
     * 
     * @return
     *     possible object is
     *     {@link WSDate }
     *     
     */
    public WSDate getLastmodified() {
        return lastmodified;
    }

    /**
     * Sets the value of the lastmodified property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSDate }
     *     
     */
    public void setLastmodified(WSDate value) {
        this.lastmodified = value;
    }

    /**
     * Gets the value of the projectNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectNumber() {
        return projectNumber;
    }

    /**
     * Sets the value of the projectNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectNumber(String value) {
        this.projectNumber = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the installerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallerName() {
        return installerName;
    }

    /**
     * Sets the value of the installerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallerName(String value) {
        this.installerName = value;
    }

}
