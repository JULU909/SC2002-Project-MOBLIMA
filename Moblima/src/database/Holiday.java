package database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Holiday {
    private String holidayName;
    private LocalDate startDate;
    private LocalDate endDate;
    
    public Holiday(String holidayName, LocalDate startDate, LocalDate endDate){
        this.holidayName = holidayName; this.startDate = startDate; this.endDate = endDate; 
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public LocalDate getStartDate(){
        return this.startDate;
    }

    public LocalDate getEndDate(){
        return this.endDate;
    }

    public String getHolidayName(){
        return this.holidayName;
    }

    public void setHolidayName(String holidayName){
        this.holidayName = holidayName;
    }

    public String getStartDateString(){
        return this.startDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }

    public String getEndDateString(){
        return this.endDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }

    public String toString(){
        return (this.getHolidayName() + ": " + this.getStartDateString() + " - " + this.getEndDateString());
    }
}
