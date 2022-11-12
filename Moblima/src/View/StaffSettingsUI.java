package View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllers.LoginController;
import controllers.StaffSettingsController;


public class StaffSettingsUI {
	public static void settingsText() throws FileNotFoundException, IOException, InterruptedException, ParseException {
		int choice = 0;
		
		Scanner sc = new Scanner(System.in);

		
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
	

}
