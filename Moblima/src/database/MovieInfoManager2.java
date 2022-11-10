package database;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import enums.AgeRating;
import enums.MovieStatus;
import enums.MovieType;

import java.util.ArrayList;

import java.io.FileWriter; //for writing to csv

public class MovieInfoManager2 {
	private String filename;

    public final static String FILENAME = new File("movieInformation2.csv").getAbsolutePath(); //"Moblima/src/Data/movieInformation2.csv";

    public MovieInfoManager2(){
        this.filename = FILENAME;
    }

    public MovieInfoManager2(String filename) {
        this.filename = filename;
    }
    
    
    public void addMoviecsv(Movie movie) throws FileNotFoundException, IOException{ //Add a movie into CSV
    	//Writer will write into filename, true allows appending
    	FileWriter writer = new FileWriter(filename,true);
    	
    	//Get each attribute out from movie
    	String title = movie.getTitle();
    	String synopsis = movie.getSynopsis();
    	String director = movie.getDirector();
    	ArrayList<String> cast = movie.getCast();
    	MovieType type = movie.getType();
    	AgeRating ageRating = movie.getAgeRating();
    	MovieStatus status = movie.getMovieStatus();
    	int totalSales = movie.getTotalSales();
    	double averageRating = movie.getAverageRating();
    	String genre = movie.getGenre();
    	String runtime = movie.getrunTime();
    	
    	ArrayList<Review> review = movie.getReviews(); //Reviews contains String user, int rating, String prose
    	
    	//Write them all into CSV
    	writer.append(title);
    	writer.append(",");
    	writer.append(synopsis);
    	writer.append(",");
    	writer.append(director);
    	writer.append(",");
    	
    	int i = 1;
    	writer.append(cast.get(0));
    	while(i!=cast.size()) { //Add cast members into one column in CSV
    		writer.append("'"); //' splits between different cast members
    		writer.append(cast.get(i));
    		i++;
    	}
    	writer.append(",");
    	writer.append(type.toString());
    	writer.append(",");
    	writer.append(ageRating.toString());
    	writer.append(",");
    	writer.append(status.toString());
    	writer.append(",");
    	writer.append(String.valueOf(totalSales));
    	writer.append(",");
    	writer.append(String.valueOf(averageRating));
    	writer.append(",");
    	writer.append(genre);
    	writer.append(",");
    	writer.append(runtime);
    	writer.append(",");
    	
    	i=1;
    	writer.append(review.get(0).getReviewer());
    	writer.append("'");//' splits between different attributes of review
    	writer.append(review.get(0).getProse());
    	writer.append("'");
    	writer.append(String.valueOf(review.get(0).getRating()));
    	while(i!=review.size())
    	{
    		writer.append("`");//` splits between different reviews in array list
    		writer.append(review.get(i).getReviewer());
        	writer.append("'");
        	writer.append(review.get(i).getProse());
        	writer.append("'");
        	writer.append(String.valueOf(review.get(i).getRating()));
        	i++;
    	}
    	
    	writer.append("\n");
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    	
    }
	
    public ArrayList<Movie> readMovieCSV() throws FileNotFoundException, IOException{ //Read CSV
    	ArrayList<Movie> list = new ArrayList<Movie>(); //Create array list of movies
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Title";
    	while ((line = br.readLine()) != null) {
    		
    		String[] split = line.split(",", 12); //Convert each column to individual attributes
    		if(split[0].equals(header)) //Ignore header
    			continue;
    		
    		String title = split[0];
    		String synopsis = split[1];
    		String director = split[2];
    		ArrayList<String> cast = new ArrayList<String>(Arrays.asList(split[3].split("'")));//' splits between different cast members

    		MovieType type = MovieType.valueOf(split[4]);
    		AgeRating ageRating = AgeRating.valueOf(split[5]);
    		MovieStatus status = MovieStatus.valueOf(split[6]);
    		int totalSales = Integer.parseInt(split[7]);
    		double averageRating = Double.parseDouble(split[8]);
    		String genre = split[9];
    		String runTime = split[10];
    		
    		ArrayList<String> reviewStr = new ArrayList<String>(Arrays.asList(split[11].split("`")));//` splits between different reviews in array list
    		ArrayList<Review> reviews = new ArrayList<Review>();
    		int i = 0;
    		while(i!=reviewStr.size())
    		{
    			String[] splitReview = reviewStr.get(i).split("'");//' splits between different attributes of review
    			String reviewer = splitReview[0];
    			String proseReview = splitReview[1];
    			int ratingReview = Integer.valueOf(splitReview[2]);
    			Review tempReview = new Review(reviewer,ratingReview,proseReview);
    			reviews.add(tempReview);
    			i++;
    		}
    		
    		
    		//Create a movie object
    		Movie tempMovie = new Movie(title,synopsis,director,cast,type,ageRating,status,totalSales,averageRating,genre,runTime,reviews);
    		//And add it to the array list
    		list.add(tempMovie);
    	}
    	
    	return list;
    }
    
    public static int findMovieCSV(String title, ArrayList<Movie> list) throws FileNotFoundException, IOException{ //Find position of movie in array list
    	int i = 0;
    	while(i!=list.size()) {
    		if(list.get(i).getTitle().equals(title)) //Match title
    			return i; //Return position
    		i++;
    	}
    	return -1; //If not present, return -1
    }
    
    public void writeMovieCSV(ArrayList<Movie> list) throws FileNotFoundException, IOException{
    	//FileWriter to write to CSV, no true because it's rewriting    	
    	FileWriter writer = new FileWriter(filename);
    	
    	int i =0;
    	while(i!=list.size()) //Go through every movie in array list
    	{
    		//Get every attribute
    		Movie movie = list.get(i);
        	String title = movie.getTitle();
        	String synopsis = movie.getSynopsis();
        	String director = movie.getDirector();
        	ArrayList<String> cast = movie.getCast();
        	MovieType type = movie.getType();
        	AgeRating ageRating = movie.getAgeRating();
        	MovieStatus status = movie.getMovieStatus();
        	int totalSales = movie.getTotalSales();
        	double averageRating = movie.getAverageRating();
        	String genre = movie.getGenre();
        	String runtime = movie.getrunTime();
        	
        	ArrayList<Review> review = movie.getReviews();
        	
        	//And add it to the CSV
        	writer.append(title);
        	writer.append(",");
        	writer.append(synopsis);
        	writer.append(",");
        	writer.append(director);
        	writer.append(",");
        
        	int j=1;
        	writer.append(cast.get(0));
        	while(j!=cast.size()) { //Adding cast into one column of CSV
        		writer.append("'");//' splits between different cast members
        		writer.append(cast.get(j));
        		j++;
        	}
        	writer.append(",");
        	writer.append(type.toString());
        	writer.append(",");
        	writer.append(ageRating.toString());
        	writer.append(",");
        	writer.append(status.toString());
        	writer.append(",");
        	writer.append(String.valueOf(totalSales));
        	writer.append(",");
        	writer.append(String.valueOf(averageRating));
        	writer.append(",");
        	writer.append(genre);
        	writer.append(",");
        	writer.append(runtime);
        	
        	int k=1;
        	writer.append(review.get(0).getReviewer());
        	writer.append("'");//' splits between different attributes of review
        	writer.append(review.get(0).getProse());
        	writer.append("'");
        	writer.append(String.valueOf(review.get(0).getRating()));
        	while(k!=review.size())
        	{
        		writer.append("`");//` splits between different reviews in array list
        		writer.append(review.get(k).getReviewer());
        		writer.append("'");
        		writer.append(review.get(k).getProse());
            	writer.append("'");
            	writer.append(String.valueOf(review.get(k).getRating()));
            	k++;
        	}
        	writer.append("\n");
        	i++;
    	}
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    }
    
    public ArrayList<Movie> removeMovieCSV(ArrayList<Movie> list, String title) throws FileNotFoundException, IOException{ //Remove movie from array list
    	int i = findMovieCSV(title,list); //Find position of movie
    	if(i!=-1) {
    		list.remove(i); // And remove it
    		System.out.println("Movie removed!");
    		return list;
    	}
    	System.out.println("Movie does not exist! Exiting...");
		writeMovieCSV(list);
    	return list; //Return list if movie not present
    }
    
    /*public void updateAverageRating() throws FileNotFoundException, IOException {
    	MovieInfoManager2 mm = new MovieInfoManager2();
    	ArrayList<Movie> list = mm.readMovieCSV();
    	int rating = 0;
    	int i = 0;
    }*/
    
    public void printAll(ArrayList<Movie> list) {
    	int i = 0;
    	while(i!=list.size()) {
    		Movie tempMovie = list.get(i);
    		System.out.println("Title: " + tempMovie.getTitle());
    		System.out.println("Synopsis: " + tempMovie.getSynopsis());
    		System.out.println("Director: " + tempMovie.getDirector());
    		ArrayList<String> cast = tempMovie.getCast();
    		int j = 0;
    		while(j!=cast.size())
    		{
    			System.out.println("Cast member " + j+1 + " : " +cast.get(j));
    			j++;
    		}
    		System.out.println("Movie type: " + tempMovie.getType());
    		System.out.println("Age Rating: " + tempMovie.getAgeRating());
    		System.out.println("Average Rating: " + tempMovie.getAverageRating());
    		System.out.println("Genre: " + tempMovie.getGenre());
    		System.out.println("Runtime: " + tempMovie.getrunTime());
    		i++;
    	}
    	return;
    }
    
    public void printOne(ArrayList<Movie> list, int index) {
    	Movie tempMovie = list.get(index);
    	System.out.println("Title: " + tempMovie.getTitle());
		System.out.println("Synopsis: " + tempMovie.getSynopsis());
		System.out.println("Director: " + tempMovie.getDirector());
		ArrayList<String> cast = tempMovie.getCast();
		int j = 0;
		while(j!=cast.size())
		{
			System.out.println("Cast member " + j+1 + " : " +cast.get(j));
			j++;
		}
		System.out.println("Movie type: " + tempMovie.getType());
		System.out.println("Age Rating: " + tempMovie.getAgeRating());
		System.out.println("Average Rating: " + tempMovie.getAverageRating());
		System.out.println("Genre: " + tempMovie.getGenre());
		System.out.println("Runtime: " + tempMovie.getrunTime());
    }


	public void rankByRatings(boolean byTicketSales) throws FileNotFoundException, IOException {
		ArrayList<Movie> list = readMovieCSV();
		int len = list.size();
		if (len > 5) len = 5;
		if(byTicketSales) {
			Collections.sort(list, new Comparator<Movie>() {
				@Override
				public int compare(Movie c1, Movie c2) {
					return Integer.compare(c2.getTotalSales(), c1.getTotalSales());
				}
			});
			System.out.println("=====================================================================================\n");
			System.out.println("                          Top 5 Movies based on Total Sales:                         \n");
			System.out.println("=====================================================================================\n");
			for(int i = 0; i < len; i++) {
				Movie tempMovie = list.get(i);
				System.out.println("Movie Title: " + tempMovie.getTitle());
				System.out.println("Total Ticket Sales: " + tempMovie.getTotalSales());
				System.out.println();
			}
		} else {
			Collections.sort(list, new Comparator<Movie>() {
				@Override
				public int compare(Movie c1, Movie c2) {
					return Double.compare(c2.getAverageRating(), c1.getAverageRating());
				}
			});
			System.out.println("=====================================================================================\n");
			System.out.println("                          Top 5 Movies based on Average Ratings:                     \n");
			System.out.println("=====================================================================================\n");
			for(int i = 0; i < len; i++) {
				Movie tempMovie = list.get(i);
				System.out.println("Movie Title: " + tempMovie.getTitle());
				System.out.println("Average Rating: " + tempMovie.getAverageRating());
				System.out.println();
			}
		}
	}
	
	/*public static void main (String[] args) throws FileNotFoundException, IOException {
		MovieInfoManager2 mm = new MovieInfoManager2();
		ArrayList<String> cast = new ArrayList<String>();
		cast.add("dude1");
		cast.add("add2");
		ArrayList<Review> review = new ArrayList<Review>();
		Review review1 = new Review("john",5,"alien good");
		Review review2 = new Review("jack",1,"alien bad");
		review.add(review1);
		review.add(review2);
		Movie movie = new Movie("alien","alien bad","guy",cast,MovieType.TWOD,AgeRating.NC16,MovieStatus.NOW_SHOWING,0,0,"Action/Horror","2H30M",review);
		mm.addMoviecsv(movie);
		ArrayList<Movie> list = mm.readMovieCSV();
		System.out.println(list.get(0).getReviews().get(0).getProse());
		System.out.println(list.get(0).getReviews().get(0).getReviewer());
	}*/
}