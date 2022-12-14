package View;
import enums.*;

import java.util.ArrayList; // import the ArrayList class

import java.util.Arrays;
import java.util.Scanner;

import entities.Customer;
import entities.Movie;
import entities.Seat;
import entities.Showtime;
import entities.Ticket;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
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
    /** 
     * This method prints the UI to get the Cineplex the user wants to watch the movie at
     * After the cineplex is obtained, it will be returned as a String
     * @return String
     */
public String askCineplex(){

    Scanner sc = new Scanner(System.in);
    CineplexTypes [] list ;
    list = CineplexTypes.values();
    int choice;
    while(true){
    try{
    System.out.println( "Please select cineplex : ");
    System.out.println( "------------------------ ");
    System.out.println(0+ " : " + "Exit");

    for (int i = 0 ; i < list.length; i++){
        System.out.println(i+1 + " : " + list[i]);
    }
    
    
    
    
    choice = sc.nextInt();
    if (choice > list.length || choice < 0  ){
        System.out.println("Enter a valid input! ");
        continue;
    }
    }catch (InputMismatchException e){
    String error = sc.next();
    System.out.println("Enter a valid input! ");
    continue;
    
    }
    if (choice == 0){
        return "exit";
    }
    return (list[choice-1]).name();

}
    
}
/** 
 * This method prints the UI to get the Seats the user wants to book at the movie.
 * After the Seats are obtained, it will be returned as a ArrayList of the item Seat
 * @param number, the number of seats to book
 * @return ArrayList of seat
 */
public ArrayList askSeats(int number){

    Scanner sc = new Scanner(System.in);
    ArrayList <Seat> seats = new ArrayList<Seat>();
    
    int check =0;
    while (true){
    try {
    System.out.println( "Please Enter your seat numbers (EG : A1) : ");
    System.out.println( "------------------------------------------- ");
    check = 0;
    for (int i = 0 ; i < number; i++){
        String seat = sc.nextLine();
        Seat TempSeat = new Seat();
        String strNew = seat.substring(1, seat.length());
        if (Integer.valueOf(seat.charAt(0))>91 || Integer.valueOf(seat.charAt(0)) <65 || Integer.valueOf(strNew) > 28 || Integer.valueOf(strNew) <= 0 ){
            System.out.println("Invalid format please enter a seat number properly!");
            check =1;
            break;
        }
        TempSeat.Seat(null, null, seat.charAt(0), Integer.valueOf(strNew));
        seats.add(TempSeat);
    }
    if(check ==1){
        continue;
    }
        break;}
    catch (NumberFormatException e){
        System.out.println("Enter the a proper seat number please!");
        continue;
    }
    catch (InputMismatchException e){
        System.out.println("Enter the a proper seat number please!");
        continue;
    }
    }
    return seats;
}
/** 
 * This method prints the UI to get the number of Seats the user wants to book at the movie.
 * After the number is obtained, it will be as a integer.
 * @return int
 */
public int askTickets(){

    Scanner sc = new Scanner(System.in);
    
    while (true){
    try {System.out.println( "Please Enter number of seats you wanna book : ");
    System.out.println( "--------------------------------------------- ");
    int choice = 0 ;
    choice = sc.nextInt();
    if (choice <= 0){
        System.out.println("Please enter a valid input!");

    }
    return choice;}
    catch (InputMismatchException e){
        System.out.println("Please enter a number!");
        String error = sc.next();
        continue;
    }
    }
    
}

/** 
 * This method prints the UI to get the specific movie the user wants to book.
 * After the movie is obtained, it will returned as a integer.
 * @param movies, the list of movies available
 * @return int, the choice of movie made
 * 
 */

public int askMovie(ArrayList <Movie> movies){
 
    Scanner sc = new Scanner(System.in);
    int count= 0;
    System.out.println("Please select the number corresponding to a movie : ");
    System.out.println( "-------------------------------------------------- ");
    while (true){
        try{
    
     for(int i = 0 ; i < movies.size(); i++){
            System.out.println(count+1 + " :  " + movies.get(i).getTitle());
            count++;
        }
        
    
    int choice = 0 ;
    choice = sc.nextInt();
    if (choice <= 0 ||choice > count){
        System.out.println("Enter a valid input! ");
        continue;
    }
    return (choice-1);
    }catch (InputMismatchException e ) {
    System.out.println("Enter a valid input! ");
    String error = sc.next(); // catch the enter;
    continue;
    }
    }
}

/**
 * This method prints the UI to get the specific showtime the user wants to book.
 * After the movie is obtained, it will returned as a integer. If noshowtime is available,
 * it prints an indicative message for that.
 * @param showtimes, the list of showtimes
 * @return int, the choice of which showtime made
 * @throws InterruptedException
 */
public int askTiming(ArrayList <Showtime>showtimes) throws InterruptedException{

    Scanner sc = new Scanner(System.in);
    int count= 0;
    if(showtimes.size() == 0){
        System.out.println( "No showtime for this specific date! ");
        System.out.println( "------------------------------------------ ");
        Thread.sleep(1000);
        System.out.println( "Enter any key to exit ");
        sc.next();
        return -1;
        
    }
    while(true){
    try {System.out.println("Please select the showtime of your liking! : ");
    System.out.println( "------------------------------------------ ");
    for(int i =0 ; i < showtimes.size() ; i++){
        Showtime temp = showtimes.get(i);
        System.out.println(i+1 + " :  " + temp.getTime() + " " + temp.getCinemaType() );
        count++;
    }
    int choice = 0 ;
    choice = sc.nextInt();
    if ( choice <=0 ||choice > showtimes.size()){
        System.out.println("Enter a valid input! ");

        continue;
    }
    return choice;
    }catch (InputMismatchException e ) {
        System.out.println("Enter a valid input! ");
        String error = sc.next(); // catch the enter;
        continue;
        }
    }
}
	/** 
	* This method prints the UI to get the specific date the user wants to book their movie.
	* After the date is obtained, it will returned as a Date object. 
	* 
	*  @return Date, the date obtained
	*/
public LocalDate askDate(){

        Scanner sc = new Scanner(System.in);
        LocalDate date =  LocalDate.now();
        Calendar cal = Calendar.getInstance();
        LocalDate [] bookingDates = new LocalDate[7];
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        DateTimeFormatter dayFormatters = DateTimeFormatter.ofPattern("EEEE");
        while(true){
        try {System.out.println("Please select the Date of your liking! : ");
        System.out.println( "--------------------------------------- ");
        for (int i = 0 ; i <7 ; i ++){
            bookingDates[i] = LocalDate.now().plusDays(i);
            String modifiedDate = bookingDates[i].format(formatters);
            String day = bookingDates[i].format(dayFormatters);
            System.out.println((i+1)+ " : "+ modifiedDate + " " + day);
            
        }

        int choice = sc.nextInt();
        if(choice <= 0 || choice >= 8){
            System.out.println("Enter a valid input! ");
            continue;
        }
        return  bookingDates[choice-1];
        }catch (InputMismatchException e ) {
            System.out.println("Enter a valid input! ");
            String error = sc.next(); // catch the enter;
            continue;
            }

    }
	
}


/** 
* This method prints the UI to ask the user whether they wanna confirm the ticket, change seats or decline purchase,
* The payment is assumed to be done no matter what the scenario. Returns their option to the controller class
* @param ticket, the ticket to be confirmed
* @return int, the choice made when confirming the booking
*/
public int confirmTicket(Ticket ticket) throws InterruptedException{

    Scanner sc = new Scanner(System.in);
    int choice = 0;
    ArrayList <Seat> bookedSeats = ticket.getSeats();
    Double price = ticket.getPrice();
    Customer customer = ticket.getCustomer();
    Showtime bookedShowtime = ticket.getShowtime();
    System.out.println("These are the details of your Booking : ");
    System.out.println("--------------------------------------- ");
    Thread.sleep(1000);
    String date = String.valueOf(ticket.getDate());
    System.out.println("Show time booked : " + bookedShowtime.getTime());
    System.out.println("Date booked      : " + date.substring(0,2) + "-" + date.substring(2,4) +'-' + date.substring(4,6));
    System.out.println("Total cost       : " +"$ " +ticket.getPrice());
    System.out.println("Number of seats  : " + bookedSeats.size());
    Thread.sleep(1000);
    System.out.println();
    for (int i = 0 ;  i< bookedSeats.size() ; i++ ){
        System.out.println("Seat " + (i+1) + " : " + (char)(bookedSeats.get(i).getRow()) + bookedSeats.get(i).getCol());
    }

    System.out.println("Ticked ID       : " + ticket.getID());
    System.out.println("User            : " + customer.getUsername());
   
    while(true){
    try {  
    System.out.println("Confirmation dialouge : ");
    System.out.println("------------------------");
    System.out.println("0 : Change seats     :     ");
    System.out.println("1 : Confirm purchase :     ");
    System.out.println("2 : Decline purhcase :    [Exit to main menu] ");
    choice = sc.nextInt();
    return choice;}
    catch(InputMismatchException e){
        System.out.println("Please enter a valid input ");
        String temp = sc.next();
        continue;
    }
      
}
    
}
/** 
 * This method prints the UI indicate the tickets have been booked successfully.
 */
public static void successExitDialouge() {

    System.out.println("Your Ticket has been booked, Have a pleasant day!.");
    return;

}
/** 
* This method collects the tally of the different age groups the purchase of tickets is for and it returns
* and array of AgeGroup objects corresponding to the tally.
* @param numSeats, the number of seats to book
* @return ArrayList of age groups
*/
public static ArrayList <AgeGroup> getAges(int numSeats) {

    ArrayList<AgeGroup> ages= new ArrayList<AgeGroup>();
    System.out.println("Enter number of movie Viewers per age group {child , adult & senior} : ");
    System.out.println("-----------------------------------------------------------------------");
    System.out.println("");
    while(true){
    try{
    ages = new ArrayList<AgeGroup>();
    System.out.println("Child : ");
    Scanner sc = new Scanner(System.in);
    int count = 0; 
    int answer  = sc.nextInt();
    count+= answer;
    for (int i = 0 ; i < answer; i++){
        ages.add(AgeGroup.CHILD);
    }
    System.out.println("Adult : ");
    answer  = sc.nextInt();
    count+= answer;
    for (int i = 0 ; i < answer; i++){
        ages.add(AgeGroup.ADULT);
    }
    System.out.println("Senior : ");
    answer  = sc.nextInt();
    count+= answer;
    for (int i = 0 ; i < answer; i++){
        ages.add(AgeGroup.SENIOR);
    }
    if (count != numSeats){
        System.out.println("The sum of moviegoers doesnt add up ! re-enter your values : ");

    }
    else{
        break;
    }
    }
    catch(InputMismatchException e){
        System.out.println("That is not an integer, please try again." );
    }
    }
    return ages;
}

/** 
 * This method prints the indicator that the tickets booked have been cancelled.
 */
public static void failExitDialouge() {
 
    System.out.println("Your Ticket has been removed, Have a pleasant day!.");
    return;

}
/** 
 * This method prints a buffer so the user can read the message above before the main UI appears and asks for an
 * input to exit.
 */
public static void printExitMessage(){

    Scanner sc = new Scanner(System.in);
    System.out.println("Press any key to exit");
    sc.next();
    return;

}



}