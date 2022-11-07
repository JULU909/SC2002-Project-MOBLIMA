package system;

import java.util.Scanner;

import enums.AgeRating;

public class AgeRatingSetter {
	public static AgeRating ageSetter() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
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
	
	}

}
