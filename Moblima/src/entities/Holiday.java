package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class initialises all relevant Holiday Attributes with getter and setter functions for Holiday. Also has a real time date and time library imported
 * @author Kit Ye
 * @version 1.0
 * @since 2022-11-13
 */

public class Holiday {
    private String holidayName;
    private LocalDate holidayDate;
    
    /**
     * Constructs a holiday object
     * @param holidayName The name of the holiday
     * @param holidayDate The date of the holiday
     */
    public Holiday(String holidayName, LocalDate holidayDate){
        this.holidayName = holidayName; this.holidayDate = holidayDate; 
    }
    /**
     * Setting a holiday date
     * @param holidayDate The date of the holiday
     */
    public void setHolidayDate(LocalDate holidayDate){
        this.holidayDate = holidayDate;
    }

    /**
     * Getting a holiday date
     * @return The date of the holiday
     */
    public LocalDate getHolidayDate(){
        return this.holidayDate;
    }

    /**
     * Getting a holiday name
     * @return The name of the holiday
     */
    public String getHolidayName(){
        return this.holidayName;
    }
    /**
     * Setting a holiday name
     * @param holidayName The name of the holiday
     */
    public void setHolidayName(String holidayName){
        this.holidayName = holidayName;
    }
    /**
     * Getting the date of the holiday as a string
     * @return The date of the holiday as a string
     */
    public String getHolidayDateString(){
        return this.holidayDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }
    /**
     * Convert the details of the holiday to a string
     * @return String containing details of the string
     */
    public String toString(){
        return (this.getHolidayName() + ": " + this.getHolidayDateString());
    }
}
