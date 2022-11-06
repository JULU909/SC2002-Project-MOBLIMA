package UI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import database.ShowtimeManager;
import system.DaySettings;
import system.Showtime;
import system.ShowtimeSettings;


public class SettingsUI {
	public static void settingsText() throws FileNotFoundException, IOException {
		System.out.println("----Cineplex Settings ---- ");
		int choice = 0;
		
		Scanner sc = new Scanner(System.in);
		String filepath = new File("Showtimes.csv").getAbsolutePath();
		ShowtimeManager sm = new ShowtimeManager(filepath);
		ArrayList<Showtime> list = new ArrayList<Showtime>(); 
		
		while(choice<8) {
			System.out.println("1) Set day.");
			System.out.println("2) Add movie.");
			System.out.println("3) Update movie.");
			System.out.println("4) Remove movie.");
			System.out.println("5) Add show time.");
			System.out.println("6) Edit show time.");
			System.out.println("7) Remove show time.");
			System.out.println("8) List top movies.");
			System.out.println("9) Exit.");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				enums.Day day = DaySettings.setDay();//this variable has to go somewhere but im not sure where
				System.out.println("Day set to " + day); 
				break;
			case 2:
				break;
			case 3:
				break;
			case 4: 
				break;
			case 5:
				Showtime newShowtime = ShowtimeSettings.addShowtime(); //get showtime to add
				sm.addShowtimecsv(newShowtime); //add it to csv
				break;
			case 6:
				System.out.println("Enter showtime to edit.");
				Showtime toEdit = ShowtimeSettings.createShowtime();
				
				list = sm.readShowtimecsv(); //convert csv to array list
				int i = ShowtimeManager.findShowtimecsv(list, toEdit);
				if(i == -1) //if showtime does not exist
				{
					System.out.println("Showtime does not exist! Exiting...");
					break; //exit program
				}
				list.remove(i);//remove the showtime that needs editing in array list
				sm.writeShowtimecsv(list); //convert array list to csv
				ShowtimeSettings.editShowtime(toEdit); //edit the showtime
				sm.addShowtimecsv(toEdit); //add it to the csv
				break;
			case 7: 
				Showtime toRemove = ShowtimeSettings.removeShowtime(); //get showtime to remove
				list = new ArrayList<Showtime>(); 
				list = sm.readShowtimecsv(); //convert csv to array list
				list = sm.removeShowtimecsv(list, toRemove); //remove showtime in array list
				sm.writeShowtimecsv(list); //rewrite csv back
				break;
			case 8:
				break;
			default:
				return;
			
			}
		}
		
	}
	/*public static void main(String [] args) throws FileNotFoundException, IOException {
		settingsText(); //testing
	}*/

}
