import UI.BookedHistoryUI;
import UI.BookedTicketsUI;
import UI.BookingDisplay;
import UI.SearchMovieUI;
import UI.StaffLoginUI;
import UI.LoginUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import system.*;
import database.*;
import enums.Day;
import enums.UserType;
import system.*;

public class Moblima {

    public static void main(String[] args) throws IOException, InterruptedException {

        do {

            Scanner sc = new Scanner(System.in);
            int choice = mainDisplayOptions();
            int selection=0;
            switch (choice) {
                case 1:
                    System.out.println(
                            "=====================================================================================\n");
                    System.out.println(
                            "                          Searching Movies                                           \n");
                    System.out.println(
                            "=====================================================================================\n");
                    System.out.println(
                            "Do you want to Display All Movies or Display one Movie? \n1) Display All Movies\n2) Display ONE movie (by index, cannot be 0 or negative)\n3) Top 5 movies based on ticket sales\n4) Top 5 movies based on average ratings\n");
                    System.out.println("Your Choice please: ");

                    // new SearchMovieUI().DisplayAll();

                    try {
                        selection = sc.nextInt(); sc.nextLine();
                        if (selection < 0 || selection > 2) throw new Exception();
                    } catch (Exception e) {System.out.println("Error: invalid input!");}
                    //Display all movies
                    if (selection == 1) {
                        new SearchMovieUI().DisplayAll();
                    //Display one movie
                    } else if (selection == 2) {
                        System.out.println("\nSelect the index of the movie you want to display: ");
                        selection = sc.nextInt();
                        new SearchMovieUI().DisplayOne(selection);
                    //Top 5 baased on ticketSales
                    } else if (selection == 3) {
                        movieRanking(true);
                    //Top 5 based on avgRatings
                    } else if (selection == 4) {
                        movieRanking(false);
                    } else {
                        break;
                    }
                    // new SearchMovieUI().DisplayOne(selection); // selection refers to the index
                    break;

                case 2:
                    System.out.println(
                            "=====================================================================================\n");
                    System.out.println(
                            "                          Viewing Movie Details                                      \n");
                    System.out.println(
                            "=====================================================================================\n");
                    System.out.println(
                            "Do you want to View ALL Movie details or View ONE Movie detail? \n1) View All Movie details\n2) View ONE Movie detail (by index, cannot be 0 or negative)\n");
                    System.out.println("Your Choice please: ");
                    try {
                        selection = sc.nextInt(); sc.nextLine();
                        if (selection < 0 || selection > 2) {sc.close(); throw new Exception();}
                    } catch (Exception e) {System.out.println("Error: invalid input!");}

                    MovieInfoManager2 m1 = new MovieInfoManager2();
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
                        int index = m1.findMovieCSV(title, list);
                        if (index == -1) System.out.println("Movie not found! ");
                        // System.out.println("Select the index of the movie you want to display: ");
                        // selection = sc.nextInt(); sc.nextLine();
                        else m1.printOne(list, index);
                    }

                    break;

                case 3:
                    purchaseTicket();
                    break;
                case 4:
                    bookingHistory();
                    break;
                case 5:
                    seatDetails();
                    break;
                case 7:
                    LoginUI.execute();
                    break;
                case 8:
                    exitDialouge();
                default:
                    sc.close();
                    break;
            }
            // choice
        } while (true);

        // PaymentUI pay = new PaymentUI();
        // pay.printPayment();

    }

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
                            "(4) View booking history\n\n" +
                            "(5) User Settings\n\n(6) Exit\n\n(7) Login \n\nChoices (1~6): ");

            try {
                System.out.print("\nWhat is your choice:  ");
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 7)
                    break;
                else
                    throw new Exception();
                // choice = Integer.parseInt(sc.next());
            } catch (Exception e) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 7)
                    break;
                else {
                    System.out.println("Please enter a valid option");
                }
            }

        } while (choice < 1 || choice > 6);

        return choice;
    }

    public static void movieListing() {

    }

    public static void movieDetails() {

    }

    public static void seatDetails() {

    }

    public static void purchaseTicket() throws FileNotFoundException, IOException, InterruptedException {
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
        int numberSeats = booking.askTickets();
        
        Pricing price = new Pricing();
        choosenShowtime.printLayout();
        ArrayList<Seat> userSeats = booking.askSeats(numberSeats);
        Ticket ticket = new Ticket(20, userSeats, choosenShowtime, Integer.valueOf(formattedDate));

        int confirmation = booking.confirmTicket(ticket);
        if (confirmation == 1) {
            System.out.println("Done! ");
            ticketHandle.uploadTicket(ticket);
        } else {
            return;
        }

    }

    public static void bookingHistory() throws IOException, InterruptedException {
        BookedHistoryUI bt = new BookedHistoryUI();
        TicketManager tk = new TicketManager("Moblima/src/Data/TicketsBooked.csv");
        ArrayList <Ticket> userTickets = tk.getUserTickets("John");
        int input  = bt.mainUI();
        switch (input) {
            case 1:
                bt.printAllTickets(userTickets);
                bt.individualTicketmenu(userTickets);
                break;


        }

    }

    public static void movieRanking(boolean byTicketSales) throws FileNotFoundException, IOException {
        MovieInfoManager2 m1 = new MovieInfoManager2();
        m1.rankByRatings(byTicketSales);
    }

    public static void admin(User user) {
        System.out.println("=====================================================================================\n");
        System.out.println("                          User Settings                                           \n");
        System.out.println("=====================================================================================\n");
        System.out.println(
                "Do you want to:\n1) Change username\n2) Change password (by index, cannot be 0 or negative)\n");
        System.out.println("Your Choice please: ");

    }

    public static void exitDialouge() {
        System.out.println("Thank you for using Moblima, Have a pleasant day!.");
        System.exit(0);

    }

}
