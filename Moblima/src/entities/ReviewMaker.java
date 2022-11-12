package entities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;
import model.Movie;
import model.MovieInfoManager;
import model.Review;

public class ReviewMaker {
	public static void newMovieReview(Customer customer) throws FileNotFoundException, IOException { 
		MovieInfoManager mm = new MovieInfoManager();
		ArrayList<Movie> list = mm.readMovieCSV();	
		int rating = -1;
		int movieOpt = 0;
		//List all movie for user to sel
		System.out.println("=====================================================================================\n");
		System.out.println("                         Select a movie to give a review to:                         \n");
		System.out.println("=====================================================================================\n");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + ") " + list.get(i).getTitle());
		}
		Scanner sc = new Scanner(System.in);

		//Select movie option
		while (movieOpt <= 0 || movieOpt > list.size()) {
			movieOpt = newMovieOption(sc);
			if(movieOpt <= 0 || movieOpt > list.size()) System.out.println("Please enter a valid option:");
		}
		System.out.println();

		//Enter review
		System.out.println("Write the review for '" + list.get(movieOpt-1).getTitle() + "':"); 
        String review = sc.nextLine();
		System.out.println();

		//Enter rating
		System.out.println("Give the movie '" +  list.get(movieOpt-1).getTitle() + "' a rating out of 5 (Rating must be within 1 to 5):");
		while (rating < 0 || rating > 5) {
			rating = newMovieRating(sc);
			if(rating < 0 || rating > 5) System.out.println("Please enter a whole number for rating and within the range of 1 to 5:");
		}

		//Get that movie being reviewed
		Movie tempMovie = list.get(movieOpt-1);

		//update avgRating 
		double avgRating = ((tempMovie.getAverageRating() * tempMovie.getReviews().size()) + rating)/(tempMovie.getReviews().size()+1);
		tempMovie.setAverageRating(avgRating);

		//Updating the review
		Review rv = new Review(customer.getUsername(), rating, review);
		tempMovie.addReview(rv);

		//Update to CSV
		list.set(movieOpt-1, tempMovie);
		mm.writeMovieCSV(list);
		mm.updateAverageRating(tempMovie.getTitle());

		System.out.println("Review successfully added!");
	}

	public static int newMovieRating(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a whole number for rating and within the range of 1 to 5:");
			sc.next();
		}
		int rating = sc.nextInt();
		sc.nextLine();
		return rating;
	}

	public static void viewMovieRating() throws FileNotFoundException, IOException { 
		MovieInfoManager mm = new MovieInfoManager();
		ArrayList<Movie> list = mm.readMovieCSV();	
		int movieOpt = 0;
		System.out.println("=====================================================================================\n");
		System.out.println("                         Select a movie to see review(s) for:                        \n");
		System.out.println("=====================================================================================\n");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + ") " + list.get(i).getTitle());
		}
		Scanner sc = new Scanner(System.in);

		//Select movie option
		while (movieOpt <= 0 || movieOpt > list.size()) {
			movieOpt = newMovieOption(sc);
			if(movieOpt <= 0 || movieOpt > list.size()) System.out.println("Please enter a valid option:");
		}

		//Displaying movie's review
		System.out.println();
		System.out.println();
		System.out.println("=====================================================================================\n");
		System.out.println("                         Review(s) for '" + list.get(movieOpt-1).getTitle() + "''                        \n");
		System.out.println("=====================================================================================\n");
		int reviewSize = list.get(movieOpt-1).getReviews().size();
		for (int i = 0; i < reviewSize; i++) {
			Review rv = list.get(movieOpt-1).getReviews().get(i);
			System.out.println("Reviewer's name:");
			System.out.println(rv.getReviewer()+"\n");
			System.out.println("Review:");
			System.out.println(rv.getProse()+"\n");
			System.out.println("Rating:");
			System.out.println(rv.getRating() + "/5");
			System.out.println("-----------------------------------------------------------------------------------------\n\n");
		}
		System.out.println();
	}

	public static int newMovieOption(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a valid option:");
			sc.next();
		}
		int movieOpt = sc.nextInt();
		sc.nextLine();
		return movieOpt;
	}

}
