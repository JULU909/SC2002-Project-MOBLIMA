package UI;
import enums.*;
import system.Cineplex;
import java.util.ArrayList; // import the ArrayList class

import java.util.Arrays;
import java.util.Scanner;
import system.Seat;
import system.Showtime;
import system.Ticket;
import database.Customer;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Represents the class of BookingDisplay, which prints the UI for Booking ticket -related operations
 * @author Harish Vasanth
 * @version 1.0
 * @since 1.0
 */


public class BookingDisplay {

public String askCineplex(){

    /** 
     * This method prints the UI to get the Cineplex the user wants to watch the movie at
     * After the cineplex is obtained, it will be returned as a String
     * @return String
     */

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
    /** 
     * This method prints the UI to get the Seats the user wants to book at the movie.
     * After the Seats are obtained, it will be returned as a ArrayList of the item Seat
     * @return ArrayList <Seat>
     */
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

    /** 
     * This method prints the UI to get the number of Seats the user wants to book at the movie.
     * After the number is obtained, it will be as a integer.
     * @return int
     */
    Scanner sc = new Scanner(System.in);
    
    
    System.out.println( "Please Enter number of seats you wanna book : ");
    System.out.println( "--------------------------------------------- ");
    int choice = 0 ;
    choice = sc.nextInt();
    return choice;
}


public int askMovie(String [] movies){
    /** 
     * This method prints the UI to get the specific movie the user wants to book.
     * After the movie is obtained, it will returned as a integer.
     * @return int
     * @param String
     */

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
        System.out.println(i+1 + " :  " + temp.getTime() + " " + temp.getCinemaType(null) );
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
    Customer customer = ticket.getCustomer();
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
    System.out.println("User            : " + customer.getUsername());
    
    System.out.println("Confirmation dialouge : ");
    System.out.println("------------------------");
    System.out.println("0 : Change seats     :     ");
    System.out.println("1 : Confirm purchase :     ");
    System.out.println("2 : Decline purhcase :    [Exit to main menu] ");
    choice = sc.nextInt();
    return choice;
      
    
    
}

public static void successExitDialouge() {
    System.out.println("Your Ticket has been booked, Have a pleasant day!.");
    return;

}

public static void getAges(int numSeats) {
    System.out.println("Enter number of movie Viewers per age group : ");
    System.out.println("----------------------------------------------");
    System.out.println("");
    while(true){
    System.out.println("Child : ");
    Scanner sc = new Scanner(System.in);
    int count = 0; 
    count+= sc.nextInt();
    System.out.println("Adult : ");
    count+= sc.nextInt();
    System.out.println("Senior : ");
    count+= sc.nextInt();
    if (count == numSeats){
        System.out.println("The sum of moviegoers doesnt add up ! re-enter your values : ");

    }
    else{
        break;
    }
    }

}


public static void failExitDialouge() {
    System.out.println("Your Ticket has been removed, Have a pleasant day!.");
    return;

}

public void cancelBooking() {


}

}