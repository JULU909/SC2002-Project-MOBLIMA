package system;
import enums.Day;
import java.util.Scanner;

public class DaySettings {
	public static Day setDay() {
		System.out.println("----Day settings----");
		System.out.println("Enter today's day");
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.println("1. MONDAY");
		System.out.println("2. TUESDAY");
		System.out.println("3. WEDNESDAY");
		System.out.println("4. THURSDAY");
		System.out.println("5. FRIDAY");
		System.out.println("6. SATURDAY");
		System.out.println("7. SUNDAY");
		System.out.println("8. PUBLIC_HOLIDAY");
		choice = sc.nextInt();
		switch(choice)
		{
			case 1:
				return Day.MONDAY;
			case 2:
				return Day.TUESDAY;
			case 3:
				return Day.WEDNESDAY;
			case 4:
				return Day.THURSDAY;
			case 5:
				return Day.FRIDAY;
			case 6:
				return Day.SATURDAY;
			case 7:
				return Day.SUNDAY;
			case 8:
				return Day.PUBLIC_HOLIDAY;
			default:
				System.out.println("Invalid choice! Defaulting to MONDAY");
				return Day.MONDAY;		
		}
	}
}
