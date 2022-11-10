package UI;
import java.util.ArrayList;
import java.util.Scanner;
import database.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerLoginUI {
    public String filepath = "Moblima/src/Data/Customers.csv";

	public static void execute() throws FileNotFoundException, IOException, InterruptedException {
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
            CustomerMenuUI menu = new CustomerMenuUI(customer);
            menu.execute();
        }

        else {
            System.out.println("Login failed!");
        }
		sc.close();
	}
}

