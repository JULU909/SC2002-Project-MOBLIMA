package View;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class CustomerLoginUI {
    public String filepath = "Moblima/src/Data/Customers.csv";

	public static void execute() throws FileNotFoundException, IOException, InterruptedException, ParseException {
        Scanner sc = new Scanner(System.in);
		System.out.println("----Login to Customer Cinema database----");
		System.out.println("Enter username: ");
		String username = sc.nextLine();
		System.out.println("Enter password: ");
		String password = sc.nextLine();
        ArrayList<Customer> database;
        try{
             database = CustomerManager.getDataAll();
        }  catch (FileNotFoundException f) {database = new ArrayList<Customer>();} catch (IOException f) {database = new ArrayList<Customer>();}
        
        if (CustomerManager.validateCustomer(username, password, database)){
            System.out.println("Login success!");
            Customer customer = CustomerManager.findCustomer(username, database);
            CustomerMenuUI.execute(customer);
        }

        else {
            System.out.println("Login failed!");
            LoginUI.execute();
        }
		sc.close();
	}
}
