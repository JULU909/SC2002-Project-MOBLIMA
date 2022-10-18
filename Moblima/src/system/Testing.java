package src.system;
import java.io.FileNotFoundException;
import java.io.IOException;

import src.UI.BookingDisplay;
import src.enums.*;
import src.database.Datamanager;

// USed to test the code/// remove before submission
public class Testing {
    
    
    

    public static void main(String[] args) throws FileNotFoundException, IOException  {

        Showtime show = new Showtime();
        show.Showtime(1157,12112021,CineplexTypes.ALPHA);
        
        BookingDisplay bk = new BookingDisplay();
        bk.askCineplex();
        
        int seats = bk.askTickets();
        
        bk.askSeats(seats);
        Datamanager Showtimes = new Datamanager();
        Showtimes.Datamanager("src/data/movieinformation.csv");
        Showtimes.getData();

    }

    







}
