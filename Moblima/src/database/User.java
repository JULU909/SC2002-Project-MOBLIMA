package src.database;

import java.util.Scanner;

enum userType { CUSTOMER, STAFF}

public class User extends Person{
	private String password;
	private userType UserType;
	public User(String firstName, String lastName){
		super(firstName,lastName);
		this.password = "default";
	}
	
	public void setPassword() { //set password with UI
		System.out.println("Enter current password (default if first time): ");
		Scanner input = new Scanner(System.in);
		String text = input.nextLine();
		if(! ( (this.password).equals(text) ) ) //make sure user is setting their own password
		{
			System.out.println("Password incorrect! Exiting...");
			return;
		}
		System.out.println("Enter new password: ");
		this.password = input.nextLine(); //update user's password attribute
		System.out.println("New password set! ");
		return;
	}
	
	public void setPassword(String password) { //set password directly
		this.password = password;
	}
	
	public void setUserType(userType type) { 
		this.UserType = type;
		
	}
	
	public userType getUserType() {
		return this.UserType;
	}
	
	

}
