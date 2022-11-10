package UI;

import enums.*;
import system.Cineplex;
import java.util.ArrayList; // import the ArrayList class

import java.util.Arrays;
import java.util.Scanner;
import system.Seat;
import system.Showtime;
import system.Ticket;
import database.TicketManager;
import database.User;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class BookedHistoryUI {
    Scanner sc = new Scanner(System.in);

    public int mainUI(){

        /** 
         * This method prints the UI to get the Cineplex the user wants to watch the movie at
         * After the cineplex is obtained, it will be returned as a String
         * @return String
         */
    
        CineplexTypes [] list ;
        list = CineplexTypes.values();
        System.out.println( "To view a booked Ticket select one of these options : ");
        System.out.println( "----------------------------------------------------- ");
        System.out.println( "1 : List all previous bookings ");
        System.out.println( "2 : Search based on TicketID   ");
        System.out.println( "3 : Search based on Movie name ");
        int userInput = sc.nextInt();
        return userInput;
    }

    public  void printAllTickets(ArrayList <Ticket> userTickets) throws IOException, InterruptedException{

        /** 
         * This method prints the UI to get the Cineplex the user wants to watch the movie at
         * After the cineplex is obtained, it will be returned as a String
         * @return String
         */
        if (userTickets.size() == 0 ){
            noBookings();
        }
        Scanner sc  = new Scanner(System.in);
        TicketManager tk = new TicketManager("Moblima/src/Data/TicketsBooked.csv");

        
        System.out.println("You have " + userTickets.size() + " tickets purchased : " );
        System.out.println("----------------------------" );

        for (int i = 0 ; i < userTickets.size(); i++){
            System.out.println("Ticket " + (i+1) + "      :" );
            //Thread.sleep(1000);
            System.out.println( );
            System.out.println("Ticket ID       : " + userTickets.get(i).getID() );
            System.out.println("Cineplex        : " + userTickets.get(i).getShowtime().getCineplex() );
            System.out.println("Movie date      : " + userTickets.get(i).getShowtime().getTime() );
            System.out.println("Movie Time      : " + userTickets.get(i).getDate() );
            System.out.println("Movie Name      : " + userTickets.get(i).getShowtime().getMovie() );
            System.out.println("Seats purchased : " + userTickets.get(i).getSeats().size()  );
            System.out.println("-------------" );



        }

        
    }

    public  void individualTicketmenu(ArrayList <Ticket> userTickets) throws NumberFormatException, IOException, InterruptedException{
        System.out.println("To view a specific booking further, enter Ticket number { 1 - "+ userTickets.size() + " } or enter 0 to exit :" );
        System.out.println("--------------------------------------------------------------------------  " );
        String temp = sc.next();
        if (Integer.valueOf(temp) == 0 ){
            return;
        }
        if ( Integer.valueOf(temp) < userTickets.size()){
            printTicketSeats((Integer.valueOf(temp)-1), userTickets);
        }
    }

    public void noBookings(){
        System.out.println("You have yet to make a Booking with us !. Press any key to Exit" );
        System.out.println("System will exit to home page on default in 10 Seconds. " );
        System.out.println("----------------------------------------------------------------");
        long start = System.currentTimeMillis();
        long end = start + 10 * 1000;
        String temp;
        while (System.currentTimeMillis() < end) {
             temp = sc.next();
             break;

        }




    }

    public  void printTicketSeats(int index ,ArrayList <Ticket> userTickets) throws IOException, InterruptedException{
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
           
        }
        System.out.println("---------------" );


        System.out.println("Total Price : " + userTickets.get(i).getPrice()+ "$");


    }

    public  String getTicketID() throws IOException, InterruptedException{

        System.out.println("Please enter your Ticket ID :     {Press 0 to go back}" );
       
        System.out.println("--------------------------------------------------------");
        String input = sc.next();
        if (input.equals("0")){
            return "null";
        }
        else  return input;

    }

    public  void printTicketIndex(int index ,ArrayList <Ticket> userTickets){
        int i = index;
        System.out.println("Ticket " + (i+1) + "      :" );
            //Thread.sleep(1000);
            System.out.println( );
            System.out.println("Ticket ID       : " + userTickets.get(i).getID() );
            System.out.println("Cineplex        : " + userTickets.get(i).getShowtime().getCineplex() );
            System.out.println("Movie date      : " + userTickets.get(i).getShowtime().getTime() );
            System.out.println("Movie Time      : " + userTickets.get(i).getDate() );
            System.out.println("Movie Name      : " + userTickets.get(i).getShowtime().getMovie() );
            System.out.println("Seats purchased : " + userTickets.get(i).getSeats().size()  );
            System.out.println("-------------" );
    }

    public void printTicket(Ticket ticket ) throws InterruptedException{
        
        System.out.println("Your ticket details  : " );
        System.out.println("---------------------- " );
        Thread.sleep(1000);
        System.out.println( );
        System.out.println("Ticket ID       : " +ticket.getID() );
        System.out.println("Cineplex        : " + ticket.getShowtime().getCineplex() );
        System.out.println("Movie date      : " + ticket.getShowtime().getTime() );
        System.out.println("Movie Time      : " + ticket.getDate() );
        System.out.println("Movie Name      : " + ticket.getShowtime().getMovie() );
        System.out.println("Seats purchased : " + ticket.getSeats().size()  );
        System.out.println("-------------" );
        System.out.println("Press any key to return ! " );
        sc.next();

    }

}
