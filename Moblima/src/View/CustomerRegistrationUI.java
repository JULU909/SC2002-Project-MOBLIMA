package View;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

import View.*;
import model.*;
public class CustomerRegistrationUI {
    Scanner sc = new Scanner(System.in);

    public String getUsername() throws FileNotFoundException, IOException {
        String username;
        do {
            ArrayList <Customer> database = CustomerManager.getDataAll();
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

    public void printSuccess(){
        System.out.println("Customer account successfully registered in database!");
    }
}
