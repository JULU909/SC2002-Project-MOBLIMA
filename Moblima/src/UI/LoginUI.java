package UI;
import java.util.ArrayList;
import java.util.Scanner;
import database.*;
import UI.*;

public class LoginUI {
	public static void execute() {
		System.out.println("=====================================================================================\n"+
                           "=====================================================================================\n"+
                           "        Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)       \n"+
                           "=====================================================================================\n"+
                           "=====================================================================================\n");
		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("1. CUSTOMER Login");
			System.out.println("2. STAFF Login");
			System.out.println("3. Exit Moblima");
			choice = sc.nextInt(); sc.next();
			switch (choice) {
				case 1:
					CustomerLoginUI.execute();
					break;
				case 2:
					StaffLoginUI.execute();
				case 3:
					System.out.println("Thank you for using Moblima. Exiting...");
					break;
				default:
					System.out.println("Invalid input!");
					break;
			}
		} while (choice != 3);
		sc.close();
	}
}