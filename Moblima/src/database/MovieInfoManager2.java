package database;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import enums.AgeRating;
import enums.MovieStatus;
import enums.MovieType;

import java.util.ArrayList;
import system.Showtime;

import java.io.FileWriter; //for writing to csv

public class MovieInfoManager2 {
	private String filename;

    public final static String FILENAME = "Moblima/src/Data/movieInformation2.csv";



    public MovieInfoManager2(){
        this.filename = FILENAME;
    }

    public MovieInfoManager2(String filename) {
        this.filename = filename;
    }
    
    
    public void addMoviecsv(Movie movie) throws FileNotFoundException, IOException{
    	FileWriter writer = new FileWriter(filename,true);
    	
    	String title = movie.getTitle();
    	String synopsis = movie.getSynopsis();
    	String director = movie.getDirector();
    	ArrayList<String> cast = movie.getCast();
    	MovieType type = movie.getType();
    	AgeRating ageRating = movie.getAgeRating();
    	int totalSales = movie.getTotalSales();
    	double averageRating = movie.getAverageRating();
    	String genre = movie.getGenre();
    	String runtime = movie.getrunTime();
    	
    	writer.append(title);
    	writer.append(",");
    	writer.append(synopsis);
    	writer.append(",");
    	writer.append(director);
    	writer.append(",");
    	writer.append("' ");
    	
    	int i = 1;
    	writer.append(cast.get(i));
    	while(i!=cast.size()) { //adding cast into one column of csv
    		writer.append(" | ");
    		writer.append(cast.get(i));
    		i++;
    	}
    	writer.append(" '");
    	writer.append(",");
    	writer.append(type.toString());
    	writer.append(",");
    	writer.append(ageRating.toString());
    	writer.append(",");
    	writer.append(String.valueOf(totalSales));
    	writer.append(",");
    	writer.append(String.valueOf(averageRating));
    	writer.append(",");
    	writer.append(genre);
    	writer.append(",");
    	writer.append(runtime);
    	writer.append("\n");
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    	
    	
    	
    	
    }
   /* public ArrayList<Movie> readMovieCSV() throws FileNotFoundException, IOException{
    	ArrayList<Movie> list = new ArrayList<Movie>();
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Title";
    	while ((line = br.readLine()) != null) {
    		
    		String split[] = line.split(",", 10); //convert each column to individual arguments
    		if(split[0].equals(header)) //ignore header
    			continue;
    		
    		String title = split[0];
    		String synopsis = split[1];
    		String director = split[2];
    	}*/
   // }
public static void main(String [] args) throws FileNotFoundException, IOException {
	ArrayList<String> cast = new ArrayList<String>();
	String filepath = new File("movieInformation2.csv").getAbsolutePath();
	MovieInfoManager2 mm = new MovieInfoManager2(filepath);
	cast.add("john");
	cast.add("jack");
	cast.add("jhin");
	Movie test = new Movie("alien","prose","dude",cast,MovieType.TWOD,AgeRating.NC16,MovieStatus.PREVIEW,"Action/Horror","2H15M");
	mm.addMoviecsv(test);
	System.out.println("Done");
}
}
