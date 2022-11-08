package UI;
import java.util.ArrayList;
import java.util.Scanner;
import database.Database;
import database.User;
import enums.UserType;

public class LoginUI {
	public static User loginText() {
		System.out.println("----Login to Cinema database----");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter userType: ");
		System.out.println("1. CUSTOMER");
		System.out.println("2. STAFF");
		enums.UserType type = enums.UserType.CUSTOMER;
		int choice = sc.nextInt();
		if(choice == 1)
			type = enums.UserType.CUSTOMER;
		else if(choice == 2)
			type = enums.UserType.STAFF;
		else
			System.out.println("Invalid entry, defaulting userType to CUSTOMER");
				
		System.out.println("Enter username: ");
		String username = sc.nextLine();
		System.out.println("Enter password: ");
		String password = sc.nextLine();
		
		User loginUser = new User(username, password); 
		loginUser.setUserType(type);
		sc.close();
		return loginUser;
	}
	
	public boolean validateUser(User user, ArrayList<User> database) {
		String filepath;
		if (user.getUserType() == UserType.CUSTOMER) filepath = "Moblima/src/Data/Customer.csv";
		else filepath = "Moblima/src/Data/Staff.csv";
		if(check == -1)
		{
			System.out.println("Login failed!");
			return false;
		}
		System.out.println("Login success!");
		return true;
	}
}
