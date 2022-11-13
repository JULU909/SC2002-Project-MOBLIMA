package View;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Displays the Search Movie UI for existing customers
 * @author 
 * @version 1.0
 * @since 1.0
 */

public class SearchingMoviesMenuUI {
    public static void execute(){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("=====================================================================================\n");
            System.out.println("                          Searching Movies                                           \n");
            System.out.println("=====================================================================================\n");
            System.out.println("Do you want to Display All Movies or Display one Movie? \n1) Display All Movies\n2) Display ONE movie (by index, cannot be 0 or negative)\n");
            System.out.println("Your Choice please: ");

            
            try {
                System.out.print("\nWhat is your choice:  ");
                choice = sc.nextInt(); sc.next();
                if (choice >=1 && choice <=2) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter a valid option");
                }  
        } while (choice<1 || choice>2);

        switch (choice) {
            case 1:
                new SearchMovieUI().DisplayAll();
                break;
            case 2:
                choice = 0;
                do {
                    try {
                        System.out.println("\nSelect the index of the movie you want to display: ");
                        choice = sc.nextInt(); sc.next();
                        if (choice < 0) throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Please enter a valid option");   
                        }
                } while (choice < 0);    
                new SearchMovieUI().DisplayOne(choice);
                break;
        }
    }
}
