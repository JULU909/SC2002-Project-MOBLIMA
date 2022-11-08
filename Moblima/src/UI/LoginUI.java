package UI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import database.*;
import UI.*;

public class LoginUI {
	public static void execute() throws FileNotFoundException, IOException {
		System.out.println("----Login to Cinema database----");
		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("1. CUSTOMER Login");
			System.out.println("2. STAFF Login");
			System.out.println("3. Exit Moblima");
			choice = sc.nextInt(); sc.nextLine();
			switch (choice) {
				case 1:
					CustomerLoginUI.execute();
					break;
				case 2:
					StaffLoginUI.execute();
					break;
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