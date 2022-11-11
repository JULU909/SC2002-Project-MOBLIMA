package entities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import enums.AgeGroup;
import model.*;

public class Pricing {
	private static double basedPrice = 7;
    public double getPrice(List<AgeGroup> ageGroups, LocalDate inputDate) throws FileNotFoundException, IOException{
        
        double totalPrice = 0;
        DayOfWeek day = DayOfWeek.of(inputDate.get(ChronoField.DAY_OF_WEEK));
        HolidayManager hm = new HolidayManager();
        ArrayList<LocalDate> holidayDataList = hm.readHolidayDataCSV();

        //Checking if weekend or holiday
        if (day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY || day == DayOfWeek.FRIDAY || holidayDataList.contains(inputDate)) {
            this.basedPrice += 3.5;
        }
        totalPrice = basedPrice * ageGroups.size();
        
        //Give discount to child and senior
        for (AgeGroup ageGroup : ageGroups) {
            if(ageGroup == AgeGroup.SENIOR || ageGroup == AgeGroup.CHILD) {
                totalPrice -= 2;
            }   
        }
        return totalPrice;
    }
    
    public double getBasedPrice() {
    	return this.basedPrice;
    }
    
    public void setBasedPrice(double basedPrice) {
    	this.basedPrice = basedPrice;
    }

}
