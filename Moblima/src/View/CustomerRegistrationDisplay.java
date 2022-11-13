package View;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.CustomerManager;

import View.*;
import entities.Customer;
import model.*;

/**
 * Displays the Customer Registration UI for new users
 * @author Kit Ye
 * @version 1.0
 * @since 1.0
 */

public class CustomerRegistrationDisplay {
    Scanner sc = new Scanner(System.in);
    /**
     * This method gets the username from a user by prompting them to enter it. If the username already exists in the database, it will prompt the user to enter another
     * @return username
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String getUsername() throws FileNotFoundException, IOException {
        String username;
        do {
            ArrayList<Customer> database = CustomerManager.getDataAll();
            System.out.println("Enter username (username is final and cannot be changed later!): ");
            username = sc.nextLine();
            if (username.isEmpty() || username.equals(" ")) { 
                System.out.println("Invalid input!");
            }
            else if (CustomerManager.duplicateUsername(username, database)) {
                System.out.println("Username already exists in database!");
            }
            else break;      
        } while (true);

        return username;
    }
    /**
     * This method gets the password from a user by prompting them to enter it
     * @return password
     */
    public String getPassword(){
        String password;
        do {
            System.out.println("Enter password: ");
            password = sc.nextLine();
            if (password.isEmpty() || password.equals(" ")) { 
                System.out.println("Invalid input!");
            }
            else break;      
        } while (true);
        return password;
    }
    /**
     * This method gets the age from a user by prompting them to enter it
     * @return age
     */
    public int getAge(){
        int age;
        do {
            try {
                System.out.println("Enter age: ");
                age = Integer.parseInt(sc.nextLine());
                if (age > 0) break;
                else throw new Exception();
            } catch (Exception e) {System.out.println("Invalid input!");}         
        } while (true);
        return age;

    }
    /**
     * This method gets the mobile number from a user by prompting them to enter it
     * @return mobile number
     */
    public String getNumber(){
        String mobileNumber;
        do {
            try {
                System.out.println("Enter phone number: ");
                mobileNumber = sc.nextLine();
                Integer.parseInt(mobileNumber);
                break;
            } catch (Exception e) {System.out.println("Invalid input!");}         
        } while (true);
        return mobileNumber;
    }
    /**
     * This method gets the age from a user by prompting them to enter it
     * @return email
     */
    public String getEmail(){
        String email;
        do {
            System.out.println("Enter email: ");
            email = sc.nextLine();
            if (email.isEmpty() || email.equals(" ") || (email.contains("@") == false)) { 
                System.out.println("Invalid email address!");
            }
            else break;      
        } while (true);

        return email;
    }
    /**
     * This method prints a success message upon successful collection of all registration details of the customer
     */
    public void printSuccess(){
        System.out.println("Customer account successfully registered in database!");
    }
}
