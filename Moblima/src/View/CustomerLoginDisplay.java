package View;

import java.util.Scanner;

/**
 * Displays the Customer Login to the Cinema with relevant getter and setter functions for username and password
 * @author 
 * @version 1.0
 * @since 1.0
 */

public class CustomerLoginDisplay {
    public String getUsername(){
        Scanner sc = new Scanner(System.in);
		System.out.println("----Login to Customer Cinema database----");
		System.out.println("Enter username: ");
		String username = sc.nextLine();
        return username;
    }

    public String getPassword(){
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter password: ");
		String password = sc.nextLine();
		
        return password;
    }

    public void printSuccess(){
        System.out.println("Login success!");
    }
    public void printFailure(){
        System.out.println("Login failed!");
    }
}
