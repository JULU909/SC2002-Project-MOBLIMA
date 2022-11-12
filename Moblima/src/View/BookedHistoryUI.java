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

public class BookedHistoryUI {
    Scanner sc = new Scanner(System.in);

    public int mainUI(){

        /** 
         * This method prints the UI to get the Cineplex the user wants to watch the movie at
         * After the cineplex is obtained, it will be returned as a String
         * @return String
         */
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

        }
        }
    }

    public  void printAllTickets(ArrayList <Ticket> userTickets) throws IOException, InterruptedException{

        /** 
         * This method prints the UI to get the Cineplex the user wants to watch the movie at
         * After the cineplex is obtained, it will be returned as a String
         * @return String
         */
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

    public  void individualTicketmenu(ArrayList <entities.Ticket> userTickets) throws NumberFormatException, IOException, InterruptedException{
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

    public void noBookings(){
        System.out.println("No corresponding booking found!" );
        System.out.println("" );
        System.out.println("-------------------------------");
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
            System.out.println(userTickets.get(i).getShowtime().getSeatType(col,row));

        }
        System.out.println("---------------" );

        System.out.println("Total Cost      : " + userTickets.get(i).getPrice() );;


    }

    public  String getTicketID() throws IOException, InterruptedException{

        System.out.println("Please enter your Ticket ID :     " );
       
        System.out.println("-----------------------------");
        String input = sc.next();
        return input;

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
            System.out.println("Total Cost      : " + userTickets.get(i).getPrice() );
            System.out.println("Seats purchased : " + userTickets.get(i).getSeats().size()  );
            System.out.println("-------------" );
            System.out.println("Press any key to exit." );
            System.out.println("" );
            System.out.println("------------------------");
            String input = sc.next();
    }

    public  void printByTicketID(String idString ,ArrayList <Ticket> userTickets) throws InterruptedException{
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

    public  String getMovieName() throws InterruptedException{

        System.out.println("Enter the movie name you would like to search" );
        System.out.println("-----------------------------");
        String input = sc.next();
        return input;
    }

}
