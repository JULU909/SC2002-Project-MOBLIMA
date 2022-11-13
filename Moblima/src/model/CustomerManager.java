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

/**	Reads and writes to the CSV containing customer information
 * @author Kit Ye
 */
public class CustomerManager {
    private static String filename;
    private ArrayList<Customer> data;
    private Showtime temp;

    public final static String FILENAME = "Moblima/src/Data/Customers.csv";

    public CustomerManager(){
        this.filename = FILENAME;
    }

    public CustomerManager(String filename) {
        this.filename = filename;
    }
    /**
     * This methods gets the number of customers in the csv
     * @return Returns an integer length of the number of customers inside the csv
     * @throws FileNotFoundException
     * @throws IOException
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
     * This method reads from a csv file (Customers.csv), splits the columns into a String[5], and returns the ArrayList
     * @return Returns an ArrayList of Customer for the code to read or write
     * @throws FileNotFoundException
     * @throws IOException
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
     * This method reads from a csv file (Customers.csv), and checks if a username exists inside the file
     * @param username the username to be checked against the database
     * @param database the list of existing users to be checked against the username
     * @return Returns true if the username is found in the database, false otherwise
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
     * This method removes a row from the csv file, by username, if it exists in the file.
     * @param username the username to be removed
     * @return Returns true if the username is found in the database, false otherwise
     * @throws FileNotFoundException
     * @throws IOException
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
     * This method checks finds a customer inside an ArrayList by username, and returns it if it does
     * @param username the username to be checked against the database
     * @param database the list of existing customers to be checked against the username
     * @return Returns the customer object if the username is found in the database, null otherwise
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
     * This method reads from an ArrayList, and checks if a username and password corresponding to a customer exists in the list
     * @param username the username to be checked against the database
     * @param password the username and password to be checked against the database
     * @param database the list of existing customers to be checked against the username and password
     * @return Returns true if the username and password of a customer is found in the database, false otherwise
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
     * This method overwrites the csv file with customer data from the ArrayList.
	 * @param list An array list of Customer for the code to read or write
     * @throws FileNotFoundException
     * @throws IOException
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
     * This methods adds a customer into the CSV in a new row
     * @param customer the customer to be added
     * @throws FileNotFoundException
     * @throws IOException
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
