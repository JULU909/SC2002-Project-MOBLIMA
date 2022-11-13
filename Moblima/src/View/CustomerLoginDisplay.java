package View;

import java.util.Scanner;

/**
 * Displays the Customer Login to the Cinema with relevant getter and setter functions for username and password
 * @author Kit Ye
 * @version 1.0
 * @since 1.0
 */

public class CustomerLoginDisplay {
    /**
     * This method gets the username from a user by prompting them to enter it
     * @return username
     */
    public String getUsername(){
        Scanner sc = new Scanner(System.in);
		System.out.println("----Login to Customer Cinema database----");
		System.out.println("Enter username: ");
		String username = sc.nextLine();
        return username;
    }
    /**
     * This method gets the password from a user by prompting them to enter it
     * @return username
     */
    public String getPassword(){
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter password: ");
		String password = sc.nextLine();
		
        return password;
    }
    /**
     * This method prints a success message upon successful login of the customer
     */
    public void printSuccess(){
        System.out.println("Login success!");
    }
    /**
     * This method prints a success message upon unsuccessful login of the customer
     */
    public void printFailure(){
        System.out.println("Login failed!");
    }
}
