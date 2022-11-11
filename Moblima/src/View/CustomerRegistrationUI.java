package View;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.*;

import model.*;  
public class CustomerRegistrationUI {
    public String filepath = "Moblima/src/Data/Customers.csv";

	public static void execute() throws FileNotFoundException, IOException, InterruptedException, ParseException {
        Scanner sc = new Scanner(System.in);
		System.out.println("----Registration for Customer Cinema database----");
        String username, password, mobileNumber, email; int age=0; ArrayList<Customer> database;
        
        database = CustomerManager.getDataAll();
          

        do {
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
		
        do {
            System.out.println("Enter password: ");
            password = sc.nextLine();
            if (password.isEmpty() || password.equals(" ")) { 
                System.out.println("Invalid input!");
            }
            else break;      
        } while (true);

        do {
            try {
                System.out.println("Enter age: ");
                age = Integer.parseInt(sc.nextLine());
                if (age > 0) break;
                else throw new Exception();
            } catch (Exception e) {System.out.println("Invalid input!");}         
        } while (true);
        
        do {
            try {
                System.out.println("Enter phone number: ");
                mobileNumber = sc.nextLine();
                Integer.parseInt(mobileNumber);
                break;
            } catch (Exception e) {System.out.println("Invalid input!");}         
        } while (true);
		
        do {
            System.out.println("Enter email: ");
            email = sc.nextLine();
            if (email.isEmpty() || email.equals(" ") || (email.contains("@") == false)) { 
                System.out.println("Invalid email address!");
            }
            else break;      
        } while (true);

        Customer customer = new Customer(username, password, age, mobileNumber, email);
        
        try{
            CustomerManager.addCustomerCSV(customer);
        }  catch (Exception e) {System.out.println("Error! Customer Database not found!"); sc.close(); return;}

        System.out.println("Customer account successfully registered in database!");
        LoginUI.execute();
	}
}
