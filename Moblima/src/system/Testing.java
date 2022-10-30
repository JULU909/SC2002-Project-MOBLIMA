package src.system;
import java.io.FileNotFoundException;
import java.io.IOException;

import src.UI.BookingDisplay;
import src.enums.*;
import src.database.Datamanager;
import java.util.ArrayList; // import the ArrayList class

import src.database.ShowtimeManager;

// USed to test the code/// remove before submission
public class Testing {
    
    
    

    public static void main(String[] args) throws FileNotFoundException, IOException  {
        String cineplex;
        
        BookingDisplay bk = new BookingDisplay();
        cineplex = bk.askCineplex();
        cineplex = "ALPHA";
        ShowtimeManager Showtimes = new ShowtimeManager();
        Showtimes.ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        int length = Showtimes.getLength();
        Showtime[] data;
        
        data = Showtimes.getDataAll(length);
        String [] names  = Showtimes.getMovies(length);
        
        int choice = bk.askMovie(names);
        ArrayList<Showtime> showtimes = Showtimes.getShowtimes(names[choice-1], cineplex);
        choice = bk.askTiming(showtimes);
        Showtime choosenShowtime = showtimes.get(choice-1);
        choosenShowtime.printLayout();
        int seats = bk.askTickets();
        
        bk.askSeats(seats);
       
    }

    







}
