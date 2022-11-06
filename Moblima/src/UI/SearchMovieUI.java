package UI;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import system.Showtime;

import database.ShowtimeManager;


// SearchMovieUI  is a Movie-goer module
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
    public final static String FILENAME = "Moblima/src/Data/movieInformation.csv";
    ShowtimeManager s1 = new ShowtimeManager();
    
  
    public SearchMovieUI(){
        this.s1 = new ShowtimeManager(FILENAME);
    }


    public void main(String args[]){
        Scanner sc = new Scanner(System.in);
        while(choice != -1){
            System.out.println("Input the following fields:");
            System.out.println("Choice 1: Search/List Movie");
            System.out.println("Choice 2: Display all Movie details, including reviews and ratings");
            System.out.println("Choice -1: exit");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                
                break;

                case 2:
                s1.getDataAll();

                break;

                default:
                System.out.println("Your choice: " + choice + " was not a valid input ");
                break;
            }
        }
    
    
    }
    
    public Showtime[] DisplayAll(){
        s1.getDataAll()

    }
    public int getLength(){
        return s1.getLength();
    }
}
      
    public Showtime[] getDataAll(int length) 
        
   
    public String[] getMovies(int length)


    public ArrayList getShowtimes(String movie, String cineplex) 
}
