package UI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import database.Movie;
import database.MovieInfoManager2;
import database.ShowtimeManager;
import system.DaySettings;
import system.MovieSettings;
import system.Showtime;
import system.ShowtimeSettings;


public class SettingsUI {
	public static void settingsText() throws FileNotFoundException, IOException {
		System.out.println("----Cineplex Settings ---- ");
		int choice = 0;
		
		Scanner sc = new Scanner(System.in);
		String Showfilepath = new File("Showtimes.csv").getAbsolutePath();
		String MovieInfoPath = new File("movieInformation2.csv").getAbsolutePath();
		ShowtimeManager sm = new ShowtimeManager(Showfilepath);
		MovieInfoManager2 mm = new MovieInfoManager2(MovieInfoPath);
		ArrayList<Showtime> showList = new ArrayList<Showtime>(); 
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
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
			sc.nextLine(); // Eat the \n
			switch(choice) {
			case 1:
				enums.Day day = DaySettings.setDay();
				System.out.println("Day set to " + day); 
				break;
			
			case 2:
				Movie newMovie = MovieSettings.addMovie(); //Create movie
				mm.addMoviecsv(newMovie); //Add to movieInfo CSV
				break;
			
			case 3: 
				System.out.println("Enter movie title to edit: ");
				String TitletoEdit = sc.nextLine();
				movieList = mm.readMovieCSV(); //Convert CSV to array list
				int j = MovieInfoManager2.findMovieCSV(TitletoEdit, movieList); //Find it in array list
				if(j == -1) //If it does not exist
				{
					System.out.println("Movie does not exist! Exiting...");
					break; //Exit
				}
				Movie MovietoEdit = movieList.get(j); //Otherwise get its details
				movieList.remove(j); //Remove it from the array list
				MovieSettings.editMovie(MovietoEdit); //Edit the movie
				movieList.add(MovietoEdit); //Then add it back to the array list
				mm.writeMovieCSV(movieList); //Finally, write it to the CSV
				break;
			
			case 4: 
				String TitletoRemove = MovieSettings.removeMovie(); //Find title of Movie to remove
				movieList = mm.readMovieCSV(); //Convert CSV to array list
				int k = MovieInfoManager2.findMovieCSV(TitletoRemove, movieList); //Find it in array list	
				if(k == -1) //If it does not exist
				{
					System.out.println("Movie does not exist! Exiting...");
					break; //Exit
				}
				movieList.remove(k); //Otherwise, remove it from array list
				mm.writeMovieCSV(movieList); //Then write the list it to CSV
				break;
				
			case 5:
				Showtime newShowtime = ShowtimeSettings.addShowtime(); //Get show time to add
				sm.addShowtimecsv(newShowtime); //And add it to CSV
				break;
				
			case 6:
				System.out.println("Enter showtime to edit.");
				Showtime toEdit = ShowtimeSettings.createShowtime(); //Find which show time to edit
				
				showList = sm.readShowtimecsv(); //Convert CSV to array list
				int i = ShowtimeManager.findShowtimecsv(showList, toEdit); //Find show time's position
				if(i == -1) //If show time does not exist
				{
					System.out.println("Showtime does not exist! Exiting...");
					break; //Exit program
				}
				showList.remove(i);//Otherwise remove the show time that needs editing in array list
				sm.writeShowtimecsv(showList); //Convert array list to CSV
				ShowtimeSettings.editShowtime(toEdit); //Edit the show time
				sm.addShowtimecsv(toEdit); //And add the show time to the CSV
				break;
			case 7: 
				Showtime ShowTimetoRemove = ShowtimeSettings.removeShowtime(); //Find which show time to remove
				showList = sm.readShowtimecsv(); //Convert CSV to array list
				showList = sm.removeShowtimecsv(showList, ShowTimetoRemove); //Remove show time in array list
				sm.writeShowtimecsv(showList); //Rewrite CSV
				break;
			case 8:
				break;
			case 9:
				System.out.println("Exiting settings...");
				return;
			default:
				System.out.println("Invalid input! Exiting settings...");
				return;
			
			}
		}
		
	}

}
