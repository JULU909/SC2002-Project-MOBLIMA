package system;
import enums.*;
import java.util.ArrayList;

// Imports all the enums to be used in the Class

/**
 * Represents the class of Seats, which holds the information at the instance a seat is selected to be booked.
 * @author Harish Vasanth
 * @version 1.0
 * @since 1.0
 */

public class Booking {
    
    private String cineplex;
    private Seat seat;
    private Showtime showtime;
    private int price;

    public void Booking(String cineplex, Seat seat , Showtime showtime , int price){
        this.cineplex = cineplex ;
        this.price = price;
        this.showtime = showtime,
        this.seat = seat;
    
    }

    public Seat getSeat(){
        return this.seat;
    }

    public int getPrice(){
        return this.price;
    }
    
    public Showtime getShowtime(){
        return this.showtime;
    }
}

