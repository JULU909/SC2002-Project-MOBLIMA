package model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import entities.Showtime;

import java.util.ArrayList;
import java.io.FileWriter; //for writing to csv

/**
 * This class allows for the location/adding/editing/removing of show times from Showtimes.csv
 * @author 
 * @version 1.0
 * @since 2022-11-13
 */


public class ShowtimeManager  {
    private String filename;
    private Showtime[] data;
    private Showtime temp;

    public final static String FILENAME = /*new File("Showtimes.csv").getAbsolutePath();*/"Moblima/src/Data/Showtimes.csv";

    public ShowtimeManager(){   
        this.filename = FILENAME;
    }



    public ShowtimeManager(String filename) {
        this.filename = filename;
    }

    /**
     * This method gets the length of the csv file
     * @return Returns an int, which denotes the length of the rows of the file
     * @throws FileNotFoundException
     * @throws IOException
     */

    public int getLength()throws FileNotFoundException, IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
            }
        }
        return count;
    }
    // object.getDataAll(object.getLength())


    /**
     * This method gets all of the Showtime data in the csv file
     * @return Returns an array of Showtime Objects
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Showtime[] getDataAll() throws FileNotFoundException, IOException{

        data = new Showtime [getLength()];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                
                
                data[count] = new Showtime(Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)), list.get(0), list.get(3));
                count++;
            }
        }
        return data;
    }

    /**
     * This method gets all of the Movie data in the csv file
     * @return Returns an array of String Objects
     * @throws FileNotFoundException
     * @throws IOException
     */

    public String[] getMovies(int length) throws FileNotFoundException, IOException{ //get all movies in csv

       String [] names = new String [10];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                
               for (int i = 0 ; i  < names.length ; i++ ){
                if (list.get(3).equals(names[i])){
                    break;
                }

                if (i == names.length-1 && count<=10 ){
                        names[count-1] = list.get(3);
                        count++;
                }
               }
            }
        }
       
        return names;
    }
    /**
     * This method will get all show times of a stated movie,cineplex and date
     * @param movie , the title of the movie
     * @param cineplex, the name of the cineplex
     * @param date, the date of the show time
     * @return An array list of show times that meet the 3 criteria
     */
    public ArrayList<Showtime> getShowtimes(String movie, String cineplex , int date) throws FileNotFoundException, IOException{ //Get all show times of certain movie and cineplex in CSV

        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        ArrayList<Showtime> list = new ArrayList<Showtime>();
        ShowtimeManager sm = new ShowtimeManager();
        showtimes = sm.readShowtimecsv(); //Get all show times out into array list
                int i = 0;
                while(i!=showtimes.size())
                {
                	if(showtimes.get(i).getMovie().equals(movie) && showtimes.get(i).getCineplex().equals(cineplex) && (showtimes.get(i).getDate() == date)) //Find rows with same movie and cineplex
                	{
                		list.add(showtimes.get(i)); // Add it to list array list
                	}
                	i++; //Increment to continue looking
                }
                return list; //Return list of show times of certain movie and cineplex
            }
        
     
    /**
     * This method will find the index of a specified show time 
     * @param list, the list of all show times in Showtimes.csv
     * @param showtime, the specific show time the user wishes to find
     * @return The index of the show time in the array list
     */
    public static int findShowtimecsv(ArrayList<Showtime> list, Showtime showtime)throws FileNotFoundException, IOException{ //Find index in array list
    	int i = 0;

    	while(i!=list.size()) { //Look through array list
    		if(list.get(i).getMovie().equals(showtime.getMovie()) //Find exact match
    				&& list.get(i).getDate() == showtime.getDate() 
    				&& list.get(i).getCineplex().equals(showtime.getCineplex()) 
    				&& list.get(i).getTime() == showtime.getTime()
    				&& list.get(i).getCinemaType().equals(showtime.getCinemaType()))
    			return i; //Return its position
    		i++; //Increment if not found
    	}
    	return -1; //Return -1 if its not in array list
    	
    }
    /**
     * This method adds a show time into Showtimes.csv
     * @param showtime, the show time to be added
     */
    public void addShowtimecsv(Showtime showtime) throws FileNotFoundException, IOException{ //Add one show time to CSV
    	
    	//FileWriter to write to csv, true to allow appending    	
    	FileWriter writer = new FileWriter(filename,true);
    	
    	//Convert show time to individual attributes
    	String cineplex = showtime.getCineplex(); 
    	int time = showtime.getTime();
    	int date = showtime.getDate();
    	String movie = showtime.getMovie();
    	String cinemaType = showtime.getCinemaType();
    	
    	//Append them all into CSV
    	writer.append(cineplex);
    	writer.append(",");
    	writer.append(String.valueOf(time));
    	writer.append(",");
    	writer.append(String.valueOf(date));
    	writer.append(",");
    	writer.append(movie);
    	writer.append(",");
    	writer.append(cinemaType);
    	writer.append("\n");
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    
    }
    /**
     * This method will read every show time in Showtimes.csv
     * @return An array list of every show time in Showtimes.csv
     */
    public ArrayList<Showtime> readShowtimecsv() throws FileNotFoundException, IOException{ //Read whole CSV
    	ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Cineplex";
    	while ((line = br.readLine()) != null) {
    		
    		String split[] = line.split(",", 5); //convert each column to individual attributes
    		if(split[0].equals(header)) //ignore header
    			continue;
    		
    		String cineplex = split[0];
    		int time = Integer.parseInt(split[1]);
    		int date = Integer.parseInt(split[2]);
    		String movie = split[3];
            String type = split[4];
    		Showtime tempTime = new Showtime (time,date,cineplex,movie); //create show time object with each attribute
            tempTime.setCinemaType(type);
    		showtimes.add(tempTime); //And add show time to array list
    	}
    	
    	return showtimes; //return array list
    }
    /**
     * This method removes a specific show time in Showtimes.csv
     * @return The array list of show times after removal
     */
    public ArrayList<Showtime> removeShowtimecsv(ArrayList<Showtime> list, Showtime showtime) throws FileNotFoundException, IOException{ //Remove from array list, use for editing/remove a row
    	int i = findShowtimecsv(list,showtime); //Find its position
    	
    	if(i!=-1) //If show time present
    	{
    		list.remove(i); //Remove it
    		System.out.println("Showtime removed!");
    		return list; //Return list
    	}
    	
    	System.out.println("Showtime does not exist! Exiting..."); //If not found
    	return list; //Just return list
    }
    /**
     * This method writes every show time in the array list.
     * This will overwrite Showtimes.csv
     * @param list , The array list of all show times that is to be written to Showtime.csv
     */
    public void writeShowtimecsv(ArrayList<Showtime> list) throws FileNotFoundException, IOException { //Rewrite CSV, use for editing/removing a row

    	//FileWriter to write to CSV, no true because it's rewriting    	
    	FileWriter writer = new FileWriter(filename);
    	
    	int i =0;
    	while(i!=list.size()) {
    		//Convert show time to each attribute
    		String cineplex = list.get(i).getCineplex(); 
        	int time = list.get(i).getTime();
        	int date = list.get(i).getDate();
        	String movie = list.get(i).getMovie();
        	String cinemaType = list.get(i).getCinemaType();
        	
        	//Append them all into CSV
        	writer.append(cineplex);
        	writer.append(",");
        	writer.append(String.valueOf(time));
        	writer.append(",");
        	writer.append(String.valueOf(date));
        	writer.append(",");
        	writer.append(movie);
        	writer.append(",");
        	writer.append(cinemaType);
        	writer.append("\n");
        	
        	i++;
    	}
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    }
    
}