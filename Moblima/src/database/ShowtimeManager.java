package src.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList; // import the ArrayList class
import src.system.Showtime;

public class ShowtimeManager {
    private String filename;
    private Showtime[] data;
    private Showtime temp;

    public void ShowtimeManager(String filename) {
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

    public Showtime[] getDataAll(int length) throws FileNotFoundException, IOException{
        
        
        data = new Showtime [length];
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
                
                data[count] = new Showtime() ;
                data[count].Showtime(Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)), list.get(0), list.get(3));
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

    public ArrayList getShowtimes(String movie, String cineplex) throws FileNotFoundException, IOException{

        ArrayList<Showtime> showtimes = new ArrayList();
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
                if (list.get(3).equals(movie)){
                    temp = new Showtime() ;
                    temp.Showtime(Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)), list.get(0), list.get(3));
                    showtimes.add(temp);
                    count++;
                    continue;

                }
                
            }
        }
        return showtimes;
     }

}
