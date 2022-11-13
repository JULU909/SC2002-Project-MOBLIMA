package controllers;
import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Showtime;
import enums.CineplexTypes;
/**
 * This class allows the adding,editing and removing of show times
 * @author Tham Holdon
 * @version 1.0
 * @since 2022-11-13
 */
 
public class ShowtimeSettings { //static functions so that object does not need to be created
	/**
	 * This method asks the user attributes of a show time to wish to create
	 * @return A show time of attributes they have inputed
	 */
	public static Showtime createShowtime() { //create a show time object
		
		Scanner sc = new Scanner(System.in);
		int time;
		int date;
		System.out.println("Enter movie: ");
		String movie = sc.nextLine();
		System.out.println("Enter cineplex: ");
		String cineplex = sc.nextLine();
		if(!cineplex.equals("ALPHA") && !cineplex.equals("BETA") && !cineplex.equals("GAMMA"))
		{
			System.out.println("Invalid cineplex! Defaulting to ALPHA");
			cineplex = "ALPHA";
			
		}
		System.out.println("Enter cinemaType: ");
		String cinemaType = sc.nextLine();
		if(!cinemaType.equals("PREMIUM") && !cinemaType.equals("GOLD") && !cinemaType.equals("STANDARD"))
		{
			System.out.println("Invalid cinema type! Defaulting to STANDARD");
			cinemaType = "ALPHA";
		}
		while(true) {
			try {
				System.out.println("Enter time: ");
				time = sc.nextInt();
				break;
			}catch(InputMismatchException e ) {
				System.out.println("Enter a valid input! ");
		        String error = sc.next(); // catch the enter;
		        continue;
			}
		}
		
		while(true) {
			try {
				System.out.println("Enter date(DDMMYY): ");
				date = sc.nextInt();
				break;
			}catch(InputMismatchException e ) {
				System.out.println("Enter a valid input! ");
		        String error = sc.next(); // catch the enter;
		        continue;
			}
		}
	
		
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
				if(!input.equals("ALPHA") && !input.equals("BETA") && !input.equals("GAMMA"))
				{
					System.out.println("Invalid cineplex! Defaulting to ALPHA");
					input = "ALPHA";
					
				}
				showtime.setCineplex(input);
				System.out.println("Cineplex edited to " + input);
				break;
			case 3:
				System.out.println("Enter cinema type: ");
				input = sc.nextLine();
				if(!input.equals("PREMIUM") && !input.equals("GOLD") && !input.equals("STANDARD"))
				{
					System.out.println("Invalid cinema type! Defaulting to STANDARD");
					input = "ALPHA";
				}
				showtime.setCinemaType(input);
				System.out.println("Cinema type edited to " + input);
				break;
			case 4:
				while(true) {
					try{
						System.out.println("Enter time: ");
						number = sc.nextInt();
						break;
					}
					
					catch(InputMismatchException e ) {
				System.out.println("Enter a valid input! ");
		        String error = sc.next(); // catch the enter;
		        continue;
			}
				}
				sc.nextLine(); // eat the \n
				showtime.setTime(number);
				System.out.println("Time edited to " + number);
				break;
			case 5:
				while(true) {
					try {
						System.out.println("Enter date(DDMMYY): ");
						number = sc.nextInt();
						break;
					}
					catch(InputMismatchException e ) {
						System.out.println("Enter a valid input! ");
				        String error = sc.next(); // catch the enter;
				        continue;
			}
				}
				
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
