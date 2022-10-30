import UI.BookingDisplay;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import system.*;
import database.*;
import system.*;

public class Moblima {
    

    public static void main(String[] args) throws IOException, InterruptedException {


        do {
            int choice = mainDisplayOptions();
            switch (choice) {
                case 1:
                     movieListing();
                    break;
    
                case 2:
                    movieDetails();
                     break;
                
                case 3:
                    seatDetails();
                    break;
                case 4:
                    purchaseTicket() ;
                    break;

                case 5:
                    bookingHistory() ;
                    break;
                
                case 6:
                    movieRanking();
                    break;

                case 7:
                    admin() ;
                    break;
                
                case 8:
                    exitDialouge();
                default:
                    break;
            }
            // choice
        } while (true);
        
        

        // PaymentUI pay = new PaymentUI();
        // pay.printPayment();
        

    }





public static int mainDisplayOptions() {

    int choice=0;
    Scanner sc = new Scanner(System.in);
    do {
        System.out.println("======================\n"+
                           "        Moblima       \n"+
                           "======================\n"+
                           "(1) Search/List movie\n(2) View movie details \n"+
                           "(3) Check seat availability and selection of seat/s\n(4) Book and purchase ticket\n"+
                           "(5) View booking history\n(6) List the Top 5 ranking by ticket sales OR by overall reviewers’ ratings\n"+
                           "(7) Admin\n(8) Exit\n");
        
        try {
            System.out.print("What is your choice:  ");
            choice = sc.nextInt();
            if (choice >=1 && choice <=8) break;
            else throw new Exception();
            // choice = Integer.parseInt(sc.next());
        } catch (Exception e) {
            choice = sc.nextInt();
            if (choice >=1 && choice <=8) break;
            else{
                System.out.println("Enter a valid option");
            }
        }

    } while (choice<1 || choice >8);

    return choice;
}

public static void movieListing(){


}

public static void movieDetails(){

    
}


public static void seatDetails(){

    
}

public static void purchaseTicket() throws FileNotFoundException, IOException, InterruptedException{
        // Connection to the managers and UI
        BookingDisplay booking = new BookingDisplay();
        ShowtimeManager Showtimes = new ShowtimeManager();
        Showtimes.ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        int showtimesLength = Showtimes.getLength();
        TicketManager ticketHandle = new TicketManager("Moblima/src/Data/TicketsBooked.csv");

        //Getting user inputs.
        String cineplex = booking.askCineplex();
        String [] movies  = Showtimes.getMovies(showtimesLength);
        int movieChoice = booking.askMovie(movies);
        ArrayList<Showtime> showtimes = Showtimes.getShowtimes(movies[movieChoice-1], cineplex);
        int showtimeChoice = booking.askTiming(showtimes);
        Showtime choosenShowtime = showtimes.get(showtimeChoice-1);
        
        int numberSeats = booking.askTickets();
        Ticket ticket = new Ticket();
        Pricing temp = new Pricing();
        choosenShowtime.printLayout();
        ArrayList <Seat> userSeats = booking.askSeats(numberSeats);
        ticket.Ticket(20, userSeats, choosenShowtime, 301222);

       int confirmation = booking.confirmTicket(ticket);
       if (confirmation == 1){
        System.out.println("Done! ");
        ticketHandle.uploadTicket(ticket);
       }
       else{return;}

}

public static void bookingHistory(){

    
}

public static void movieRanking(){

    
}

public static void admin(){

    
}


public static void exitDialouge(){
    System.out.println("Thank you for using Moblima, Have a pleasant day!.");
    System.exit(0);
    
}

}
