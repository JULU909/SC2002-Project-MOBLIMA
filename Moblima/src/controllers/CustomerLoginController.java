package controllers;
import java.util.ArrayList;
import java.util.Scanner;

import View.*;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class CustomerLoginController {
    public String filepath = "Moblima/src/Data/Customers.csv";

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

