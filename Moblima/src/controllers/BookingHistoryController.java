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

/**	Control Class that manages the History of the bookings
 * @author 
 * @version 1.0
 */


public class BookingHistoryController {
    
    /**
     * This method displays the History of all the booked Tickets, by id or by ALL tickets
     * @param customer customer object with attributes such as age, ageGroup, mobileNumber, emailAddress, movieHistory
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getBookingHistory(Customer customer) throws IOException, InterruptedException {
        BookedHistoryUI bt = new BookedHistoryUI();
        TicketManager tk = new TicketManager("Moblima/src/Data/TicketsBooked.csv");
        ArrayList <Ticket> userTickets = tk.getUserTickets(customer.getUsername());
        while (true){
        int input  = bt.mainUI();
        switch (input) {
            case 1:
                bt.printAllTickets(userTickets);
                bt.individualTicketmenu(userTickets);
                break;
            case 2: 
                String id = bt.getTicketID();
                bt.printByTicketID(id, userTickets);
                break;
            case 3 :
                String movie = bt.getMovieName();
                ArrayList<Ticket>matchingTickets = tk.searchByMovie(userTickets, movie);
                bt.printAllTickets(matchingTickets);
                break;
            
            case 4: 
                return;

            default : 
                Thread.sleep(1000);
                System.out.println("Enter a number within the options");
                Thread.sleep(1000);
                break;
            }
        }
    }
}
