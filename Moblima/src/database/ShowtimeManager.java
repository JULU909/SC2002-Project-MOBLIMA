package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import system.Showtime;

import java.util.StringTokenizer; //for writing to csv
import java.io.FileWriter; //for writing to csv
import java.io.PrintWriter; //for writing to csv
import java.io.FileInputStream; //for writing to csv

public class ShowtimeManager {
    private String filename;
    private Showtime[] data;
    private Showtime temp;

    public final static String FILENAME = "Moblima/src/Data/Movies.csv";

    public ShowtimeManager(){
        this.filename = FILENAME;
    }



    public ShowtimeManager(String filename) {
        this.filename = filename;
    }

    

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

    public String[] getMovies(int length) throws FileNotFoundException, IOException{

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

    public ArrayList<Showtime> getShowtimes(String movie, String cineplex) throws FileNotFoundException, IOException{

        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
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
                if (list.get(3).equals(movie) && list.get(0).equals(cineplex)){

                    temp = new Showtime(Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)), list.get(0), list.get(3));
                    showtimes.add(temp);
                    count++;
                    continue;

                }
                
            }
        }
        return showtimes;
     }
    
    public void addShowtimecsv(Showtime showtime) throws FileNotFoundException, IOException{ //add one showtime to csv
    	
    	//FileWriter to write to csv, true to allow appending    	
    	FileWriter writer = new FileWriter(filename,true);
    	
    	//convert showtime to individual arguments
    	String cineplex = showtime.getCineplex(); 
    	int time = showtime.getTime();
    	int date = showtime.getDate();
    	String movie = showtime.getMovie();
    	
    	//append them all into csv
    	writer.append(cineplex);
    	writer.append(",");
    	writer.append(String.valueOf(time));
    	writer.append(",");
    	writer.append(String.valueOf(date));
    	writer.append(",");
    	writer.append(movie);
    	writer.append("\n");
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    
    }
    
   /* public static void main(String [] args) throws FileNotFoundException, IOException {
    	String filepath = new File("Showtimes.csv").getAbsolutePath(); //to get exact path to csv
    	ShowtimeManager sm = new ShowtimeManager(filepath);
    	Showtime showtime = new Showtime(1031,121122,"ALPHA","BlackAdam");
    	sm.addShowtimecsv(showtime);
    	//System.out.println("test: " + sm.getShowtimes("BlackAdam","ALPHA").get(0).getMovie());
    }*/
}