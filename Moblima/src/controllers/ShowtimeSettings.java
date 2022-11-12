package controllers;
import java.util.Scanner;

import entities.Showtime;
/**
 * This class allows the adding,editing and removing of show times
 * @author Tham Holdon
 */
public class ShowtimeSettings { //static functions so that object does not need to be created
	/**
	 * This method asks the user attributes of a show time to wish to create
	 * @return A show time of attributes they have inputed
	 */
	public static Showtime createShowtime() { //create a show time object
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter movie: ");
		String movie = sc.nextLine();
		System.out.println("Enter cineplex: ");
		String cineplex = sc.nextLine();
		System.out.println("Enter cinemaType: ");
		String cinemaType = sc.nextLine();
		System.out.println("Enter time: ");
		int time = sc.nextInt();
		System.out.println("Enter date(DDMMYY): ");
		int date = sc.nextInt();
		
		Showtime showtime = new Showtime(time,date,cineplex,movie);
		showtime.setCinemaType(cinemaType);
		return showtime;
	}
	/**
	 * This method informs the user that a show time is being added
	 * @return A show time of attributes they wish to add to showTime CSV
	 */
	public static Showtime addShowtime() { //Add a show time object
		System.out.println("Adding showtime...");
		return createShowtime();
		
	}
	/**
	 * This method allows the user to edit the various attributes of a show time they wish to edit
	 * @param showtime , the show time object they wish to edit
	 */
	public static void editShowtime(Showtime showtime) { //Edit a show time object
		Scanner sc = new Scanner(System.in);

		int choice=0;

		while(choice<6) {
			System.out.println("---Showtime editor---");
			System.out.println("1) Edit movie");
			System.out.println("2) Edit cineplex");
			System.out.println("3) Edit cinema type");
			System.out.println("4) Edit time");
			System.out.println("5) Edit date");
			System.out.println("6) Exit");
			String input;
			int number;
			choice = sc.nextInt();
			sc.nextLine(); // eat the \n
			switch(choice)
			{
			case 1:
				System.out.println("Enter movie: ");
				input = sc.nextLine();
				showtime.setMovie(input);
				System.out.println("Movie edited to " + input);
				break;
			case 2:
				System.out.println("Enter cineplex: ");
				input = sc.nextLine();
				showtime.setCineplex(input);
				System.out.println("Cineplex edited to " + input);
				break;
			case 3:
				System.out.println("Enter cinema type: ");
				input = sc.nextLine();
				showtime.setCinemaType(input);
				System.out.println("Cinema type edited to " + input);
				break;
			case 4:
				System.out.println("Enter time: ");
				number = sc.nextInt();
				sc.nextLine(); // eat the \n
				showtime.setTime(number);
				System.out.println("Time edited to " + number);
				break;
			case 5:
				System.out.println("Enter date(DDMMYY): ");
				number = sc.nextInt();
				sc.nextLine(); // eat the \n
				showtime.setDate(number);
				System.out.println("Date edited to " + number);
				break;
			case 6:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid input! Exiting...");
				return;
				
			}

		}
	}
	/**
	 * This method informs the user that a show time is to be removed
	 * @return A show time with attributes they wish to remove
	 */
	public static Showtime removeShowtime() { //Remove a show time object
		System.out.println("Removing showtime...");
		return createShowtime();
	}

}
