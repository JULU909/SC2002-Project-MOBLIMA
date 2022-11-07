package system;

import java.util.Scanner;

import database.Movie;
import enums.AgeRating;
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
		System.out.println("Enter cast member 1: ");
		String cast1 = sc.nextLine();
		System.out.println("Enter cast member 2:");
		String cast2 = sc.nextLine();
		String[] cast = {cast1,cast2};
		System.out.println("Enter movie type:");
		MovieType type = MovieTypeSetter.typeSetter();
		System.out.println("Enter age rating: ");
		AgeRating rating = AgeRatingSetter.ageSetter();
		Movie movie = new Movie(title,synopsis,director,cast,type,rating);
		return movie;
	}

}
