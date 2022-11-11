package system;
import enums.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import system.Showtime;
import database.Customer;

// Imports all the enums to be used in the Class

/**
 * Represents the class of Seats, which holds the information at the instance a seat is selected to be booked.
 * @author Harish Vasanth
 * @version 1.0
 * @since 1.0
 */

public class Ticket {
    
    private ArrayList <Seat> seats;
    private Showtime showtime;
    private double price;
    private int date;
    private Customer customer;
    private int ticketID;
    public Ticket(Customer customer, Double cost, ArrayList <Seat> seats , Showtime showtime , int date ){
        this.price = cost;
        this.showtime = showtime;
        this.seats = seats;
        this.date = date;
        this.customer=customer;
    }

    public ArrayList <Seat> getSeats(){
        return this.seats;
    }

    public Double getPrice(){
        return this.price;
    }
    
    public Showtime getShowtime(){
        return this.showtime;
    }

    public String getID(){
        Seat mainSeat = seats.get(0);
        String d = Integer.toString(date) ;
        String ID = Integer.toString(mainSeat.getCol()) + Integer.toString(mainSeat.getRow()) +     "2022" + d.substring(0, 2)  + d.substring(2, 4) +  Integer.toString(showtime.getTime());
        return ID;
    
        
    }

    public int getDate(){
        return this.date;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    
}



