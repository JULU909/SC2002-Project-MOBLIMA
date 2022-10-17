package src.system;
import src.UI.BookingDisplay;
import src.enums.*;

// USed to test the code/// remove before submission
public class Testing {
    
    
    

    public static void main(String[] args)  {

        Showtime show = new Showtime();
        show.Showtime(1157,12112021,CineplexTypes.ALPHA);
        show.printLayout();
        BookingDisplay bk = new BookingDisplay();
        bk.askCineplex();
        
        

    }

    







}
