package View;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import View.BookedHistoryUI;
import View.BookingDisplay;
import View.SearchMovieUI;
import View.StaffLoginUI;
import controllers.BookingController;
import controllers.BookingHistoryController;
import controllers.LoginController;
import controllers.ReviewMaker;
import controllers.SeatDetailController;
import entities.*;
import enums.AgeGroup;
import enums.Cinema;
import enums.SeatStatus;
import enums.SeatTypes;
import enums.UserType;
import model.*;

/**
 * Displays the Customer Menu UI that customers interact with to interact/book/search for movies and also to edit personal information
 * @author Kit Ye
 * @version 1.0
 * @since 1.0
 */

public class CustomerMenuUI {

    /**
     * This method displays the movie details, can search all movies, one movie, by top 5(by sales or avgRatings)
     * @param customer a customer object class to read information from
     * @throws InterruptedException
     * @throws IOException
     * @throws ParseException
     */
    public static void execute(Customer customer) throws IOException, InterruptedException, ParseException {
        BookingController bookingController = new BookingController();
        BookingHistoryController bookingHistoryController = new BookingHistoryController();
        int choice = 0;
        do {
            Scanner sc = new Scanner(System.in);
            choice = mainDisplayOptions();
            int selection=0;
            switch (choice) {
                case 1:
                    System.out.println(
                            "=====================================================================================\n");
                    System.out.println(
                            "                          Viewing Movie Details                                      \n");
                    System.out.println(
                            "=====================================================================================\n");
                    System.out.println(
                            "How would you like to view the movie details? \n1) View All Movie details\n2) View ONE Movie detail \n3) View top 5 movies by sales\n4) View top 5 movies by rating (by index, cannot be 0 or negative)\n");
                    System.out.println("Your Choice please: ");
                    try {
                        selection = sc.nextInt(); sc.nextLine();
                        if (selection < 0 || selection > 4) {throw new Exception();}
                    } catch (Exception e) {System.out.println("Error: invalid input!");}

                    MovieInfoManager m1 = new MovieInfoManager();
                    // MovieInfoManager m1 = new MovieInfoManager();
                    ArrayList<Movie> list = m1.readMovieCSV();

                    // m1.getDataAll();

                    if (selection == 1) {
                        m1.printAll(list);
                        // m1.PrintAll();
                    } else if (selection == 2) {
                        // Scanner sc = new Scanner(System.in);
                        System.out.println("Enter name of movie to display: ");
                        String title = sc.nextLine();
                        int index = MovieInfoManager.findMovieCSV(title, list);
                        if (index == -1) System.out.println("Movie not found! ");
                        // System.out.println("Select the index of the movie you want to display: ");
                        // selection = sc.nextInt(); sc.nextLine();
                        else m1.printOne(list, index);
                    }
                    else if (selection == 3) {
                        movieRanking(true);
                    //Top 5 based on avgRatings
                    } else if (selection == 4) {
                        movieRanking(false);
                    } 
                    break;

                case 2:
                    seatDetails();
                    break;

                case 3:
                    bookingController.purchaseTicket(customer);
                    break;
                case 4:
                    bookingHistoryController.getBookingHistory(customer);
                    break;
                case 5:
                    movieReview(customer);
                    break;
                case 6:
                    CustomerSettingsUI.settingsText(customer);
                    break;
                case 7:
                    exitDialouge();
                    LoginController.execute();
            }
            // choice
        } while (choice != 7);

        // PaymentUI pay = new PaymentUI();
        // pay.printPayment();

    }
/**
 * This method displays all the options available in the customer menu
 * @return the integer of the choice the customer has decided to access
 */
    public static int mainDisplayOptions() {

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out
                    .println("=====================================================================================\n" +
                            "=====================================================================================\n" +
                            "        Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)       \n" +
                            "=====================================================================================\n" +
                            "=====================================================================================\n" +
                            "(1) Search/List Movies\n\n" +
                            "(2) Check seat availability and selection of seat/s\n\n(3) Book and purchase ticket\n\n" +
                            "(4) View booking history\n\n" + "(5) Write a new Movie Review/See Movie's review(s)\n\n" +
                            "(6) User Settings\n\n(7) Exit\n\n \n\nChoices (1~7): ");

            try {
                System.out.print("\nWhat is your choice:  ");
                choice = sc.nextInt(); sc.nextLine();
                if (choice >= 1 && choice <= 7)
                    break;
                else
                    throw new Exception();
                // choice = Integer.parseInt(sc.next());
            } catch (Exception e) {
                    System.out.println("Please enter a valid option");
                }
            } while (choice < 1 || choice > 7);
        return choice;
    }

/**
 * This method displays the seat details of the movies to the user
 * @throws FileNotFoundException
 * @throws IOException
 * @throws InterruptedException
 */
    public static void seatDetails() throws FileNotFoundException, IOException, InterruptedException{
        SeatDetailController sdc = new SeatDetailController();
        sdc.getSeatDetails();
    }
    /**
     * This method enables customer to write a movieReview (string/int)
     * @param customer a customer object class to read information from
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void movieReview(Customer customer) throws FileNotFoundException, IOException {
        MovieInfoManager m1 = new MovieInfoManager();
        int op = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("=====================================================================================\n");
		System.out.println("                    Write a new Movie Review/See Movie's review(s)                   \n");
		System.out.println("=====================================================================================\n");
        do {
            System.out.println("Select an option:");
            System.out.println("1) Give a movie review\n\n2) See a movie's review(s)\n\n3) Return to main menu");
            op = sc.nextInt();
            if(op < 1 || op > 3)  {
                System.out.println("Invalid option, please try again\n\n");
            } else {
                switch(op){
                    case 1: ReviewMaker.newMovieReview(customer);
                            break;
                    case 2: ReviewMaker.viewMovieRating();
                            break;
                    case 3: return;      
                }
            }
        } while (op < 1 || op > 3);
    }

    /**
     * This method enables customer to search movies by movieRanking
     * @param byTicketSales boolean, true denoting by Ticket sales, false denoting by avgRating
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void movieRanking(boolean byTicketSales) throws FileNotFoundException, IOException {
        MovieInfoManager m1 = new MovieInfoManager();
        m1.rankByRatings(byTicketSales);
    }
/**
 * Displays the user settings for the user
 * @param user, a user object to ensure that a valid user object is accessing the settings
 */
    public static void admin(User user) {
        System.out.println("=====================================================================================\n");
        System.out.println("                          User Settings                                           \n");
        System.out.println("=====================================================================================\n");
        System.out.println(
                "Do you want to:\n1) Change username\n2) Change password (by index, cannot be 0 or negative)\n");
        System.out.println("Your Choice please: ");

    }
/**
 * Displays text when exiting MOBLIMA
 */
    public static void exitDialouge() {
        System.out.println("Thank you for using Moblima! Logging out...");

    }

}
