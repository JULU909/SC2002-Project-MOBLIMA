package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import entities.Movie;
import entities.Review;
import enums.AgeRating;
import enums.MovieStatus;
import enums.MovieType;

import java.util.ArrayList;

import java.io.FileWriter; //for writing to csv

/**	Reads and writes to the CSV containing movie information
 * @author Tham Holdon
 * @author Jia Wei
 * @version 1.0
 * @author Kit Ye
 */
public class MovieInfoManager  extends FileManager{
	private String filename;
    public final static String FILENAME = /*new File("movieinformation.csv").getAbsolutePath();*/"Moblima/src/Data/movieinformation.csv";


	/**
     * Constructs the MovieInfoManager with the default file name
     */
    public MovieInfoManager(){
		super(FILENAME);
        this.filename = FILENAME;
    }
	/**
     * Constructs the MovieInfoManager with the inputed file name
	 * @param filename The name of the string
     */
    public MovieInfoManager(String filename) {
		super(filename);
        this.filename = filename;
		
    }
    
    /**
     * This methods adds a movie into the CSV
     * @param movie
     * @throws FileNotFoundException
     * @throws IOException
     */
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
		writer.append("`");
		writer.append("`");
		writer.append(synopsis);
		writer.append("`");
		writer.append("`");
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
    	if(review.size()==0)
    	{
    		writer.append("\n");
        	
    		//cleanup
        	writer.flush();
        	writer.close();
    		return;
    	}
    		
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
    /**
     * This method will read the movie CSV
     * @return Returns an array list of movies for the code to read or write
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ArrayList<Movie> readMovieCSV() throws FileNotFoundException, IOException{ //Read CSV
    	ArrayList<Movie> list = new ArrayList<Movie>(); //Create array list of movies
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Title";
    	while ((line = br.readLine()) != null) {
    		
    		String split[] = line.split("``", 0); //Convert each column to individual attributes
			if(split[0].equals(header)) continue; //Ignore header
			String title = split[0].split(",")[0];
			String synopsis = split[1];
    		split = split[2].split(",", 0); //Convert each column to individual attributes
    		String director = split[1];
    		ArrayList<String> cast = new ArrayList<String>(Arrays.asList(split[2].split("'"))); //' splits between different cast members

    		MovieType type = MovieType.valueOf(split[3]);
    		AgeRating ageRating = AgeRating.valueOf(split[4]);
    		MovieStatus status = MovieStatus.valueOf(split[5]);
    		int totalSales = Integer.parseInt(split[6]);
    		double averageRating = Double.parseDouble(split[7]);
    		String genre = split[8];
    		String runTime = split[9];
    		int size = split.length;
    		if(size<11) {
    			//If no reviews written, split[10] will not exist
    				ArrayList<Review> reviews = new ArrayList<Review>();
        			Movie tempMovie = new Movie(title,synopsis,director,cast,type,ageRating,status,totalSales,averageRating,genre,runTime,reviews);
        			list.add(tempMovie);
    				continue;
    			}
    		
    		ArrayList<String> reviewStr = new ArrayList<String>(Arrays.asList(split[10].split("`")));//` splits between different reviews in array list
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
    /**
     * This method will read the movie CSV and return the list of movies that have status PREVIEW and NOWSHOWING
     * @return Returns an array list of movies for the code to read or write
     * @throws FileNotFoundException
     * @throws IOException
     */	
	public ArrayList<Movie> findShowingMovies() throws FileNotFoundException, IOException{ //Remove movie from array list
    	ArrayList<Movie> list = readMovieCSV();
		ArrayList<Movie> target = new ArrayList<Movie>();
		for (Movie movie : list){
			if (movie.getMovieStatus() == MovieStatus.PREVIEW || movie.getMovieStatus() == MovieStatus.NOW_SHOWING) target.add(movie);
		}
    	return target;
    }
    /**
     * This method will find a specific movie in the array list
     * @param title, the title of the movie to find
     * @param list, the array list of all movies in the CSV
     * @return Returns an int that denotes the index of the movie to be found in the CSV
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    /**
     * This method will find an exact match of a specific movie in the array list
     * @param title, the title of the movie to find
     * @param list, the array list of all movies in the CSV
     * @return the index of the movie to be found in the CSV
     */
    public static int findExactMovieCSV(String title, ArrayList<Movie> list) {
    	int i = 0;
		//title is input from user
    	while(i!=list.size()) {
    		if(list.get(i).getTitle().equals(title)) //Match title
    			return i; //Return position
    		i++;
    	}
    	return -1;
    }

	/**
     * This method will find the closest substring to the target string, current getLevenshteinDistance is smaller than 5. You can increase this number to allow for greater user input inaccuracies
     * @param user_inputed, user input String to be compared
     * @param lmaoArr_indexString, target string from array to be compared
     * @return Returns an int that denotes whether the target string can be approximately found 
	*/

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
	
    /**
     * This method writes all movies in the array list of movies into the CSV
     * @param list, the array list of movies
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeMovieCSV(ArrayList<Movie> list) throws FileNotFoundException, IOException{
    	//FileWriter to write to CSV, no true because it's rewriting    	
    	FileWriter writer = new FileWriter(filename);
    	
    	int i =0;
    	while(i<list.size()) //Go through every movie in array list
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
        	writer.append(",``");
        	writer.append(synopsis);
        	writer.append("``,");
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
        		i++;
            	continue;
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
    /**
     * This method removes a specific movie from the CSV
     * @param list, the array list of movies in the CSV
     * @param title, the title of the movie to be removed
     * @return An array list of movies that are in the CSV after removal
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    /**
     * This method calculates the average rating of the movie from all ratings related to the movie.
     * After calculating, it will update that value into the CSV
	 * @throws FileNotFoundException
     * @throws IOException
     */
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
   
    /**
     * This method updates the averageRating of a specific movie
     * @param title, the title of the movie
	 * @throws FileNotFoundException
     * @throws IOException
     */
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
    
    /**
     * This method updates the total sales of a certain movie
     * @param title , the title of the movie
     * @param sales, the number of sales of the movie
	 * @throws FileNotFoundException
     * @throws IOException
     */
    public void updateTotalSales(String title,int sales) throws FileNotFoundException, IOException {
    	ArrayList<Movie> list = readMovieCSV();//Convert CSV to Array list of movies
    	int i = findMovieCSV(title,list);//Find position of that movie in array list
    	int currentSales = list.get(i).getTotalSales();
    	list.get(i).setTotalSales(currentSales + sales);//Update its sales
    	writeMovieCSV(list); //Rewrite into CSV
    }
    
    /**
     * This method prints out all the details of all movies stored in the CSV, except for the reviews
     * @param list, the array list of movies in the CSV
     */
    public void printAll(ArrayList<Movie> list) {
    	int i = 0;
		Scanner sc = new Scanner(System.in);
		    	while(i!=list.size()) {
    		Movie tempMovie = list.get(i);
    		System.out.println("Title: " + tempMovie.getTitle());
    		System.out.println("Synopsis: " + tempMovie.getSynopsis());
    		System.out.println("Director: " + tempMovie.getDirector());
    		ArrayList<String> cast = tempMovie.getCast();
    		int j = 0;
    		while(j!=cast.size())
    		{
    			System.out.println("Cast member " + (j+1) + " : " +cast.get(j));
    			j++;
    		}
    		System.out.println("Movie type: " + tempMovie.getType());
    		System.out.println("Age Rating: " + tempMovie.getAgeRating());
    		System.out.println("Average Rating: " + tempMovie.getAverageRating());
    		System.out.println("Genre: " + tempMovie.getGenre());
    		System.out.println("Runtime: " + tempMovie.getrunTime());
			System.out.println("Showing Status: " + tempMovie.getMovieStatus());
			System.out.println();
    		i++;
    	}
		System.out.println("Press Any Key to Continue");
		sc.next();
    	return;
    }
    
    /**
     * This method prints all the details of one movie stored in the CSV, except for the reviews
     * @param list, the array list of movies in the CSV
     * @param index, the position of the movie specified in the array list
     */
    public void printOne(ArrayList<Movie> list, int index) {
    	Movie tempMovie = list.get(index);
    	System.out.println("Title: " + tempMovie.getTitle());
		System.out.println("Synopsis: " + tempMovie.getSynopsis());
		System.out.println("Director: " + tempMovie.getDirector());
		ArrayList<String> cast = tempMovie.getCast();
		int j = 0;
		while(j!=cast.size())
		{
			System.out.println("Cast member " + (j+1) + " : " +cast.get(j));
			j++;
		}
		System.out.println("Movie type: " + tempMovie.getType());
		System.out.println("Age Rating: " + tempMovie.getAgeRating());
		System.out.println("Average Rating: " + tempMovie.getAverageRating());
		System.out.println("Genre: " + tempMovie.getGenre());
		System.out.println("Runtime: " + tempMovie.getrunTime());
    }
    /**
     * This method ranks the movies either by ratings or by total number of sales
     * @param byTicketSales , true will print by ticket sales, false will print by ratings
	 * @throws FileNotFoundException
     * @throws IOException
     */
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
				if(tempMovie.getAverageRating() == 0)
					System.out.println("Average Rating: NA");
				else
					System.out.printf("Average Rating: %.2f \n" , tempMovie.getAverageRating());
				System.out.println();
			}
		}
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


