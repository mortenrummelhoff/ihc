
package values.utcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSWeekdayValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSWeekdayValue">
 *   &lt;complexContent>
 *     &lt;extension base="{utcs.values}WSResourceValue">
 *       &lt;sequence>
 *         &lt;element name="weekdayNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSWeekdayValue", propOrder = {
    "weekdayNumber"
})
public class WSWeekdayValue
    extends WSResourceValue
{

    protected int weekdayNumber;

    /**
     * Gets the value of the weekdayNumber property.
     * 
     */
    public int getWeekdayNumber() {
        return weekdayNumber;
    }

    /**
     * Sets the value of the weekdayNumber property.
     * 
     */
    public void setWeekdayNumber(int value) {
        this.weekdayNumber = value;
    }

}
