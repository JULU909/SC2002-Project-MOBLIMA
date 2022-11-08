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
		
		System.out.println("Enter movie: ");
		String title = sc.nextLine();
		System.out.println("Enter synopsis: ");
		String synopsis = sc.nextLine();
		System.out.println("Enter director: ");
		String director = sc.nextLine();
		ArrayList<String> cast = new ArrayList<String>;
		
		while (true) {
			System.out.printf("Enter cast member %d (Enter END to stop inputting): \n", i+1);
			String input = sc.nextLine();
			if(input.equals("END"))
				break;
			cast.add(input);
		}
		// (int index, String title, String showingStatus, String synopsis, String director, ArrayList<String> cast, double averageRating, AgeRating ageRating,ArrayList<Review> reviews, String genre, String runTime)
		System.out.println("Enter movie type:");
		MovieType type = MovieTypeSetter.typeSetter();
		System.out.println("Enter age rating: ");
		AgeRating rating = AgeRatingSetter.ageSetter();
		System.out.println("Enter showing status: ");
		AgeRating rating = AgeRatingSetter.ageSetter();
		Movie movie = new Movie(title,synopsis,director,cast,type,rating);
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
	
	while(choice<8) {
		System.out.println("---Movie Editor---");
		System.out.println("1) Title");
		System.out.println("2) Synopsis");
		System.out.println("3) Director");
		System.out.println("4) Cast");
		System.out.println("5) Type");
		System.out.println("6) AgeRating");
		System.out.println("7) Showing status");
		System.out.println("8) Exit");
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
			System.out.println("Current cast members: ");
			int i = 0;
			String[] cast = movie.getCast();
			for(i = 0;i<cast.length;i++) {
				System.out.printf("Cast member %d: %s \n",i+1, cast[i]);
			}
			i = 0;
			while(i<cast.length) {
				System.out.println("Enter cast member number to edit: ");
				int exit = cast.length + 1;
				System.out.println("Input " + exit + " to exit.");
				i = sc.nextInt();
				sc.nextLine();
				if(i >= exit)
					break;	
				System.out.println("Enter new cast member name: ");
				cast[i-1] = sc.nextLine();
			}
			System.out.println("New cast set to: ");
			for(i = 0;i<cast.length;i++) {
				System.out.printf("Cast member %d: %s \n",i+1, cast[i]);
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
			System.out.println("Exiting...");
			return;
		default:
			System.out.println("Invalid choice! Exiting...");
			return;	
		}
	}
	
}
public static Movie removeMovie() {
	System.out.println("Removing movie...");
	return createMovie();
	
}

}
