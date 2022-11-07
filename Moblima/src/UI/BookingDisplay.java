package UI;
import enums.*;
import system.Cineplex;
import java.util.ArrayList; // import the ArrayList class

import java.util.Arrays;
import java.util.Scanner;
import system.Seat;
import system.Showtime;
import system.Ticket;
import database.User;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
public class BookingDisplay {

public String askCineplex(){
    Scanner sc = new Scanner(System.in);
    CineplexTypes [] list ;
    list = CineplexTypes.values();
    System.out.println( "Please select cineplex : ");
    System.out.println( "------------------------ ");
    for (int i = 0 ; i < list.length; i++){
        System.out.println(i+1 + " : " + list[i]);
    }

    int choice;
    choice = sc.nextInt();
    return (list[choice-1]).name();
}

public ArrayList askSeats(int number){
    
    Scanner sc = new Scanner(System.in);
    ArrayList <Seat> seats = new ArrayList<Seat>();
    System.out.println( "Please Enter your seat numbers (EG : A1) : ");
    System.out.println( "------------------------------------------- ");
    for (int i = 0 ; i < number; i++){
        String seat = sc.nextLine();
        Seat TempSeat = new Seat();
        String strNew = seat.substring(1, seat.length());
        
        TempSeat.Seat(null, null, seat.charAt(0), Integer.valueOf(strNew));
        seats.add(TempSeat);
    }
    
    return seats;
}

public int askTickets(){
    Scanner sc = new Scanner(System.in);
    
    
    System.out.println( "Please Enter number of seats you wanna book : ");
    System.out.println( "--------------------------------------------- ");
    int choice = 0 ;
    choice = sc.nextInt();
    return choice;
}


public int askMovie(String [] movies){
    Scanner sc = new Scanner(System.in);
    int count= 0;
    System.out.println("Please select the number corresponding to a movie : ");
    System.out.println( "-------------------------------------------------- ");
    while(movies[count] != null){
        System.out.println(count+1 + " :  " + movies[count]);
        count++;
    }
    int choice = 0 ;
    choice = sc.nextInt();
    return choice;
}

public int askTiming(ArrayList <Showtime>showtimes){
    Scanner sc = new Scanner(System.in);
    int count= 0;
    System.out.println("Please select the showtime of your liking! : ");
    System.out.println( "------------------------------------------ ");
    for(int i =0 ; i < showtimes.size() ; i++){
        Showtime temp = showtimes.get(i);
        System.out.println(i+1 + " :  " + temp.getTime());
        count++;
    }
    int choice = 0 ;
    choice = sc.nextInt();
    return choice;
}

public LocalDate askDate(){
        Scanner sc = new Scanner(System.in);
        LocalDate date =  LocalDate.now();
        Calendar cal = Calendar.getInstance();
        LocalDate [] bookingDates = new LocalDate[7];
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        DateTimeFormatter dayFormatters = DateTimeFormatter.ofPattern("EEEE");
        System.out.println("Please select the Date of your liking! : ");
        System.out.println( "--------------------------------------- ");
        for (int i = 0 ; i <7 ; i ++){
            bookingDates[i] = LocalDate.now().plusDays(i);
            String modifiedDate = bookingDates[i].format(formatters);
            String day = bookingDates[i].format(dayFormatters);
            System.out.println((i+1)+ " : "+ modifiedDate + " " + day);
            
        }
        
        
       
        

		int choice = sc.nextInt();
        return  bookingDates[choice-1];
		
}



public int confirmTicket(Ticket ticket) throws InterruptedException{
    Scanner sc = new Scanner(System.in);
    int choice = 0;
    ArrayList <Seat> bookedSeats = ticket.getSeats();
    int price = ticket.getPrice();
    User user = ticket.getUser();
    Showtime bookedShowtime = ticket.getShowtime();
    System.out.println("These are the details of your Booking : ");
    System.out.println("--------------------------------------- ");
    Thread.sleep(1000);
    System.out.println("Show time booked : " + bookedShowtime.getTime());
    System.out.println("Date booked      : " + ticket.getDate());
    System.out.println("Number of seats  : " + bookedSeats.size());
    Thread.sleep(1000);
    System.out.println();
    for (int i = 0 ;  i< bookedSeats.size() ; i++ ){
        System.out.println("Seat " + (i+1) + " : " + (char)(bookedSeats.get(i).getRow()) + bookedSeats.get(i).getCol());
    }

    System.out.println("Ticked ID       : " + ticket.getID());
    System.out.println("User            : " + user.getUsername());
    System.out.println("To confirm purchase type 1 : ");
    choice = sc.nextInt();
    return choice;
}

}