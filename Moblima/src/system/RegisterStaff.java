package system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import database.Staff;

public class RegisterStaff {
	public static Staff registerStaff() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new staff username: "); //Get new staff user name
		String username = sc.nextLine();
		System.out.println("Enter new staff password: "); //Get new staff password
		String password = sc.nextLine();
		Staff newStaff = new Staff(username,password); //Create new staff object
		
		return newStaff;
	}
	
	public static void addStaffCSV(Staff newStaff) throws IOException {
		String StaffPath = new File("Staff.csv").getAbsolutePath();
		FileWriter writer = new FileWriter(StaffPath,true); //Append to Staff.csv
		String username = newStaff.getUsername(); //Convert to individual attributes
		String password = newStaff.getPassword();
		
		writer.append(username); //And append them to Staff.csv
		writer.append(",");
		writer.append(password);
		writer.append("\n");
		
		
    	//cleanup
    	writer.flush();
    	writer.close();
	}

}
