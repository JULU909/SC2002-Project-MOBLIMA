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
import enums.SeatTypes;
import model.*;

/**
 * This class initialises all relevant pricing for the movie tickets
 * @author
 * @version 1.0
 * @since 2022-11-13
 */


public class Pricing {
	private static double basedPrice = 7;

    /**
     * This method adds a movie into the CSV
     * @param ageGroups a List Array of ENUMERATIONS ADULT,SENIOR,CHILD
     * @param inputDate Real time input Date, java.time library
     * @param userSeats , a List Array of Seat objects containing attributes
     * @param choosenShowtime , A Showtime object containing many attributes of showtime
     * @return returns a double, which is the total price of all the tickets bought by the customer object
     * @throws FileNotFoundException
     * @throws IOException
     */
    public double getPrice(List<AgeGroup> ageGroups, LocalDate inputDate ,ArrayList <Seat> userSeats,
    Showtime choosenShowtime) throws FileNotFoundException, IOException{
        double totalPrice = 0;
        DayOfWeek day = DayOfWeek.of(inputDate.get(ChronoField.DAY_OF_WEEK));
        HolidayManager hm = new HolidayManager();
        ArrayList<LocalDate> holidayDataList = hm.readHolidayDataCSV();
        Double price = this.basedPrice;
        for (int i = 0 ; i <userSeats.size(); i++){
            SeatTypes s =  choosenShowtime.getSeatType(userSeats.get(i).getCol(),userSeats.get(i).getRow());
            if (s.equals(SeatTypes.DELUXE) || s.equals(SeatTypes.COUPLE)){
                price+=0.5;
            }
         }

        if(choosenShowtime.getCinemaType().equals("GOLD")){
            price+=0.5;
        }
        else if (choosenShowtime.getCinemaType().equals("PREMIUM")){
            price+=1;
        }
        //Checking if weekend or holiday
        if (day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY || day == DayOfWeek.FRIDAY || holidayDataList.contains(inputDate)) {
            price += 3.5;
        }
        totalPrice = price * ageGroups.size();
        
        //Give discount to child and senior
        for (AgeGroup ageGroup : ageGroups) {
            if(ageGroup == AgeGroup.SENIOR || ageGroup == AgeGroup.CHILD) {
                totalPrice -= 2;
            }   
        }
        return totalPrice;
    }
    
    /**
     * These methods are getter and setter functions
     */
    public double getBasedPrice() {
    	return Pricing.basedPrice;
    }
    
    public void setBasedPrice(double basedPrice) {
    	Pricing.basedPrice = basedPrice;
    }

}
