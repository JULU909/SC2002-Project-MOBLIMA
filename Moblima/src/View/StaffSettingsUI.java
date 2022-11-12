package View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.login.LoginContext;
import controllers.LoginController;
import controllers.StaffSettingsController;
import entities.MovieSettings;
import entities.Pricing;
import entities.RegisterStaff;
import entities.Showtime;
import entities.ShowtimeSettings;
import model.Movie;
import model.MovieInfoManager;
import model.ShowtimeManager;
import model.Staff;


public class StaffSettingsUI {
	public static void settingsText() throws FileNotFoundException, IOException, InterruptedException, ParseException {
		int choice = 0;
		
		Scanner sc = new Scanner(System.in);
		String Showfilepath = "Moblima/src/Data/Showtimes.csv"; //new File("Showtimes.csv").getAbsolutePath();
		String MovieInfoPath = "Moblima/src/Data/movieInformation2.csv"; //new File("movieInformation2.csv").getAbsolutePath(); 
		ShowtimeManager sm = new ShowtimeManager(Showfilepath);
		MovieInfoManager mm = new MovieInfoManager(MovieInfoPath);
		ArrayList<Showtime> showList = new ArrayList<Showtime>(); 
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		while(choice<11) {
			System.out.println("1) Set ticket base price.");
			System.out.println("2) Add movie.");
			System.out.println("3) Edit movie.");
			System.out.println("4) Remove movie.");
			System.out.println("5) Add show time.");
			System.out.println("6) Edit show time.");
			System.out.println("7) Remove show time.");
			System.out.println("8) Add holiday.");
			System.out.println("9) Remove holiday.");
			System.out.println("10) Register Staff.");
			System.out.println("11) Display top 5 by total sales");
			System.out.println("12) Display top 5 by ratings");
			System.out.println("13) Exit.");

			try {
				choice = sc.nextInt();
				sc.nextLine(); // Eat the \n
			} catch (Exception e) {System.out.println("Please enter a valid input!"); continue;}
			
			
			switch(choice) {
			case 1:
				StaffSettingsController.setPrice();
				break;
			
			case 2:
				StaffSettingsController.addMovie();
				break;
			
			case 3: 
				StaffSettingsController.editMovie();
				break;
			
			case 4: 
				StaffSettingsController.removeMovie();
				break;
			case 5:
				StaffSettingsController.addShowtime();
				break;
				
			case 6:
				StaffSettingsController.editShowtime();
				break;
			case 7: 
				StaffSettingsController.removeShowtime();
				break;
			case 8:
				StaffSettingsController.addHoliday();
				break;
			case 9:
				StaffSettingsController.removeHoliday();
				break;
			case 10:
				StaffSettingsController.registerStaff();
				break;
				
			case 11:
				StaffSettingsController.rankMovieSales();
				break;
			case 12:
				StaffSettingsController.rankMovieRatings();
				break;
			case 13:
				System.out.println("Exiting settings...");
				LoginController.execute();
				return;
			default:
				System.out.println("Please enter a valid input!");
				break;
			}
		}
		
	}
	
	/*public static void main (String[] args) throws FileNotFoundException, IOException {
		StaffSettingsUI.settingsText();
	}*/

}
