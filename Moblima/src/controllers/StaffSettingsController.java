package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Holiday;
import entities.Movie;
import entities.Pricing;
import entities.RegisterStaff;
import entities.Showtime;
import entities.Staff;
import model.HolidayManager;
import model.MovieInfoManager;
import model.ShowtimeManager;
import enums.MovieStatus;

/** This class controls StaffSettingsUI
 * @author Tham Holdon
 * @version 1.0
 * @since 2022-11-13
 */


public class StaffSettingsController {

	/**
	 * This method sets the base price of a movie ticket
	 */
	public static void setPrice() {
		Scanner sc = new Scanner(System.in);
		Pricing price = new Pricing();
		double basedPrice;
		while(true) {
			try{System.out.println("Current base price is " + price.getBasedPrice());
			System.out.println("Enter new base price ");
			basedPrice = sc.nextDouble();
			if(basedPrice<=0)
			{
				System.out.println("Enter a positive value!");
				continue;
			}
				break;
			}catch(InputMismatchException e ) {
				System.out.println("Enter a valid input! ");
		        String error = sc.next(); // catch the enter;
		        continue;
			}
		}
			price.setBasedPrice(basedPrice);
			System.out.println("Base price set to " + price.getBasedPrice());
			return;
	}
	
	/**
	 * This method adds a movie into the movieInformation CSV
	 */
	public static void addMovie() throws FileNotFoundException, IOException {
		Movie newMovie = MovieSettings.addMovie(); //Create movie
		MovieInfoManager mm = new MovieInfoManager();
		int i = MovieInfoManager.findExactMovieCSV(newMovie.getTitle(), mm.readMovieCSV());
		if(i==-1)
		{
			mm.addMoviecsv(newMovie); //Add to movieInfo CSV
			System.out.println(newMovie.getTitle() + " added!");
		}
		else
			System.out.println("A movie with that title is already in the database! Exiting...");
		return;
	}
	/**
	 * This method edits a movie in movieInformation CSV
	 */
	public static void editMovie() throws FileNotFoundException, IOException {
		MovieInfoManager mm = new MovieInfoManager();
		ArrayList<Movie >movieList = mm.readMovieCSV(); //Convert CSV to array list
		StaffSettingsController.showMovies();
		System.out.println("Enter movie title to edit: ");
		Scanner sc = new Scanner(System.in);
		String TitletoEdit = sc.nextLine();
		int j = MovieInfoManager.findExactMovieCSV(TitletoEdit, movieList); //Find it in array list
		if(j == -1) //If it does not exist
		{
			System.out.println("Movie does not exist! Exiting...");
			return; //Exit
		}
		Movie MovietoEdit = movieList.get(j); //Otherwise get its details
		MovieSettings.editMovie(MovietoEdit); //Edit the movie
		
		movieList.set(j,MovietoEdit); //Then set it back to the array list
		mm.writeMovieCSV(movieList); //Finally, write it to the CSV
		System.out.println(MovietoEdit + " successfully edited!");
		return;
	}
	/**
	 * This method removes a movie in movieInformation CSV
	 */
	public static void removeMovie() throws FileNotFoundException, IOException {
		MovieInfoManager mm = new MovieInfoManager();
		ArrayList<Movie>movieList = mm.readMovieCSV();
		StaffSettingsController.showMovies();
		String TitletoRemove = MovieSettings.removeMovie(); //Find title of Movie to remove
		int k = MovieInfoManager.findExactMovieCSV(TitletoRemove, movieList); //Find it in array list	
		if(k == -1) //If it does not exist
		{
			System.out.println("Movie does not exist! Exiting...");
			return; //Exit
		}
		Movie movie = movieList.get(k); //Otherwise, get it from array list
		movie.setMovieStatus(MovieStatus.END_OF_SHOWING);
		movieList.set(k,movie); //Remove it from array list
		mm.writeMovieCSV(movieList); //Then write the list it to CSV
		System.out.println(TitletoRemove + " removed!");
		return;
	}
	/**
	 * This method adds a show time into show time CSV
	 */
	public static void addShowtime() throws FileNotFoundException, IOException {
		StaffSettingsController.showMovies();
		ShowtimeManager sm = new ShowtimeManager("Moblima/src/Data/Showtimes.csv");
		
		Showtime newShowtime = ShowtimeSettings.addShowtime(); //Get show time to add
		int i = ShowtimeManager.findShowtimecsv(sm.readShowtimecsv(), newShowtime);
		if(i==-1)
			sm.addShowtimecsv(newShowtime); //And add it to CSV
		else
		{
			System.out.println("That showtime already exists on the database! Exiting...");
		}
		return;
	}
	/**
	 * This method edits a show time in show time CSV
	 */
	public static void editShowtime() throws FileNotFoundException, IOException {
		StaffSettingsController.showMovies();
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showList = sm.readShowtimecsv();
		System.out.println("Enter showtime to edit.");
		Showtime toEdit = ShowtimeSettings.createShowtime(); //Find which show time to edit
		
		showList = sm.readShowtimecsv(); //Convert CSV to array list
		int i = ShowtimeManager.findShowtimecsv(showList, toEdit); //Find show time's position
		if(i == -1) //If show time does not exist
		{
			System.out.println("Showtime does not exist! Exiting...");
			return; //Exit program
		}
		ShowtimeSettings.editShowtime(toEdit); //Edit the show time
		showList.set(i, toEdit); //And change the show time in the CSV
		sm.writeShowtimecsv(showList); //Convert array list to CSV
		return;
	}
	/**
	 * This method removes a show time in show time CSV
	 */
	public static void removeShowtime() throws FileNotFoundException, IOException {
		StaffSettingsController.showMovies();
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showList = sm.readShowtimecsv();
		Showtime ShowTimetoRemove = ShowtimeSettings.removeShowtime(); //Find which show time to remove
		showList = sm.removeShowtimecsv(showList, ShowTimetoRemove); //Remove show time in array list
		sm.writeShowtimecsv(showList); //Rewrite CSV
		return;
	}
	/**
	 * This method adds a holiday into holiday CSV
	 */
	public static void addHoliday() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		HolidayManager hm = new HolidayManager();
		System.out.println("Enter holiday name.");
		String name = sc.nextLine();
		System.out.println("Enter holiday date (yyyy-MM-dd).");
		LocalDate date = LocalDate.parse(sc.nextLine());
		Holiday newHoliday = new Holiday(name, date); //Get show time to add
		hm.addHolidayCSV(newHoliday); //And add it to CSV
		System.out.println("Holiday date successfully added!");
		return;
	}
	/**
	 * This method removes a holiday in holiday CSV
	 */
	public static void removeHoliday() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		HolidayManager hm = new HolidayManager();ShowtimeManager sm = new ShowtimeManager();
		hm.printHolidaysCSV();
		System.out.println("Enter index of date to remove: ");
		int i = sc.nextInt(); sc.nextLine();
		hm.removeHolidayCSV(i);
		return;
	}
	/**
	 * This method allows a staff member to add another staff member into the Staff CSV
	 */
	public static void registerStaff() throws IOException {
		Staff newStaff = RegisterStaff.registerStaff();
		RegisterStaff.addStaffCSV(newStaff);
		System.out.println("Staff member " + newStaff.getUsername() + " added to database.");
		return;
	}
	/**
	 * This method calls the function to print the top 5 movies by sales
	 */
	public static void rankMovieSales() throws FileNotFoundException, IOException {
		MovieInfoManager mm = new MovieInfoManager();
		mm.rankByRatings(true);
		return;
	}
	
	/**
	 * This method calls the function to print the top 5 movies by ratings
	 */
	public static void rankMovieRatings() throws FileNotFoundException, IOException {
		MovieInfoManager mm = new MovieInfoManager();
		mm.rankByRatings(false);
		return;
	}
	/**
	 * This method shows all the movie titles currently in movieInformation CSV
	 */
	public static void showMovies() throws FileNotFoundException, IOException {
		System.out.println("Current movies: ");
		MovieInfoManager mm = new MovieInfoManager();
		ArrayList<Movie>movieList = mm.readMovieCSV();
		int k = 0;
		while(k!=movieList.size())
		{
			System.out.println("- " + movieList.get(k).getTitle()); //Show all movies in database
			k++;
		}
	}
}
