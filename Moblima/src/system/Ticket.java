package system;
import enums.*;
import java.util.ArrayList;
import system.Showtime;
import database.User;

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
    private int price;
    private int date;
    private User user = new User("John Doe","123");
    private int ticketID;
    public Ticket(int price, ArrayList <Seat> seats , Showtime showtime , int date ){
        this.price = price;
        this.showtime = showtime;
        this.seats = seats;
        this.date = date;
    }

    public Ticket(){
        
    }

    public ArrayList <Seat> getSeats(){
        return this.seats;
    }

    public int getPrice(){
        return this.price;
    }
    
    public Showtime getShowtime(){
        return this.showtime;
    }

    public String getID(){
        Seat mainSeat = seats.get(0);
        
        String ID = Integer.toString(mainSeat.getCol()) + Integer.toString(mainSeat.getRow()) +  Integer.toString(showtime.getTime()) +   Integer.toString(date) ;
        return ID;
    
        
    }

    public int getDate(){
        return this.date;
    }

    public User getUser(){
        return this.user;
    }

    
}



