package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return (this.getHolidayName() + ": " + this.getHolidayDate() + " - " + this.getHolidayDateString());
    }
}
