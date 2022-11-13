package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import enums.AgeRating;
/**
 * This class allows the setting of a movie's age rating
 * @author Tham Holdon
 * @version 1.0
 * @since 2022-11-13
 */
public class AgeRatingSetter {
	/**
	 * This method lets a user decide the age rating a movie is to have
	 * @return The age rating a movie is to have
	 */
	public static AgeRating ageSetter() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("1) G");
				System.out.println("2) PG");
				System.out.println("3) PG13");
				System.out.println("4) NC16");
				System.out.println("5) R21");
				choice = sc.nextInt();
				switch(choice) {
					case 1:
						return AgeRating.G;
					case 2:
						return AgeRating.PG;
					case 3:
						return AgeRating.PG13;
					case 4:
						return AgeRating.NC16;
					case 5:
						return AgeRating.R21;
					default:
						System.out.println("Invalid choice! Defaulting to R21");
						return AgeRating.R21;
					}
			
			}catch(InputMismatchException e ) {
				System.out.println("Enter a valid input! ");
		        String error = sc.next(); // catch the enter;
		        continue;
			}

		}

	}

}
