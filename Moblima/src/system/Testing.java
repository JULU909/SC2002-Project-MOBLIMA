package src.system;
import java.io.FileNotFoundException;
import java.io.IOException;

import src.UI.BookingDisplay;
import src.enums.*;
import src.database.Datamanager;
import src.database.ShowtimeManager;

// USed to test the code/// remove before submission
public class Testing {
    
    
    

    public static void main(String[] args) throws FileNotFoundException, IOException  {

        Showtime show = new Showtime();
        show.Showtime(1157,12112021,"Alpha","movuie");
        
        BookingDisplay bk = new BookingDisplay();
        bk.askCineplex();
        show.printLayout();
        int seats = bk.askTickets();
        
        bk.askSeats(seats);
        ShowtimeManager Showtimes = new ShowtimeManager();
        Showtimes.ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        int length = Showtimes.getLength();
        Showtime[] data = new Showtime[100] ;
        
        data = Showtimes.getDataAll(length);
        String [] names  = Showtimes.getMovies(length);
        
        int count = 0;
        while (names[count] != null){
            System.out.println(names[count]);
            count++;
        }
       
    }

    







}
