package View;
import enums.*;

import java.util.ArrayList; // import the ArrayList class

import java.util.Arrays;
import java.util.Scanner;

import entities.Customer;
import entities.Seat;
import entities.Showtime;
import entities.Ticket;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


/**
 * Displays the Login Display for our main application, MOBLIMA
 * @version 1.0
 * @since 1.0
 */

public class LoginDisplay {
    Scanner sc = new Scanner(System.in);
	/**
	 * This method prints the exit text when a user enters Moblima
	 */
    public void printBanner(){
        System.out.println("=====================================================================================\n"+
                           "=====================================================================================\n"+
                           "        Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)       \n"+
                           "=====================================================================================\n"+
                           "=====================================================================================\n");
    }
	/**
	 * This method prints all the login and registration text for a staff member to interact with
	 */
    public int getMenuOption(){
        int choice =-1;
        do {
			System.out.println("1. CUSTOMER Login");
			System.out.println("2. CUSTOMER Registration");
			System.out.println("3. STAFF Login");
			System.out.println("4. Exit Moblima");
			try {
				System.out.print("\nWhat is your choice:  ");
				choice = sc.nextInt(); sc.nextLine();	
				if (choice >=1 && choice <=4) break;
				else throw new Exception();
			} catch (Exception e) {
					System.out.println("Please enter a valid option");
			}
		} while (choice <1 || choice >4);

        return choice;
    }
	/**
	 * This method prints the exit text when a user exits Moblima
	 */
    public void printExitText(){
        System.out.println("Thank you for using MOBLIMA. Exiting...");
        java.lang.System.exit(0); 
    }
}
