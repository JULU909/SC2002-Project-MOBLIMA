package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entities.Customer;
import entities.Showtime;

import java.util.ArrayList;
import java.io.FileWriter; //for writing to csv

/**
 * This class manages everything related to the customer
 * @author 
 * @version 1.0
 * @since 2022-11-13
 */

public class CustomerManager {
    private static String filename;
    private ArrayList<Customer> data;
    private Showtime temp;

    public final static String FILENAME = "Moblima/src/Data/Customers.csv";

    // constructor initialises the default path to customer.csv if no input data is given
    public CustomerManager(){
        this.filename = FILENAME;
    }

    public CustomerManager(String filename) {
        this.filename = filename;
    }

    /**
    *This method gets the Length of the csv file by iterating through all rows
    @return int denoting the length
    @throws FileNotFoundException
    @throws IOException
    */
    public static int getLength() throws FileNotFoundException, IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Moblima/src/Data/Customers.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
            }
        }
        return count;
    }

    /**
    *This method gets the Length of the csv file by iterating through all rows
    @return int denoting the length
    @throws FileNotFoundException
    @throws IOException
    */

    static public ArrayList<Customer> getDataAll() throws FileNotFoundException, IOException{
        
        ArrayList<Customer> data = new ArrayList<Customer>(getLength());
        try (BufferedReader br = new BufferedReader(new FileReader("Moblima/src/Data/Customers.csv"))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                Customer c = new Customer(list.get(0), list.get(1), Integer.parseInt(list.get(2)), list.get(3), list.get(4));
                data.add(c);
                count++;
            }

        }
        return data;
    }

    /**
    *This method detects for duplicate Usernames
    @param username an attribute of customer
    @param database a list array of customer objects
    @return boolean: true if duplicate, false if not
    */
    public static boolean duplicateUsername(String username, ArrayList<Customer> database){
        for (Customer c : database){
            if(c.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    } 

    /**
    *This method removes the customer from the csv by username
    @param username an attribute of customer
    */
    public static void removeCustomerCSV(String username) throws FileNotFoundException, IOException {
        ArrayList<Customer> database = getDataAll();
        int i=0;
        for (Customer c : database){
            if(c.getUsername().equals(username)){
                break;
            }
            i++;
        }
        if (i == database.size()) return;
        database.remove(i);
        writeCustomerCSV(database);
    } 

    /**
    *This method finds the customer from the database
    @param username an attribute of customer
    @param database a list array of customer objects
    @return Customer: Customer Object that is to be found, null if cannot find
    */
    public static Customer findCustomer(String username, ArrayList<Customer> database){
        for (Customer c : database){
            if(c.getUsername().equals(username)){
                return c;
            }
        }
        return null;
    } 

    /**
    *This method validates the csutomer
    @param username an attribute of customer
    @param password an attribute of customer
    @param database a list array of customer objects
    @return boolean: true if duplicate, false if not
    */
    public static boolean validateCustomer(String username, String password, ArrayList<Customer> database){
        for (Customer c : database){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    } 

    /**
    *This method writes the customer into the CSV file
    @param database a list array of customer objects
    @throws FileNotFoundException
    @throws IOException
    */
    public static void writeCustomerCSV(ArrayList<Customer> database) throws FileNotFoundException, IOException { //Add a movie into CSV
    	//Writer will write into filename, true allows appending
    	FileWriter writer = new FileWriter("Moblima/src/Data/Customers.csv",false);
    	writer.append("username,password,age,mobile number,email address\n");
        for (Customer customer : database){
            //Get each attribute out from customer
            String username = customer.getUsername();
            String mobileNumber = customer.getmobileNumber();
            String email = customer.getEmailAddress();
            int age = customer.getAge();
            String password = customer.getPassword();

            //Write them all into CSV
            writer.append(username);
            writer.append(",");
            writer.append(password);
            writer.append(",");
            writer.append(String.valueOf(age));
            writer.append(",");
            writer.append(mobileNumber);
            writer.append(",");
            writer.append(email);
            writer.append("\n");
        }
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    }


    /**
    *This method appends the customer into the last row of the csv
    @param customer 
    @throws FileNotFoundException
    @throws IOException
    */
    public static void addCustomerCSV(Customer customer) throws FileNotFoundException, IOException { //Add a movie into CSV
    	//Writer will write into filename, true allows appending
    	FileWriter writer = new FileWriter("Moblima/src/Data/Customers.csv",true);
    	
    	//Get each attribute out from customer
    	String username = customer.getUsername();
    	String mobileNumber = customer.getmobileNumber();
    	String email = customer.getEmailAddress();
        int age = customer.getAge();
        String password = customer.getPassword();

    	//Write them all into CSV
    	writer.append(username);
    	writer.append(",");
    	writer.append(password);
    	writer.append(",");
    	writer.append(String.valueOf(age));
    	writer.append(",");
        writer.append(mobileNumber);
    	writer.append(",");
        writer.append(email);
        writer.append("\n");;

    	
    	//cleanup
    	writer.flush();
    	writer.close();
    }
}
