package UI;
import java.io.FileNotFoundException;
import java.io.IOException;
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
		int choice=0;
		do {
			System.out.println("1. CUSTOMER Login");
			System.out.println("2. STAFF Login");
			System.out.println("3. Exit Moblima");
			try {
				System.out.print("\nWhat is your choice:  ");
				choice = sc.nextInt(); sc.next();	
				if (choice >=1 && choice <=6) break;
				else throw new Exception();
			} catch (Exception e) {
					System.out.println("Please enter a valid option");
			}
		} while (choice <1 || choice >3);
			
			switch (choice) {
				case 1:
					CustomerLoginUI.execute();
					break;
				case 2:
					StaffLoginUI.execute();
					break;
				case 3:
					System.out.println("Thank you for using MOBLIMA. Exiting...");
					break;
			}
		sc.close();
	}
}