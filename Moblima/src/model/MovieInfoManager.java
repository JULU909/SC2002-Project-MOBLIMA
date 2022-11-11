package model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import enums.AgeRating;
import enums.MovieStatus;
import enums.MovieType;

import java.util.ArrayList;

import java.io.FileWriter; //for writing to csv

public class MovieInfoManager {
	private String filename;

    public final static String FILENAME = /*new File("movieInformation2.csv").getAbsolutePath();*/"Moblima/src/Data/movieInformation2.csv";



    public MovieInfoManager(){
        this.filename = FILENAME;
    }

    public MovieInfoManager(String filename) {
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
    	ArrayList<Review> review = movie.getReviews();
    	
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
    	
    	if(review.size()==0)
    	{
    		writer.append("\n");
        	
    		//cleanup
        	writer.flush();
        	writer.close();
    		return;
    	}
    	i=1;
    	writer.append(",");
    	writer.append(review.get(0).getReviewer());
    	writer.append("'");
    	writer.append(review.get(0).getProse());
    	writer.append("'");
    	writer.append(String.valueOf(review.get(0).getRating()));
    	while(i!=review.size())
    	{
    		writer.append("`");//' splits between different attributes of review
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
    		
    		String split[] = line.split(",", 12); //Convert each column to individual attributes
    		if(split[0].equals(header)) //Ignore header
    			continue;
    		
    		String title = split[0];
    		String synopsis = split[1];
    		String director = split[2];
    		ArrayList<String> cast = new ArrayList<String>(Arrays.asList(split[3].split("'"))); //' splits between different cast members

    		MovieType type = MovieType.valueOf(split[4]);
    		AgeRating ageRating = AgeRating.valueOf(split[5]);
    		MovieStatus status = MovieStatus.valueOf(split[6]);
    		int totalSales = Integer.parseInt(split[7]);
    		double averageRating = Double.parseDouble(split[8]);
    		String genre = split[9];
    		String runTime = split[10];
    		int size = split.length;
    		if(size<12) //If no reviews written, split[11] will not exist
    			return list;
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
		//title is input from user
    	while(i!=list.size()) {
    		if(list.get(i).getTitle().equals(title)) //Match title
    			return i; //Return position

			if(fuzzyMatching(title,list.get(i).getTitle()) == 1){
				return i;
			}
			i++;
    	}
    	return -1; //If not present, return -1
    }

	public static int fuzzyMatching(String user_inputed, String lmaoArr_indexString){
		//debug : System.out.printf("%d",getLevenshteinDistance(user_inputed, lmaoArr_indexString));
		if(getLevenshteinDistance(user_inputed, lmaoArr_indexString) <5){
			System.out.println("You may have a typo in your input, however, we found the closest Movie match:");
			return 1;
		}
		else{
			return 0;
		}
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
        	
        	
        	
        	if(review.size()==0)
        	{
        		writer.append("\n");
            	//cleanup
            	writer.flush();
            	writer.close();
            	return;
        	}
        	writer.append(",");
        	int k=1;
        	writer.append(review.get(0).getReviewer());
        	writer.append("'");
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
    
    public void updateAverageRating() throws FileNotFoundException, IOException { //Update all movies ratings
    	ArrayList<Movie> list = readMovieCSV(); //Convert CSV to Array list of movies
    	int i = 0;
    	int j = 1;
    	int totalRating = 0;
    	double avgRating = 0;
    	while(i!=list.size())
    	{
    		Movie tempMovie = list.get(i); //Go through whole list
    		ArrayList<Review> listReview = tempMovie.getReviews(); //Pick up all their reviews
    		while(j<=listReview.size())
    		{
    			int rating = listReview.get(j).getRating(); //Read each rating
    			totalRating+=rating; //Add all the ratings up
    			j++; //Increment j
    		}
    		if(j==0)//If no reviews
    			avgRating = 0; 
    		else if(j==1) //If only 1 review
    			avgRating = totalRating;
    		else
    			avgRating = totalRating/(j-1); //If more than 1 review EG. 3 reviews, j will be 4
    		j=1; //Reset j
    		tempMovie.setAverageRating(avgRating); //Set that rating in movie
    		list.set(i, tempMovie); //Set movie with new values
    		i++;
    	}
    	writeMovieCSV(list); //Rewrite into CSV
    }
    
    public void updateAverageRating(String title) throws FileNotFoundException, IOException { //Update movie title's rating
    	ArrayList<Movie> list = readMovieCSV();//Convert CSV to Array list of movies
    	int i = findMovieCSV(title,list);//Find position of that movie in array list
    	Movie tempMovie = list.get(i);//Get that movie
    	ArrayList<Review> listReview = tempMovie.getReviews(); //Pick up all their reviews
    	int j = 1;
    	int totalRating = 0;
    	while(j<=listReview.size())
		{
			int rating = listReview.get(j).getRating(); //Get each rating
			totalRating+=rating;// And add them up
			j++;//Increment j
		}
    	double avgRating = 0;
    	if(j==0)//If no reviews
    		avgRating = 0;
    	else if(j==1)//If only 1 review
        	avgRating = totalRating;
        else
        	avgRating = totalRating/(j-1); //If more than 1 review
		j=1;//Reset j
		tempMovie.setAverageRating(avgRating);//Set that rating in movie
		list.set(i, tempMovie);//Set movie with new values
		
		writeMovieCSV(list);//Rewrite into CSV	
    }
    
    public void updateTotalSales(String title,int sales) throws FileNotFoundException, IOException {
    	ArrayList<Movie> list = readMovieCSV();//Convert CSV to Array list of movies
    	int i = findMovieCSV(title,list);//Find position of that movie in array list
    	if(i==-1)
    	{
    		System.out.println("Movie does not exist! No changes to total sales.");
    		return;
    	}
    	list.get(i).setTotalSales(sales);//Update its sales
    	writeMovieCSV(list); //Rewrite into CSV
    }
    
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
			System.out.println();
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
			int m = Math.min(5, list.size());
			for(int i = 0; i < m; i++) {
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
			int m = Math.min(5, list.size());
			for(int i = 0; i < m; i++) {
				Movie tempMovie = list.get(i);
				System.out.println("Movie Title: " + tempMovie.getTitle());
				System.out.println("Average Rating: " + tempMovie.getAverageRating());
				System.out.println();
			}
		}
	}

	public void newMovieReview(Customer customer) throws FileNotFoundException, IOException { 
		ArrayList<Movie> list = readMovieCSV();	
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
		writeMovieCSV(list);

		System.out.println("Review successfully added!");
	}

	public int newMovieRating(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a whole number for rating and within the range of 1 to 5:");
			sc.next();
		}
		int rating = sc.nextInt();
		sc.nextLine();
		return rating;
	}

	public void viewMovieRating() throws FileNotFoundException, IOException { 
		ArrayList<Movie> list = readMovieCSV();	
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
			System.out.println(rv.reviewer+"\n");
			System.out.println("Review:");
			System.out.println(rv.prose+"\n");
			System.out.println("Rating:");
			System.out.println(rv.rating + "/5");
			System.out.println("-----------------------------------------------------------------------------------------\n\n");
		}
		System.out.println();
	}

	public int newMovieOption(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a valid option:");
			sc.next();
		}
		int movieOpt = sc.nextInt();
		sc.nextLine();
		return movieOpt;
	}
/**
 * <p>Find the Levenshtein distance between two Strings.</p>
 *
 * <p>This is the number of changes needed to change one String into
 * another, where each change is a single character modification (deletion,
 * insertion or substitution).</p>
 *
 * <p>The previous implementation of the Levenshtein distance algorithm
 * was from <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a></p>
 *
 * <p>Chas Emerick has written an implementation in Java, which avoids an OutOfMemoryError
 * which can occur when my Java implementation is used with very large strings.<br>
 * This implementation of the Levenshtein distance algorithm
 * is from <a href="http://www.merriampark.com/ldjava.htm">http://www.merriampark.com/ldjava.htm</a></p>
 *
 * <pre>
 * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
 * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
 * StringUtils.getLevenshteinDistance("","")               = 0
 * StringUtils.getLevenshteinDistance("","a")              = 1
 * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
 * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
 * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
 * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
 * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
 * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
 * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
 * </pre>
 *
 * @param s  the first String, must not be null
 * @param t  the second String, must not be null
 * @return result distance
 * @throws IllegalArgumentException if either String input {@code null}
 * @since 3.0 Changed signature from getLevenshteinDistance(String, String) to
 * getLevenshteinDistance(CharSequence, CharSequence)
 */
public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
    if (s == null || t == null) {
        throw new IllegalArgumentException("Strings must not be null");
    }

    /*
       The difference between this impl. and the previous is that, rather
       than creating and retaining a matrix of size s.length() + 1 by t.length() + 1,
       we maintain two single-dimensional arrays of length s.length() + 1.  The first, d,
       is the 'current working' distance array that maintains the newest distance cost
       counts as we iterate through the characters of String s.  Each time we increment
       the index of String t we are comparing, d is copied to p, the second int[].  Doing so
       allows us to retain the previous cost counts as required by the algorithm (taking
       the minimum of the cost count to the left, up one, and diagonally up and to the left
       of the current cost count being calculated).  (Note that the arrays aren't really
       copied anymore, just switched...this is clearly much better than cloning an array
       or doing a System.arraycopy() each time  through the outer loop.)

       Effectively, the difference between the two implementations is this one does not
       cause an out of memory condition when calculating the LD over two very large strings.
     */

    int n = s.length(); // length of s
    int m = t.length(); // length of t

    if (n == 0) {
        return m;
    } else if (m == 0) {
        return n;
    }

    if (n > m) {
        // swap the input strings to consume less memory
        final CharSequence tmp = s;
        s = t;
        t = tmp;
        n = m;
        m = t.length();
    }

    int p[] = new int[n + 1]; //'previous' cost array, horizontally
    int d[] = new int[n + 1]; // cost array, horizontally
    int _d[]; //placeholder to assist in swapping p and d

    // indexes into strings s and t
    int i; // iterates through s
    int j; // iterates through t

    char t_j; // jth character of t

    int cost; // cost

    for (i = 0; i <= n; i++) {
        p[i] = i;
    }

    for (j = 1; j <= m; j++) {
        t_j = t.charAt(j - 1);
        d[0] = j;

        for (i = 1; i <= n; i++) {
            cost = s.charAt(i - 1) == t_j ? 0 : 1;
            // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
            d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
        }

        // copy current distance counts to 'previous row' distance counts
        _d = p;
        p = d;
        d = _d;
    }

    // our last action in the above loop was to switch d and p, so p now
    // actually has the most recent cost counts
    return p[n];
}

}

