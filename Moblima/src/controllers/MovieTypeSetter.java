package controllers;
import java.util.InputMismatchException;
import java.util.Scanner;

import enums.MovieType;

public class MovieTypeSetter {
	/**
	 * This class allows the setting of a movie's movie type
	 * @author Tham Holdon
	 */
	public static MovieType typeSetter() {
		/**
		 * This method lets the user decide which movie type a movie is to have
		 * @return The movie type a movie is to have
		 */
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("1) TWOD");
				System.out.println("2) THREED");
				System.out.println("3) BLOCKBUSTER");
				System.out.println("4) NONBLOCKBUSTER");
				int choice = sc.nextInt();
				switch(choice) {
				case 1:
					return MovieType.TWOD;
				case 2:
					return MovieType.THREED;
				case 3:
					return MovieType.BLOCKBUSTER;
				case 4:
					return MovieType.NONBLOCKBUSTER;
				default:
					System.out.println("Invalid choice! Defaulting to TWOD");
					return MovieType.TWOD;
				}
				
				}catch(InputMismatchException e ) {
					System.out.println("Enter a valid input! ");
			        String error = sc.next(); // catch the enter;
			        continue;
			      }
		}	
		}
	}
