package database;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import system.Showtime;

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

    public static int getLength() throws FileNotFoundException, IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
            }
        }
        return count;
    }
    static public ArrayList<Customer> getDataAll() throws FileNotFoundException, IOException{
        ArrayList<Customer> data = new ArrayList<Customer>(getLength());
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                Customer c = new Customer(list.get(1), list.get(2), Integer.parseInt(list.get(3)), list.get(5), list.get(6));
                data.add(c);
                count++;
            }
        }
        return data;
    }



    public static Customer findCustomer(String username, ArrayList<Customer> database){
        for (Customer c : database){
            if(c.getUsername().equals(username)){
                return c;
            }
        }
        return null;
    } 

    public static boolean validateCustomer(String username, String password, ArrayList<Customer> database){
        for (Customer c : database){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    } 
}
