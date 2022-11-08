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
			sc.nextLine(); // eat the \n
			switch(choice) {
			case 1:
				enums.Day day = DaySettings.setDay();//this variable has to go somewhere but im not sure where
				System.out.println("Day set to " + day); 
				break;
			case 2:
				Movie newMovie = MovieSettings.addMovie(); //create movie
				mm.addMoviecsv(newMovie); //add to csv
				break;
			case 3: 
				System.out.println("Enter movie title to edit: ");
				String TitletoEdit = sc.nextLine();
				movieList = mm.readMovieCSV(); //convert csv to arraylist
				int j = MovieInfoManager2.findMovieCSV(TitletoEdit, movieList); //find it in array list
				if(j == -1) //if it does not exist
				{
					System.out.println("Movie does not exist! Exiting...");
					break; //exit
				}
				Movie MovietoEdit = movieList.get(j); //if it exist, get details
				movieList.remove(j); //remove it from array list
				MovieSettings.editMovie(MovietoEdit); //edit movie
				movieList.add(MovietoEdit); //add it back to array list
				mm.writeMovieCSV(movieList); //write it to csv
				break;
			case 4: 
				String TitletoRemove = MovieSettings.removeMovie();
				movieList = mm.readMovieCSV(); //convert csv to arraylist
				int k = MovieInfoManager2.findMovieCSV(TitletoRemove, movieList); //find it in array list
				if(k == -1) //if it does not exist
				{
					System.out.println("Movie does not exist! Exiting...");
					break; //exit
				}
				movieList.remove(k); //if it exist, remove it from array list
				mm.writeMovieCSV(movieList); //write it to csv
				break;
			case 5:
				Showtime newShowtime = ShowtimeSettings.addShowtime(); //get showtime to add
				sm.addShowtimecsv(newShowtime); //add it to csv
				break;
			case 6:
				System.out.println("Enter showtime to edit.");
				Showtime toEdit = ShowtimeSettings.createShowtime();
				
				showList = sm.readShowtimecsv(); //convert csv to array list
				int i = ShowtimeManager.findShowtimecsv(showList, toEdit);
				if(i == -1) //if showtime does not exist
				{
					System.out.println("Showtime does not exist! Exiting...");
					break; //exit program
				}
				showList.remove(i);//remove the showtime that needs editing in array list
				sm.writeShowtimecsv(showList); //convert array list to csv
				ShowtimeSettings.editShowtime(toEdit); //edit the showtime
				sm.addShowtimecsv(toEdit); //add it to the csv
				break;
			case 7: 
				Showtime ShowTimetoRemove = ShowtimeSettings.removeShowtime(); //get showtime to remove
				showList = new ArrayList<Showtime>(); 
				showList = sm.readShowtimecsv(); //convert csv to array list
				showList = sm.removeShowtimecsv(showList, ShowTimetoRemove); //remove showtime in array list
				sm.writeShowtimecsv(showList); //rewrite csv back
				break;
			case 8:
				break;
			default:
				return;
			
			}
		}
		
	}
	public static void main(String [] args) throws FileNotFoundException, IOException {
		settingsText(); //testing
	}

}
