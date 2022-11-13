package View;
import java.io.IOException;
import java.util.Scanner;

import entities.Showtime;
import model.ShowtimeManager;

import java.io.FileNotFoundException;


/**
 * Displays the all or ONE movie from searching the database, showtimes.csv for movies
 * @author 
 * @version 1.0
 * @since 1.0
 */

// SearchMovieUI is a Movie-goer module
/*
1. Search/List movie
2. View movie details – including reviews and ratings
	@@ -18,94 +23,57 @@ 
    */
    public class SearchMovieUI {
    private String type;
    private int option;
    private int length;
    public final static String FILENAME = "Moblima/src/Data/Showtimes.csv";
    private ShowtimeManager s1;


    public SearchMovieUI(){
        s1 = new ShowtimeManager(FILENAME);
    }


    public static void main(String args[]){
        new SearchMovieUI();
        }


    public void DisplayAll(){
        Showtime[] lmaoArr;
        try{
            lmaoArr = s1.getDataAll();
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
            for(int i=0; i<lmaoArr.length; i++){
                if(i==0){continue;}
                if(i == index){
                    flag = 1;
                    System.out.println(String.format("Entry %s: %s", i, lmaoArr[i].getShowTimeDetails()));
                    break;
                }

            }
            if(flag == 0){
                System.out.println("Invalid Range");
            }
        }
        catch(FileNotFoundException para1){
            System.out.println("Error! FileNotFoundException ");
        }
        catch(IOException para2){
            System.out.println("Error! IOException ");
        }
    }

}
