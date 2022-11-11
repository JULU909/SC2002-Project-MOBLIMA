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
import View.LoginUI;
import View.SearchMovieUI;
import View.StaffLoginUI;
import entities.*;
import enums.AgeGroup;
import enums.Day;
import enums.UserType;
import model.*;

public class BookingController {
    public static void purchaseTicket(Customer customer) throws FileNotFoundException, IOException, InterruptedException, ParseException {
        // Connection to the managers and UI
        BookingDisplay booking = new BookingDisplay();
        ShowtimeManager Showtimes = new ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        int showtimesLength = Showtimes.getLength();
        TicketManager ticketHandle = new TicketManager("Moblima/src/Data/TicketsBooked.csv");

        // Getting user inputs.
        String cineplex = booking.askCineplex();
        String[] movies = Showtimes.getMovies(showtimesLength);
        int movieChoice = booking.askMovie(movies);
        ArrayList<Showtime> showtimes = Showtimes.getShowtimes(movies[movieChoice - 1], cineplex);
        LocalDate inputDate = booking.askDate();
        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("ddMMyy"));
        int showtimeChoice = booking.askTiming(showtimes);
        Showtime choosenShowtime = showtimes.get(showtimeChoice - 1);
        choosenShowtime.setDate(Integer.valueOf(formattedDate));
        choosenShowtime.setLayout();

       
        while (true){
            int numberSeats = booking.askTickets();
            choosenShowtime.printLayout();
            ArrayList<Seat> userSeats = booking.askSeats(numberSeats);
            double temp = 0;
            int check = 0;
            for (int i = 0 ; i <userSeats.size(); i++){
                SeatTypes s =  choosenShowtime.getSeatType(userSeats.get(i).getCol(),userSeats.get(i).getRow());
                if (s.equals(SeatTypes.DELUXE) || s.equals(SeatTypes.COUPLE)){
                 temp+=0.5;
                 
                }
                if (choosenShowtime.getSeatStatus(userSeats.get(i).getCol(),userSeats.get(i).getRow()).equals(SeatStatus.OCCUPIED)){
                 System.out.println("Seats you booked were booked , retry !");
                 check =1;
                 break;
                 
                }
             }
            if (check == 1){
                continue;
            }
            ArrayList<AgeGroup> ages = booking.getAges(numberSeats);
            
            
            
            if(choosenShowtime.getCinemaType().equals("GOLD")){
                temp+=0.5;
            }
            else if (choosenShowtime.getCinemaType().equals("PREMIUM")){
                temp+=1;
            }
            
            Pricing price = new Pricing();
            Double cost  = price.getPrice(ages,inputDate);
            Ticket ticket = new Ticket(customer, (cost+temp), userSeats, choosenShowtime, Integer.valueOf(formattedDate));
            int confirmation = booking.confirmTicket(ticket);
            if (confirmation == 1) {
                System.out.println("Purchase successful!  ");
                ticketHandle.uploadTicket(ticket);
                break;
            } else if (confirmation == 0){
                continue;
            }
            else {
                Thread.sleep(1000);
                booking.failExitDialouge();
                break;
            }

        }
        

    }

}
