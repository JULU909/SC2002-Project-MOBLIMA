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
    
    public Holiday(String holidayName, LocalDate holidayDate){
        this.holidayName = holidayName; this.holidayDate = holidayDate; 
    }

    public void setHolidayDate(LocalDate holidayDate){
        this.holidayDate = holidayDate;
    }


    public LocalDate getHolidayDate(){
        return this.holidayDate;
    }


    public String getHolidayName(){
        return this.holidayName;
    }

    public void setHolidayName(String holidayName){
        this.holidayName = holidayName;
    }

    public String getHolidayDateString(){
        return this.holidayDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }

    public String toString(){
        return (this.getHolidayName() + ": " + this.getHolidayDateString());
    }
}
