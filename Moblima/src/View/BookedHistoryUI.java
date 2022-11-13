package View;

import enums.*;
import model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import entities.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Represents the class of BookingHistory, which prints the UI for Booking history -related operations
 * @author Harish Vasanth
 * @version 1.0
 * @since 1.0
 */


public class BookedHistoryUI {
    Scanner sc = new Scanner(System.in);

    /** 
     * This method prints the UI to let the user check their previous ticket bookings
     * @return int, the choice made by the user
     */
    public int mainUI(){


        while(true){
    
        CineplexTypes [] list ;
        list = CineplexTypes.values();
        System.out.println( "To view a booked Ticket select one of these options : ");
        System.out.println( "----------------------------------------------------- ");
        System.out.println( "1 : List all previous bookings ");
        System.out.println( "2 : Search based on TicketID   ");
        System.out.println( "3 : Search based on Movie name ");
        System.out.println( "4 : Exit ");
        try{
        int userInput = sc.nextInt();
        return userInput;}
        catch (InputMismatchException e){
            System.out.println("Please enter a valid input!");
            System.out.println("---------------------------");
            String error = sc.next();
            }
        }
    }
    /** 
     * This method prints purchased tickets of the user based on the input.
     * If the ArrayList is empty it simply returns to the controller class.
     * @param userTickets, the list of all tickets purchased by the user
     */
    public void printAllTickets(ArrayList <Ticket> userTickets) throws IOException, InterruptedException{


        if (userTickets.size() == 0 ){
            noBookings(); return;
        }
        Scanner sc  = new Scanner(System.in);
        TicketManager tk = new TicketManager("Moblima/src/Data/TicketsBooked.csv");

        
        System.out.println("You have " + userTickets.size() + " tickets purchased : " );
        System.out.println("----------------------------" );
        Thread.sleep(1000);
        for (int i = 0 ; i < userTickets.size(); i++){
            System.out.println("Ticket " + (i+1) + "      :" );
            //Thread.sleep(1000);
            System.out.println( );
            System.out.println("Ticket ID       : " + userTickets.get(i).getID() );
            System.out.println("Cineplex        : " + userTickets.get(i).getShowtime().getCineplex() );
            System.out.println("Movie Time      : " + userTickets.get(i).getShowtime().getTime() );
            System.out.println("Movie Date      : " + userTickets.get(i).getDate() );
            System.out.println("Movie Name      : " + userTickets.get(i).getShowtime().getMovie() );
            
            System.out.println("Seats purchased : " + userTickets.get(i).getSeats().size()  );
            System.out.println("Total Cost      : " + userTickets.get(i).getPrice() );
            System.out.println("-------------" );
        }

        
    }
    /** 
     * This method asks the user whether they want to view the seat details of any previewed 
     * tickets and accordingly calls another function to print the specific ticket details.
     * 
     * @param userTickets, the list of all tickets booked by the user previously
     */
    public void individualTicketmenu(ArrayList <entities.Ticket> userTickets) throws NumberFormatException, IOException, InterruptedException{
        if (userTickets.size() == 0 ){
            return;
        }
        while(true){
        System.out.println("To view a specific booking's seat details further, enter Ticket number { 1 - "+ userTickets.size() + " } or enter 0 to exit :" );
        System.out.println("----------------------------------------------------------------------------------------------------  " );
        String temp = sc.next();
        if (Integer.valueOf(temp) == 0 ){
            return;
        }
        if ( Integer.valueOf(temp) <= userTickets.size()){
            printTicketSeats((Integer.valueOf(temp)-1), userTickets);
        }
        }
    }
    /** 
     * This method prints a error message under the circumstance that no booking has been found.
     */
    public void noBookings(){
        System.out.println("No corresponding booking found!" );
        System.out.println("" );
        System.out.println("-------------------------------");
        }

  /** 
     * * This method prints the seat details along with the ticket details of a specific purchased tickets
     * It collects the ticket data from the ArrayList input and the specific index mentioned by the user of 
     * the ticket.
     * 
     * @param index, the index of the seat specified by the user
     * @param userTickets, an array list of tickets booked by the user
     */
    public void printTicketSeats(int index ,ArrayList <Ticket> userTickets) throws IOException, InterruptedException{

        int i = index;
        Ticket mainTicket = userTickets.get(index);

        System.out.println("Ticket " + (i+1) + "      :" );
        Thread.sleep(1000);
        System.out.println( );
        System.out.println("Ticket ID       : " + userTickets.get(i).getID() );
        System.out.println("Movie Name      : " + userTickets.get(i).getShowtime().getMovie() );
        System.out.println("Seats purchased : " + userTickets.get(i).getSeats().size()  );
        System.out.println("---------------" );
        ArrayList <Seat> seats =  userTickets.get(i).getSeats();
        for (int x = 0 ; x < seats.size() ; x++){
            char row = (char) seats.get(x).getRow();
            int col = seats.get(x).getCol();
            System.out.println("Seat  "+ (x+1) +"       : " + row + col);
            System.out.println(userTickets.get(i).getShowtime().getSeatType(col,row));

        }
        System.out.println("---------------" );

        System.out.println("Total Cost      : " + userTickets.get(i).getPrice() );;

    }
 /** 
   * This method asks the user for a specific ticket ID.
   * @return String
   */
    public String getTicketID() throws IOException, InterruptedException{

        System.out.println("Please enter your Ticket ID :     " );
       
        System.out.println("-----------------------------");
        String input = sc.next();
        return input;

    }
    /** 
     * This method prints the the ticket details of a specific purchased ticket
     * It collects the ticket data from the ArrayList input and the specific index mentioned by the user of 
     * the ticket.
     * 
     * @param index, the index of the ticket mentioned
     * @param userTickets, an array list of tickets previously bought by the user
     */
    public void printTicketIndex(int index ,ArrayList <Ticket> userTickets){

        int i = index;
        System.out.println("Ticket " + (i+1) + "      :" );
            //Thread.sleep(1000);
            System.out.println( );
            System.out.println("Ticket ID       : " + userTickets.get(i).getID() );
            System.out.println("Cineplex        : " + userTickets.get(i).getShowtime().getCineplex() );
            System.out.println("Movie date      : " + userTickets.get(i).getShowtime().getTime() );
            System.out.println("Movie Time      : " + userTickets.get(i).getDate() );
            System.out.println("Movie Name      : " + userTickets.get(i).getShowtime().getMovie() );
            System.out.println("Total Cost      : " + userTickets.get(i).getPrice() );
            System.out.println("Seats purchased : " + userTickets.get(i).getSeats().size()  );
            System.out.println("-------------" );
            System.out.println("Press any key to exit." );
            System.out.println("" );
            System.out.println("------------------------");
            String input = sc.next();
    }
    
    /** 
    * This method prints the the ticket details of a specific purchased ticket based on the ticket ID
    * It collects the ticket data from the ArrayList input and the specific ticketID mentioned by the user of 
    * the ticket. If no booking exists it prints an message to indicate that.
    * @param idString, the id number of the string
    * @param userTickets, the list of tickets bought
    */
    public void printByTicketID(String idString ,ArrayList <Ticket> userTickets) throws InterruptedException{

        int check = 0;
        for (int i = 0 ; i < userTickets.size(); i++){
            Ticket temp = userTickets.get(i);
            if (temp.getID().equals(idString)){
                System.out.println( );
                System.out.println("Ticket ID       : " + userTickets.get(i).getID() );
                System.out.println("Cineplex        : " + userTickets.get(i).getShowtime().getCineplex() );
                System.out.println("Movie date      : " + userTickets.get(i).getShowtime().getTime() );
                System.out.println("Movie Time      : " + userTickets.get(i).getDate() );
                System.out.println("Movie Name      : " + userTickets.get(i).getShowtime().getMovie() );
                System.out.println("Total Cost      : " + userTickets.get(i).getPrice() );
                System.out.println("Seats purchased : " + userTickets.get(i).getSeats().size()  );
                System.out.println("-------------" );
                check =1;
                System.out.println("" );

                System.out.println("Press any key to exit." );
                System.out.println("" );
                System.out.println("------------------------");
                String input = sc.next();
                break;
            }

            




        }

        if (check == 0){
            System.out.println("No such booking exists. Will exit to previous menu" );
            System.out.println("" );
            System.out.println("----------------------------------------------------------------");
            Thread.sleep(1000);
            


            return;
        }

    }
   /** 
    * This method asks the user for a movie name they would like to search and returns it.
    * 
    * @return String, the name of the movie
    */
    public  String getMovieName() throws InterruptedException{

        System.out.println("Enter the movie name you would like to search" );
        System.out.println("-----------------------------");
        String input = sc.next();
        return input;
    }

}
