package controllers;
import java.util.ArrayList;
import java.util.Scanner;

import View.*;
import entities.Customer;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**	Control Class that checks and validates the Customer Login
 * @author Kit Ye
 * @version 1.0
 * @since 2022-11-13
 */

public class CustomerLoginController {
    public String filepath = "Moblima/src/Data/Customers.csv";


    /**
     * This method controls and Displays the Customer Login Display, and validates the Customer's input fields
     * @throws IOException
     * @throws InterruptedException
     * @throws FileNotFoundException
     * @throws ParseException
     */

	public static void execute() throws FileNotFoundException, IOException, InterruptedException, ParseException {
        Scanner sc = new Scanner(System.in);
	
        CustomerLoginDisplay cd =new CustomerLoginDisplay();
        String username = cd.getUsername();
		String password = cd.getPassword();
        ArrayList<Customer> database;
        try{
             database = CustomerManager.getDataAll();
        }  catch (FileNotFoundException f) {database = new ArrayList<Customer>();} catch (IOException f) {database = new ArrayList<Customer>();}
        
        if (CustomerManager.validateCustomer(username, password, database)){
            cd.printSuccess();
            Customer customer = CustomerManager.findCustomer(username, database);
            CustomerMenuUI.execute(customer);
        }

        else {
            cd.printFailure();
            LoginController.execute();
        }
		sc.close();
	}
}

