package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import View.*;
import enums.*;
import model.*;
import entities.*;

public class SeatDetailController {
    public void getSeatDetails() throws NumberFormatException, FileNotFoundException, IOException, InterruptedException{
        // Connection to the managers and UI
        BookingDisplay booking = new BookingDisplay();
        ShowtimeManager Showtimes = new ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        int showtimesLength = Showtimes.getLength();
        TicketManager ticketHandle = new TicketManager("Moblima/src/Data/TicketsBooked.csv");
        // Getting user inputs.
        String cineplex = booking.askCineplex();
        if (cineplex.equals("exit")){
            return;
        }
        MovieInfoManager movieManager = new MovieInfoManager();
        ArrayList<Movie> showingMovies = movieManager.findShowingMovies();
        int movieChoice = booking.askMovie(showingMovies);
        String movieName = showingMovies.get(movieChoice).getTitle();
        LocalDate inputDate = booking.askDate();
        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("ddMMyy"));
        ArrayList<Showtime> showtimes = Showtimes.getShowtimes(movieName, cineplex ,Integer.valueOf(formattedDate) );
        int showtimeChoice = booking.askTiming(showtimes);
        if(showtimeChoice == -1){
            return;
        }
        Showtime choosenShowtime = showtimes.get(showtimeChoice - 1);
        choosenShowtime.setDate(Integer.valueOf(formattedDate));
        choosenShowtime.setLayout();
        choosenShowtime.printLayout();
        booking.printExitMessage();
        return;

    }
}
