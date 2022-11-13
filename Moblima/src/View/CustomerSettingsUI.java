package View;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Customer;
import model.CustomerManager;

public class CustomerSettingsUI {
	public static void settingsText(Customer customer) throws FileNotFoundException, IOException, InterruptedException, ParseException {
		int choice = 0;
		
		Scanner sc = new Scanner(System.in);
		String customerpath = new File("Customers.csv").getAbsolutePath();
		
		while(choice!=5) {
			System.out.println("1) Update password.");
			System.out.println("2) Update email address.");
			System.out.println("3) Update phone number.");
			System.out.println("4) Update age.");
			System.out.println("5) Exit.");

			try {
				choice = sc.nextInt();
				sc.nextLine(); // Eat the \n
			} catch (Exception e) {System.out.println("Please enter a valid input!"); continue;}
			
			Customer updatedCustomer;
			switch(choice) {
			case 1:
            String password;
            do {
                    System.out.println("Enter new password: ");
                    password = sc.nextLine();
                    if (password.isEmpty() || password.equals(" ")) { 
                        System.out.println("Invalid input!");
                    }
                    else break;      
                } while (true);

                updatedCustomer = new Customer(customer.getUsername(), password, customer.getAge(), customer.getmobileNumber(), customer.getEmailAddress());
                CustomerManager.removeCustomerCSV(customer.getUsername());
                CustomerManager.addCustomerCSV(updatedCustomer);
                customer = updatedCustomer;
                System.out.println("Successfully updated!");
				break;
			
			case 2:
            String email;
            do {
                    System.out.println("Enter new email address: ");
                    email = sc.nextLine();
                    if (email.isEmpty() || email.equals(" ") || (email.contains("@") == false)) { 
                        System.out.println("Invalid input!");
                    }
                    else break;      
                } while (true);

                updatedCustomer = new Customer(customer.getUsername(), customer.getPassword(), customer.getAge(), customer.getmobileNumber(), email);
                CustomerManager.removeCustomerCSV(customer.getUsername());
                CustomerManager.addCustomerCSV(updatedCustomer);
                customer = updatedCustomer;
                System.out.println("Successfully updated!");
				break;
			case 3: 
                String phone;
                do {
                        System.out.println("Enter new phone number: ");
                        phone = sc.nextLine();
                        try {
                            Integer.parseInt(phone);
                        } catch (Exception e) {System.out.println("Please enter a valid input!"); continue;}
                        if (phone.isEmpty() || phone.equals(" ")) { 
                            System.out.println("Invalid input!");
                        }
                        else break;      
                    } while (true);

                    updatedCustomer = new Customer(customer.getUsername(), customer.getPassword(), customer.getAge(), phone, customer.getEmailAddress());
                    CustomerManager.removeCustomerCSV(customer.getUsername());
                    CustomerManager.addCustomerCSV(updatedCustomer);
                    customer = updatedCustomer;
                    System.out.println("Successfully updated!");
                    break;
			
			case 4: 
                int age;
                do {
                        System.out.println("Enter new age: ");
                        try {
                            age = Integer.parseInt(sc.nextLine());
                            if (age <=0) throw new Exception();
                            break;
                        } catch (Exception e) { System.out.println("Invalid input!");}
                    } while (true);

                    updatedCustomer = new Customer(customer.getUsername(), customer.getPassword(), age, customer.getmobileNumber(), customer.getEmailAddress());
                    CustomerManager.removeCustomerCSV(customer.getUsername());
                    CustomerManager.addCustomerCSV(updatedCustomer);
                    customer = updatedCustomer;
                    System.out.println("Successfully updated!");
                    break;
				
			case 5:
				CustomerMenuUI.execute(customer);
				break;

			default:
				System.out.println("Please enter a valid input!");
				break;
			}
		}
		
	}

}
