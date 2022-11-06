package database;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import system.Showtime;

/* 
 * HOW TO USE:
 * MovieInfoManager() calls "Moblima/src/Data/movieInformation.csv"
 * getLength() returns the length (int)
 * getDataAll() iterates and returns all rows, except the first
 * printAll() prints the data in getDataAll()
 * 
 */

public class MovieInfoManager {
    private String filename;
    private MovieInfo[] data;
    private Showtime temp;

    public final static String FILENAME = "Moblima/src/Data/movieInformation.csv";



    public MovieInfoManager(){
        this.filename = FILENAME;
    }

    public MovieInfoManager(String filename) {
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

    public MovieInfo[] getDataAll() throws FileNotFoundException, IOException{

        data = new MovieInfo[getLength()];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                List<String> list = Arrays.asList(values);
                    data[count] = new MovieInfo(Integer.parseInt(list.get(0)), list.get(1), list.get(2),list.get(3),list.get(4),list.get(5), list.get(6), list.get(10),list.get(11),list.get(12));
    
                count++;
            }
        }
        return data;
    }

    public void PrintAll(){
        System.out.println("=====================================================================================");
        System.out.println("                   Viewing Movie Details (Displaying ALL)                            ");
        System.out.println("=====================================================================================");
        for(int i =0;i<data.length;i++){
            if(i == 0){
                continue;
            }
            System.out.println(data[i].getAll());
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public void PrintOne(int index){
        //INDEX CANNOT START FROM 0
        int flag = 0;
        

            if(index> 0 && index<data.length){
                System.out.println("=====================================================================================");
                System.out.printf("                   Viewing Movie Details (Displaying %s)                            \n",data[index].getTitle());
                System.out.println("=====================================================================================");
                System.out.println(data[index].getAll());
                System.out.println("-------------------------------------------------------------------------------------");
                flag = 1;
                
            
        }
        if(flag == 0){
            System.out.println("Invalid Range!");
        }
    }

}
    
    
        
