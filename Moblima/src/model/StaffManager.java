package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import entities.Showtime;
import entities.Staff;

import java.util.ArrayList;

/**
 * This class allows for the location/adding/validation of all staff in staff.csv
 * @author 
 * @version 1.0
 * @since 2022-11-13
 */

public class StaffManager extends FileManager  {
    private static String filename;
    private ArrayList<Staff> data;
    private Showtime temp;

    public final static String FILENAME = "Moblima/src/Data/Staff.csv";

    public StaffManager(){
        super(FILENAME);
        this.filename = FILENAME;
    }

    public StaffManager(String filename) {
        super(filename);
        this.filename = filename;
    }

    /**
     * This method gets the length of the csv file
     * @return Returns an int, which denotes the length of the rows of the file
     * @throws FileNotFoundException
     * @throws IOException
     */
    
    public static int getLength()throws FileNotFoundException, IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Moblima/src/Data/Staff.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * This method gets all of the Staff data in the csv file
     * @return Returns an List of array of Staff Objects
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Staff> getDataAll() throws FileNotFoundException, IOException{
        ArrayList<Staff> data = new ArrayList<Staff>(getLength());
        try (BufferedReader br = new BufferedReader(new FileReader("Moblima/src/Data/Staff.csv"))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                Staff s = new Staff(list.get(0), list.get(1));
                data.add(s);
                count++;
            }
        }
        return data;
    }

    /**
     * This method locates the staff by username in the database
     * @param username, an attribute of Staff
     * @param password, an attribute of Staff
     * @param database, a list of array of Staff objects
     * @return Returns boolean, true denoting that staff is verified, false denoting that user is not a staff
     */
    public static boolean validateStaff(String username, String password, ArrayList<Staff> database) {
		for (Staff c : database){
		    if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return true;
            }
	    }
        return false;
    }
    
    /**
     * This method locates the staff by username in the database
     * @param username, an attribute of Staff
     * @param database, a list of array of Staff objects
     * @return Returns a Staff object
     */
    public static Staff findStaff(String username, ArrayList<Staff> database){
        for (Staff s : database){
            if(s.getUsername().equals(username)){
                return s;
            }
        }
        return null;
    } 

    
}

    
