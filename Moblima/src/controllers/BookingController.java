package controllers;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import enums.*;
import View.BookedHistoryUI;
import View.BookingDisplay;
import View.SearchMovieUI;
import View.StaffLoginUI;
import entities.*;
import enums.AgeGroup;
import enums.UserType;
import model.*;

/**	Control Class for Customers to interact with to book seat
 * @author 
 * @version 1.0
 * @since 2022-11-13
 */


public class BookingController {
    /**
     * This method displays the bookingDisplay UI and gets all relevant information from Customer object to purchaseTicket
     * @param customer customer object with attributes such as age, ageGroup, mobileNumber, emailAddress, movieHistory
	 * @throws FileNotFoundException
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public static void purchaseTicket(Customer customer) throws FileNotFoundException, IOException, InterruptedException, ParseException {
        // Connection to the managers and UI
        BookingDisplay booking = new BookingDisplay();
        ShowtimeManager Showtimes = new ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        int showtimesLength = Showtimes.getLength();
        TicketManager ticketHandle = new TicketManager("Moblima/src/Data/TicketsBooked.csv");
        // Getting user inputs.
        String cineplex = booking.askCineplex();
        if (cineplex.equals("exit")){
            return;
        }
        MovieInfoManager movieManager = new MovieInfoManager();
        ArrayList<Movie> showingMovies = movieManager.findShowingMovies();
        int movieChoice = booking.askMovie(showingMovies);
        String movieName = showingMovies.get(movieChoice).getTitle();
        LocalDate inputDate = booking.askDate();
        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("ddMMyy"));
        ArrayList<Showtime> showtimes = Showtimes.getShowtimes(movieName, cineplex ,Integer.valueOf(formattedDate) );
        int showtimeChoice = booking.askTiming(showtimes);
        if(showtimeChoice == -1){
            return;
        }
        Showtime choosenShowtime = showtimes.get(showtimeChoice - 1);
        choosenShowtime.setDate(Integer.valueOf(formattedDate));
        choosenShowtime.setLayout();
       
        while (true){
            int numberSeats = booking.askTickets();
            choosenShowtime.printLayout();
            ArrayList<Seat> userSeats = booking.askSeats(numberSeats);
            int check = 0;
            for (int i = 0 ; i <userSeats.size(); i++){
                
                if (choosenShowtime.getSeatStatus(userSeats.get(i).getCol(),userSeats.get(i).getRow()).equals(SeatStatus.OCCUPIED)){
                 System.out.println("Seats you want to book have been previously \nbooked by another customer, select other seats! \n ");
                 check =1;
                 break;
                 
                }
             }
            if (check == 1){
                continue;
            }
            ArrayList<AgeGroup> ages = booking.getAges(numberSeats);
            
            
        
            
            Pricing price = new Pricing();
            Double cost  = price.getPrice(ages,inputDate,userSeats, choosenShowtime);
            Ticket ticket = new Ticket(customer, (cost), userSeats, choosenShowtime, Integer.valueOf(formattedDate));
            int confirmation = booking.confirmTicket(ticket);
            if (confirmation == 1) {
                System.out.println("Purchase successful!  ");
                ticketHandle.uploadTicket(ticket);
                MovieInfoManager mm = new MovieInfoManager(); 
                mm.updateTotalSales(movieName,numberSeats);
                break;
            } else if (confirmation == 0){
                continue;
            }
            else {
                Thread.sleep(1000);
                BookingDisplay.failExitDialouge();
                break;
            }

        }
        

    }

}
