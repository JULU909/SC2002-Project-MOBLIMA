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

	public void setUsername(String username) { 
		this.username = username;
		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) { //set password directly
		this.password = password;
	}

	public String getPassword() { //set password directly
		return this.password;
	}
	
	public void setUserType(UserType type) { 
		this.UserType = type;
		
	}
	
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
