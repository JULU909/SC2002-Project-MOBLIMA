package database;

import java.util.Scanner;
import enums.UserType;


public class User{
	private String username;
	private String password;
	private UserType UserType;
	public User (String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public void setUsername(String username) { 
		this.username = username;
		
	}
	
	public String getUsername() {
		return this.username;
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
	
	public void setUserType(UserType type) { 
		this.UserType = type;
		
	}
	
	public UserType getUserType() {
		return this.UserType;
	}
}
