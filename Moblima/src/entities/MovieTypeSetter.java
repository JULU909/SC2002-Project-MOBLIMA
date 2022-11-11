package entities;
import java.util.Scanner;

import enums.MovieType;

public class MovieTypeSetter {
	public static MovieType typeSetter() {
		Scanner sc = new Scanner(System.in);
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
	}

}
