package system;

import java.util.Scanner;
import java.util.ArrayList;

import database.Movie;
import enums.AgeRating;
import enums.MovieStatus;
import enums.MovieType;

public class MovieSettings {
public static Movie createMovie() { //create a Movie object
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter movie title: ");
		String title = sc.nextLine();
		
		System.out.println("Enter synopsis: ");
		String synopsis = sc.nextLine();
		
		System.out.println("Enter director: ");
		String director = sc.nextLine();
		
		ArrayList<String> cast = new ArrayList<String>();
		int i = 1;
		while (true) {
			
			System.out.printf("Enter cast member %d (Enter END to stop inputting): \n", i);
			String input = sc.nextLine();
			if(input.equals("END"))
				break;
			i++;
			cast.add(input);
		}
		
		System.out.println("Enter movie type:");
		MovieType type = MovieTypeSetter.typeSetter();
		
		System.out.println("Enter age rating: ");
		AgeRating rating = AgeRatingSetter.ageSetter();
		
		System.out.println("Enter movie status: ");
		MovieStatus status = MovieStatusSetter.statusSetter();

		System.out.println("Enter movie genre(s) (Action/Adventure/...): ");
		String genre = sc.nextLine();
		
		System.out.println("Enter runtime (xHxxM): ");
		String runTime = sc.nextLine();
		
		Movie movie = new Movie(title,synopsis,director,cast,type,rating,status,genre,runTime);
		return movie;
	}

public static Movie addMovie() {
	System.out.println("Adding movie...");
	return createMovie();
	
	}

public static void editMovie(Movie movie) {
	Scanner sc = new Scanner(System.in);
	int choice = 0;
	String input = "";
	
	while(choice<10) {
		System.out.println("---Movie Editor---");
		System.out.println("1) Title");
		System.out.println("2) Synopsis");
		System.out.println("3) Director");
		System.out.println("4) Cast");
		System.out.println("5) Type");
		System.out.println("6) AgeRating");
		System.out.println("7) Showing status");
		System.out.println("8) Genre");
		System.out.println("9) Runtime");
		System.out.println("10) Exit");
		choice = sc.nextInt();
		sc.nextLine(); //eat the \n
		
		switch(choice) {
		case 1:
			System.out.println("Enter movie title: ");
			input = sc.nextLine();
			movie.setTitle(input);
			System.out.println("Movie title set to " + input);
			break;
		
		case 2:
			System.out.println("Enter synopsis: ");
			input = sc.nextLine();
			movie.setTitle(input);
			System.out.println("Synopsis set to " + input);
			break;
			
		case 3:
			System.out.println("Enter director: ");
			input = sc.nextLine();
			movie.setDirector(input);
			System.out.println("Director set to " + input);
			break;
			
		case 4:
			int i = 0;
			System.out.println("Current cast members: ");
			ArrayList<String> cast = movie.getCast();

			i = 0;
			while(i<cast.size()) {
				
				for(i = 0;i<cast.size();i++) {
					System.out.printf("Cast member %d: %s \n",i+1, cast.get(i));
				}
				
				System.out.println("Enter cast member number to edit: ");
				int exit = cast.size() + 1;
				System.out.println("Input " + exit + " to exit.");
				i = sc.nextInt();
				sc.nextLine(); //eat the \n
				if(i == exit)
					break;
				if(i<0|| i>exit)
				{
					System.out.println("Invalid input! Exiting...");
					break;
				}
				cast.remove(i-1);
				System.out.println("Enter new cast member name: ");
				cast.add(sc.nextLine());
			}
			System.out.println("New cast set to: ");
			for(i = 0;i<cast.size();i++) {
				System.out.printf("Cast member %d: %s \n",i+1, cast.get(i));
			}
			movie.setCast(cast);
			break;
			
		case 5:
			System.out.println("Enter movie type: ");
			MovieType type = MovieTypeSetter.typeSetter();
			movie.setType(type);
			System.out.println("Movie type set to: " + type);
			break;
			
		case 6:
			System.out.println("Enter age rating: ");
			AgeRating rating = AgeRatingSetter.ageSetter();
			movie.setAgeRating(rating);
			System.out.println("Movie age rating set to: " + rating);
			break;
		
		case 7:
			System.out.println("Enter movie showing status: ");
			MovieStatus status = MovieStatusSetter.statusSetter();
			movie.setMovieStatus(status);
			System.out.println("Movie status set to: " + status);
			break;
			
		case 8:
			System.out.println("Enter movie genre(s) (Action/Horror): ");
			input = sc.nextLine();
			movie.setGenre(input);
			System.out.println("Movie genre set to: " + input);
			break;
		
		case 9:
			System.out.println("Enter movie runtime (xHxxM): ");
			input = sc.nextLine();
			movie.setrunTime(input);
			System.out.println("Movie runtime set to: " + input);
			break;
		case 10:
			System.out.println("Exiting...");
			return;
		default:
			System.out.println("Invalid choice! Exiting...");
			return;	
		}
	}
	
}
public static String removeMovie() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Removing movie...");
	System.out.println("Enter movie title: ");
	return sc.nextLine();
	
}

/*public static void main(String [] args) {
	ArrayList<String> cast = new ArrayList<String>();
	cast.add("john");
	cast.add("jack");
	cast.add("jhin");
	Movie movie = new Movie("alien","prose","guy",cast,MovieType.TWOD,AgeRating.NC16,MovieStatus.NOW_SHOWING,"Action/Horror","2H 15M");
	editMovie(movie);		
	
}*/ //for testing
}
