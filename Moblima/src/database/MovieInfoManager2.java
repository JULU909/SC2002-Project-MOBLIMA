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
    	MovieStatus status = movie.getMovieStatus();
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
    	//writer.append("'");
    	
    	int i = 1;
    	writer.append(cast.get(0));
    	while(i!=cast.size()) { //adding cast into one column of csv
    		writer.append("|");
    		writer.append(cast.get(i));
    		i++;
    	}
    	//writer.append("'");
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
    	writer.append("\n");
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    	
    }
    public ArrayList<Movie> readMovieCSV() throws FileNotFoundException, IOException{
    	ArrayList<Movie> list = new ArrayList<Movie>();
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Title";
    	while ((line = br.readLine()) != null) {
    		
    		String split[] = line.split(",", 11); //convert each column to individual arguments
    		if(split[0].equals(header)) //ignore header
    			continue;
    		
    		String title = split[0];
    		String synopsis = split[1];
    		String director = split[2];
    		ArrayList<String> cast = new ArrayList<String>(Arrays.asList(split[3].split("|")));
    		MovieType type = MovieType.valueOf(split[4]);
    		AgeRating ageRating = AgeRating.valueOf(split[5]);
    		MovieStatus status = MovieStatus.valueOf(split[6]);
    		int totalSales = Integer.parseInt(split[7]);
    		double averageRating = Double.parseDouble(split[8]);
    		String genre = split[9];
    		String runTime = split[10];
    		
    		Movie tempMovie = new Movie(title,synopsis,director,cast,type,ageRating,status,totalSales,averageRating,genre,runTime);
    		list.add(tempMovie);
    	}
    	
    	return list;
    }
    
    public static int findMovieCSV(String title, ArrayList<Movie> list) throws FileNotFoundException, IOException{
    	int i = 0;
    	while(i!=list.size()) {
    		if(list.get(i).getTitle().equals(title))
    			return i;
    		i++;
    	}
    	return -1;
    }
    
    public void writeMovieCSV(ArrayList<Movie> list) throws FileNotFoundException, IOException{
    	//FileWriter to write to csv, no true because it's rewriting    	
    	FileWriter writer = new FileWriter(filename);
    	
    	int i =0;
    	while(i!=list.size())
    	{
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
        	
        	writer.append(title);
        	writer.append(",");
        	writer.append(synopsis);
        	writer.append(",");
        	writer.append(director);
        	writer.append(",");
        	//writer.append("'");
        
        	int j=1;
        	writer.append(cast.get(0));
        	while(j!=cast.size()) { //adding cast into one column of csv
        		writer.append("|");
        		writer.append(cast.get(j));
        		j++;
        	}
        	//writer.append("'");
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
        	writer.append("\n");
        	i++;
    	}

    	
    	//cleanup
    	writer.flush();
    	writer.close();
    }
    
    public ArrayList<Movie> removeMovieCSV(ArrayList<Movie> list, String title) throws FileNotFoundException, IOException{
    	int i = findMovieCSV(title,list);
    	if(i!=-1) {
    		list.remove(i);
    		System.out.println("Movie removed!");
    		return list;
    	}
    	System.out.println("Movie does not exist! Exiting...");
    	return list;
    }
    
    

/*public static void main(String [] args) throws FileNotFoundException, IOException {
	ArrayList<String> cast = new ArrayList<String>();
	String filepath = new File("movieInformation2.csv").getAbsolutePath();
	MovieInfoManager2 mm = new MovieInfoManager2(filepath);
	cast.add("john");
	cast.add("jack");
	cast.add("jhin");
	Movie test = new Movie("alien","prose","dude",cast,MovieType.TWOD,AgeRating.NC16,MovieStatus.PREVIEW,"Action/Horror","2H15M");
	mm.addMoviecsv(test);
	Movie test2 = new Movie("aliens","prose","dude",cast,MovieType.TWOD,AgeRating.NC16,MovieStatus.PREVIEW,"Action/Horror","2H15M");
	mm.addMoviecsv(test2);
	ArrayList<Movie> list = mm.readMovieCSV();
	System.out.println("1: " + list.get(0).getTitle() + " 2.: " + list.get(1).getTitle());
	System.out.println("Done");
}*/

}
