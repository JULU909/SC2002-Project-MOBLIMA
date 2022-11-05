package UI;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import system.Showtime;

import database.ShowtimeManager;


// SearchMovieUI is a Movie-goer module
/*
1. Search/List movie
2. View movie details – including reviews and ratings
 */
public class SearchMovieUI {
    public int choice = 0;
    private String title;
    private String type;
    private int option;
    private int length;
    public final static String FILENAME = "Moblima/src/Data/Showtimes.csv";
    private ShowtimeManager s1;
  
    
    public SearchMovieUI(){
        s1 = new ShowtimeManager(FILENAME);
        // Scanner sc = new Scanner(System.in);
        // while(choice != -1){
            // System.out.println("Input the following fields:");
            // System.out.println("Choice 1: Search/List Movie");
            // System.out.println("Choice 2: Display all Movie details, including reviews and ratings");
            // System.out.println("Choice -1: exit");
            // choice = sc.nextInt();

        //     switch(choice){
        //         case 1:
                
        //         break;

        //         case 2:
        //         DisplayAll();
        //         break;

        //         default:
        //         System.out.println("Your choice: " + choice + " was not a valid input ");
        //         break;
        //     }
        // }
        // sc.close();
    }


    public static void main(String args[]){
        new SearchMovieUI();
        }
    
    
    public void DisplayAll(){
        Showtime[] lmaoArr;
        try{
            lmaoArr = s1.getDataAll();
            System.out.println("=====================================================================================");
            System.out.println("                        Searching Movies (Displaying ALL)                            ");
            System.out.println("=====================================================================================");
            for(int i=0; i<lmaoArr.length; i++){
                if(i==0){continue;}
                System.out.println(String.format("Entry %s: %s", i, lmaoArr[i].getShowTimeDetails()));
            }
        }
        catch(FileNotFoundException para1){
            System.out.println("Error! FileNotFoundException ");
        }
        catch(IOException para2){
            System.out.println("Error! IOException ");
        }

    }

    public void DisplayOne(int index){
        Showtime[] lmaoArr;
        int flag = 0;
        try{
            lmaoArr = s1.getDataAll();
            System.out.println("=====================================================================================");
            System.out.printf("                   Searching Movies (Displaying %s)                            \n",lmaoArr[index].returnTitle());
            System.out.println("=====================================================================================");
            int i = index;
            if(i == 0){System.out.println("Integer input cannot be a value of 0.");}

            if(i < lmaoArr.length && i > 0){
                flag = 1;
                System.out.println(String.format("Entry %s\n%s", i, lmaoArr[i].getShowTimeDetails()));
            
            }
            if(flag == 0){
                System.out.println("Invalid Range");
            }

            // for(int i=0; i<lmaoArr.length; i++){
            //     if(i==0){continue;}
            //     if(i == index){
            //         flag = 1;
            //         System.out.println(String.format("Entry %s: %s", i, lmaoArr[i].getShowTimeDetails()));
            //         break;
            //     }

            // }
            // if(flag == 0){
            //     System.out.println("Invalid Range");
            // }
        }
        catch(FileNotFoundException para1){
            System.out.println("Error! FileNotFoundException ");
        }
        catch(IOException para2){
            System.out.println("Error! IOException ");
        }
    }
    

      
    // public Showtime[] getDataAll(int length);
        
   
    // public String[] getMovies(int length);


    // public ArrayList getShowtimes(String movie, String cineplex);
}
