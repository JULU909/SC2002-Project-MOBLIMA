package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import entities.MovieSettings;
import entities.Pricing;
import entities.RegisterStaff;
import entities.Showtime;
import entities.ShowtimeSettings;
import model.Holiday;
import model.HolidayManager;
import model.Movie;
import model.MovieInfoManager;
import model.ShowtimeManager;
import model.Staff;

public class StaffSettingsController {
	
	public static void setPrice() {
		Scanner sc = new Scanner(System.in);
		Pricing price = new Pricing();
		System.out.println("Current base price is " + price.getBasedPrice());
		System.out.println("Enter new base price ");
		double basedPrice = sc.nextDouble();
		price.setBasedPrice(basedPrice);
		System.out.println("Base price set to " + price.getBasedPrice());
		return;
	}
	
	public static void addMovie() throws FileNotFoundException, IOException {
		Movie newMovie = MovieSettings.addMovie(); //Create movie
		MovieInfoManager mm = new MovieInfoManager();
		mm.addMoviecsv(newMovie); //Add to movieInfo CSV
		return;
	}
	
	public static void editMovie() throws FileNotFoundException, IOException {
		System.out.println("Enter movie title to edit: ");
		Scanner sc = new Scanner(System.in);
		MovieInfoManager mm = new MovieInfoManager();
		String TitletoEdit = sc.nextLine();
		ArrayList<Movie >movieList = mm.readMovieCSV(); //Convert CSV to array list
		int j = MovieInfoManager.findMovieCSV(TitletoEdit, movieList); //Find it in array list
		if(j == -1) //If it does not exist
		{
			System.out.println("Movie does not exist! Exiting...");
			return; //Exit
		}
		Movie MovietoEdit = movieList.get(j); //Otherwise get its details
		movieList.remove(j); //Remove it from the array list
		MovieSettings.editMovie(MovietoEdit); //Edit the movie
		movieList.add(MovietoEdit); //Then add it back to the array list
		mm.writeMovieCSV(movieList); //Finally, write it to the CSV
		return;
	}
	
	public static void removeMovie() throws FileNotFoundException, IOException {
		MovieInfoManager mm = new MovieInfoManager();
		ArrayList<Movie>movieList = mm.readMovieCSV(); //Convert CSV to array list
		System.out.println("Current movies: ");
		int k = 0;
		while(k!=movieList.size())
		{
			System.out.println("- " + movieList.get(k).getTitle()); //Show all movies in database
		}
		String TitletoRemove = MovieSettings.removeMovie(); //Find title of Movie to remove
		k = MovieInfoManager.findMovieCSV(TitletoRemove, movieList); //Find it in array list	
		if(k == -1) //If it does not exist
		{
			System.out.println("Movie does not exist! Exiting...");
			return; //Exit
		}
		movieList.remove(k); //Otherwise, remove it from array list
		mm.writeMovieCSV(movieList); //Then write the list it to CSV
		return;
	}
	
	public static void addShowtime() throws FileNotFoundException, IOException {
		ShowtimeManager sm = new ShowtimeManager();
		Showtime newShowtime = ShowtimeSettings.addShowtime(); //Get show time to add
		sm.addShowtimecsv(newShowtime); //And add it to CSV
		return;
	}
	
	public static void editShowtime() throws FileNotFoundException, IOException {
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
		showList.remove(i);//Otherwise remove the show time that needs editing in array list
		sm.writeShowtimecsv(showList); //Convert array list to CSV
		ShowtimeSettings.editShowtime(toEdit); //Edit the show time
		sm.addShowtimecsv(toEdit); //And add the show time to the CSV
		return;
	}
	
	public static void removeShowtime() throws FileNotFoundException, IOException {
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showList = sm.readShowtimecsv();
		Showtime ShowTimetoRemove = ShowtimeSettings.removeShowtime(); //Find which show time to remove
		showList = sm.removeShowtimecsv(showList, ShowTimetoRemove); //Remove show time in array list
		sm.writeShowtimecsv(showList); //Rewrite CSV
		return;
	}

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
	
		public static void removeHoliday() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		HolidayManager hm = new HolidayManager();ShowtimeManager sm = new ShowtimeManager();
		hm.printHolidaysCSV();
		System.out.println("Enter index of date to remove: ");
		int i = sc.nextInt(); sc.nextLine();
		hm.removeHolidayCSV(i);
		return;
	}
	public static void registerStaff() throws IOException {
		Staff newStaff = RegisterStaff.registerStaff();
		RegisterStaff.addStaffCSV(newStaff);
		System.out.println("Staff member " + newStaff.getUsername() + " added to database.");
		return;
	}
	
	public static void rankMovieSales() throws FileNotFoundException, IOException {
		MovieInfoManager mm = new MovieInfoManager();
		mm.rankByRatings(true);
		return;
	}
	
	public static void rankMovieRatings() throws FileNotFoundException, IOException {
		MovieInfoManager mm = new MovieInfoManager();
		mm.rankByRatings(false);
		return;
	}
}
