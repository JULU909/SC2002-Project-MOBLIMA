package entities;

import enums.MovieStatus;
import java.util.Scanner;

public class MovieStatusSetter {
	public static MovieStatus statusSetter() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.println("1) COMING_SOON");
		System.out.println("2) PREVIEW");
		System.out.println("3) NOW_SHOWING");
		System.out.println("4) END_OF_SHOWING");
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			return MovieStatus.COMING_SOON;
		case 2:
			return MovieStatus.PREVIEW;
		case 3:
			return MovieStatus.NOW_SHOWING;
		case 4: 
			return MovieStatus.END_OF_SHOWING;
		default:
			System.out.println("Invalid choice! Defaulting to COMING_SOON");
			return MovieStatus.COMING_SOON;
		}
		
	}

}
