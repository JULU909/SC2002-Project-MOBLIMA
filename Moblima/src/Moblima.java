import UI.BookingDisplay;
import UI.SearchMovieUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import system.*;
import database.*;
import enums.Day;
import system.*;

public class Moblima {
    

    public static void main(String[] args) throws IOException, InterruptedException {


        do {
            Scanner sc = new Scanner(System.in);
            int choice = mainDisplayOptions();
            int selection;
            switch (choice) {
                case 1:
                    System.out.println("=====================================================================================\n");
                    System.out.println("                          Searching Movies                                           \n");
                    System.out.println("=====================================================================================\n");
                    System.out.println("Do you want to Display All Movies or Display one Movie? \n1) Display All Movies\n2) Display ONE movie (by index, cannot be 0 or negative)\n");
                    System.out.println("Your Choice please: ");

                    // new SearchMovieUI().DisplayAll();
                    selection = sc.nextInt();
                    if(selection == 1){
                        new SearchMovieUI().DisplayAll();
                    }
                    else if(selection == 2){
                        System.out.println("\nSelect the index of the movie you want to display: ");
                        selection = sc.nextInt();
                        new SearchMovieUI().DisplayOne(selection);
                    }
                    // new SearchMovieUI().DisplayOne(selection); // selection refers to the index
                    break;
    
                case 2:
                    System.out.println("=====================================================================================\n");
                    System.out.println("                          Viewing Movie Details                                      \n");
                    System.out.println("=====================================================================================\n");
                    System.out.println("Do you want to View ALL Movie details or View ONE Movie detail? \n1) View All Movie details\n2) View ONE Movie detail (by index, cannot be 0 or negative)\n");
                    System.out.println("Your Choice please: ");
                    selection = sc.nextInt();
                    MovieInfoManager m1 = new MovieInfoManager();
                    m1.getDataAll();
                    
                    if(selection == 1){
                    m1.PrintAll();
                    }
                    else if(selection == 2){
                    // Scanner sc = new Scanner(System.in);
                        System.out.println("Select the index of the movie you want to display: ");
                        selection = sc.nextInt();
                        m1.PrintOne(selection);
                    }

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
                    sc.close();
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
        System.out.println("=====================================================================================\n"+
                           "=====================================================================================\n"+
                           "        Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)       \n"+
                           "=====================================================================================\n"+
                           "=====================================================================================\n"+
                           "(1) Search/List Movies\n\n"+
                           "(2) Check seat availability and selection of seat/s\n\n(3) Book and purchase ticket\n\n"+
                           "(4) View booking history\n\n"+
                           "(5) Admin\n\n(6) Exit\n\nChoices (1~6): ");
        
        try {
            System.out.print("\nWhat is your choice:  ");
            choice = sc.nextInt();
            if (choice >=1 && choice <=8) break;
            else throw new Exception();
            // choice = Integer.parseInt(sc.next());
        } catch (Exception e) {
            choice = sc.nextInt();
            if (choice >=1 && choice <=8) break;
            else{
                System.out.println("Please enter a valid option");
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
        ShowtimeManager Showtimes = new ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        int showtimesLength = Showtimes.getLength();
        TicketManager ticketHandle = new TicketManager("Moblima/src/Data/TicketsBooked.csv");

        //Getting user inputs.
        String cineplex = booking.askCineplex();
        String [] movies  = Showtimes.getMovies(showtimesLength);
        int movieChoice = booking.askMovie(movies);
        ArrayList<Showtime> showtimes = Showtimes.getShowtimes(movies[movieChoice-1], cineplex);
        LocalDate inputDate = booking.askDate();
        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("ddMMyy"));
        int showtimeChoice = booking.askTiming(showtimes);
        Showtime choosenShowtime = showtimes.get(showtimeChoice-1);
        choosenShowtime.setLayout();
        int numberSeats = booking.askTickets();
        Ticket ticket = new Ticket();
        Pricing price = new Pricing();
        choosenShowtime.printLayout();
        ArrayList <Seat> userSeats = booking.askSeats(numberSeats);
        ticket.Ticket(price.getPrice(), userSeats, choosenShowtime, Integer.valueOf(formattedDate));

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
