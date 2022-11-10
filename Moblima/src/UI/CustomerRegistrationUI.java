package UI;
import java.util.ArrayList;
import java.util.Scanner;
import database.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.*;  
public class CustomerRegistrationUI {
    public String filepath = "Moblima/src/Data/Customers.csv";

	public static void execute() {
        Scanner sc = new Scanner(System.in);
		System.out.println("----Registration for Customer Cinema database----");
        String username, password, mobileNumber, email; int age=0; ArrayList<Customer> database;
        try{
            database = CustomerManager.getDataAll();
        }  catch (Exception e) {System.out.println("Error! Customer Database not found!"); sc.close(); return;}

        do {
            System.out.println("Enter username: ");
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
                age = sc.nextInt(); sc.nextLine();
                if (age > 0) break;
                else throw new Exception();
            } catch (Exception e) {System.out.println("Invalid input!");}         
        } while (true);
        
        do {
            try {
                System.out.println("Enter phone number: ");
                mobileNumber = sc.next(); sc.nextLine();
                Integer.parseInt(mobileNumber);
                break;
            } catch (Exception e) {System.out.println("Invalid input!");}         
        } while (true);
		
        do {
            System.out.println("Enter email: ");
            email = sc.nextLine();
            if (email.isEmpty() || email.equals(" ")) { 
                System.out.println("Invalid input!");
            }
            else break;      
        } while (true);

        Customer customer = new Customer(username, password, age, mobileNumber, email);
        
        try{
            CustomerManager.addCustomerCSV(customer);
        }  catch (Exception e) {System.out.println("Error! Customer Database not found!"); sc.close(); return;}

        System.out.println("Customer account successfully registered in database!");
		sc.close();
	}
}
