package controllers;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.*;
import View.CustomerRegistrationDisplay;
import entities.Customer;
import model.*;  

/**	Control Class that manages the History of the bookings
 * @author 
 * @version 1.0
 */

public class CustomerRegistrationController {
    public String filepath = "Moblima/src/Data/Customers.csv";

    /**
     * This method controls and Displays the Customer Registration Display, and inserts the Customer's input fields into the csv for Registration
     * @throws IOException
     * @throws InterruptedException
     * @throws FileNotFoundException
     * @throws ParseException
     */
	public static void execute() throws FileNotFoundException, IOException, InterruptedException, ParseException {
        Scanner sc = new Scanner(System.in);
		System.out.println("----Registration for Customer Cinema database----");
        String username, password, mobileNumber, email; int age=0; ArrayList<Customer> database;
        
        database = CustomerManager.getDataAll();
          
        CustomerRegistrationDisplay crd = new CustomerRegistrationDisplay();
        username = crd.getUsername();
        password = crd.getPassword();
        age = crd.getAge();
        mobileNumber = crd.getNumber();
		
        email = crd.getEmail();

        Customer customer = new Customer(username, password, age, mobileNumber, email);
        
        try{
            CustomerManager.addCustomerCSV(customer);
        }  catch (Exception e) {System.out.println("Error! Customer Database not found!"); sc.close(); return;}

        crd.printSuccess();
        LoginController.execute();
	}
}
