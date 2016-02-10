
package utcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="day" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthWithJanuaryAsOne" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minutes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seconds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDate", propOrder = {
    "day",
    "monthWithJanuaryAsOne",
    "hours",
    "minutes",
    "seconds",
    "year"
})
public class WSDate {

    protected int day;
    protected int monthWithJanuaryAsOne;
    protected int hours;
    protected int minutes;
    protected int seconds;
    protected int year;

    /**
     * Gets the value of the day property.
     * 
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the value of the day property.
     * 
     */
    public void setDay(int value) {
        this.day = value;
    }

    /**
     * Gets the value of the monthWithJanuaryAsOne property.
     * 
     */
    public int getMonthWithJanuaryAsOne() {
        return monthWithJanuaryAsOne;
    }

    /**
     * Sets the value of the monthWithJanuaryAsOne property.
     * 
     */
    public void setMonthWithJanuaryAsOne(int value) {
        this.monthWithJanuaryAsOne = value;
    }

    /**
     * Gets the value of the hours property.
     * 
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets the value of the hours property.
     * 
     */
    public void setHours(int value) {
        this.hours = value;
    }

    /**
     * Gets the value of the minutes property.
     * 
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets the value of the minutes property.
     * 
     */
    public void setMinutes(int value) {
        this.minutes = value;
    }

    /**
     * Gets the value of the seconds property.
     * 
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the value of the seconds property.
     * 
     */
    public void setSeconds(int value) {
        this.seconds = value;
    }

    /**
     * Gets the value of the year property.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

}
