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
public class CustomerRegistrationController {
    public String filepath = "Moblima/src/Data/Customers.csv";

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
