package entities;
import java.util.ArrayList;
import java.util.Scanner;
import enums.UserType;

/**
 * This class initializes all User attributes with relevant getter and setter methods
 * @author  Kit Ye
 * @version 1.0
 * @since 2022-11-13
 */

public class User{
	private String username;
	private String password;
	private UserType UserType;
	public User (String username, String password){
		this.username = username;
		this.password = password;
	}
	
/**
 * These methods are getter and setter methods for username, password, userType
 */
	/**
	 * This method sets a username for the User
	 * @param username The username to be set for the user object
	 */
	public void setUsername(String username) { 
		this.username = username;
		
	}
	/**
	 * This method gets the username of the user
	 * @return The username of the user as a string
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * This method sets the password for the user
	 * @param password The password to be set for the user
	 */
	public void setPassword(String password) { //set password directly
		this.password = password;
	}
	/**
	 * This method gets the password of the user
	 * @return The password of the user as a String
	 */
	public String getPassword() { //set password directly
		return this.password;
	}
	/**
	 * This method sets the user type of the user
	 * @param type The type the user is to have
	 */
	public void setUserType(UserType type) { 
		this.UserType = type;
		
	}
	/**
	 * This method gets the user type of the user
	 * @return The UserType of the user
	 */
	public UserType getUserType() {
		return this.UserType;
	}

/**
 * This method validates the User to check for appropriate data fields
 * @param username an attribute of customer object
 * @param password an attribute of customer object
 * @param database a List of array of User Objects
 * @return boolean: true if user is valid, false if user is invalid
 */
	public static boolean validateUser(String username, String password, ArrayList<User> database) {
		for (User u : database){
		    if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                return true;
            }
	    }
        return false;
    }
}
