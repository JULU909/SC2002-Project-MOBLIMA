package system;
import java.io.FileNotFoundException;
import java.io.IOException;

import UI.BookingDisplay;
import enums.*;
import database.Datamanager;
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;

import system.Ticket;
import database.ShowtimeManager;
import database.TicketManager;

// USed to test the code/// remove before submission


public class HarishTesting {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException  {
        Scanner sc  = new Scanner(System.in);
        TicketManager tk = new TicketManager("Moblima/src/Data/TicketsBooked.csv");

        ArrayList <Ticket> userTickets = tk.getUserTickets("John");
        System.out.println("You have " + userTickets.size() + " tickets purchased : " );
        System.out.println("----------------------------" );

        for (int i = 0 ; i < userTickets.size(); i++){
            System.out.println("Ticket " + i + "      :" );
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

}
