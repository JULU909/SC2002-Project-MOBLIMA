package system;
import java.util.Scanner;

public class ShowtimeSettings { //static functions so that object does not need to be created
	public static Showtime addShowtime() {
		System.out.println("Adding showtime...");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter movie: ");
		String movie = sc.nextLine();
		System.out.println("Enter cineplex: ");
		String cineplex = sc.nextLine();
		System.out.println("Enter time: ");
		int time = sc.nextInt();
		System.out.println("Enter date(DDMMYY): ");
		int date = sc.nextInt();
		Showtime showtime = new Showtime(time,date,cineplex,movie);
		return showtime;
	}
	
	public static Showtime editShowtime(Showtime showtime) {
		Scanner sc = new Scanner(System.in);
		int choice=0;

		while(choice<5) {
			System.out.println("---Showtime editor---");
			System.out.println("1) Edit movie");
			System.out.println("2) Edit cineplex");
			System.out.println("3) Edit time");
			System.out.println("4) Edit date");
			System.out.println("5) Exit");
			String input;
			int number;
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
				System.out.println("Enter time: ");
				number = sc.nextInt();
				showtime.setTime(number);
				System.out.println("Time edited to " + number);
				break;
			case 4:
				System.out.println("Enter date(DDMMYY): ");
				number = sc.nextInt();
				showtime.setDate(number);
				System.out.println("Date edited to " + number);
				break;
			case 5:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid input! Exiting...");
				break;
				
			}

		}
		return showtime;
	}
	
	public static Showtime removeShowtime() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Removing showtime...");
		System.out.println("Enter movie title of showtime to be removed: ");
		String movie = sc.nextLine();
		System.out.println("Enter cineplex of showtime to be removed: ");
		String cineplex = sc.nextLine();
		System.out.println("Enter time of showtime to be removed: ");
		int time = sc.nextInt();
		System.out.println("Enter date (DDMMYY) of showtime to be removed: ");
		int date = sc.nextInt();
		Showtime toRemove = new Showtime(time,date,cineplex,movie);
		return toRemove;
	}

}
