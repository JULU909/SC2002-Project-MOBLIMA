package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import entities.Showtime;

import java.util.ArrayList;

public class StaffManager extends Datamanager{
    private static String filename;
    private ArrayList<Staff> data;
    private Showtime temp;

    public final static String FILENAME = "Moblima/src/Data/Staff.csv";

    public StaffManager(){
        this.filename = FILENAME;
    }

    public StaffManager(String filename) {
        this.filename = filename;
    }

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

    public static boolean validateStaff(String username, String password, ArrayList<Staff> database) {
		for (Staff c : database){
		    if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return true;
            }
	    }
        return false;
    }
    

    public static Staff findStaff(String username, ArrayList<Staff> database){
        for (Staff s : database){
            if(s.getUsername().equals(username)){
                return s;
            }
        }
        return null;
    } 

    
}

    
