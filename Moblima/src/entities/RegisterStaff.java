package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.StaffManager;

/**
 * This class registers the staff and adds the staff object and its relevant attributes to the csv
 * @author Kit Ye
 * @version 1.0
 * @since 2022-11-13
 */


public class RegisterStaff {
	/**
     * This method registers a staff
     * @return returns a Staff object, which is a child of user class
	 * @throws IOException 
	 * @throws FileNotFoundException 
     */

	public static Staff registerStaff() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		Staff newStaff = new Staff("temp","temp");
		while(true)
		{
			System.out.println("Enter new staff username: "); //Get new staff user name
			String username = sc.nextLine();
			System.out.println("Enter new staff password: "); //Get new staff password
			String password = sc.nextLine();
			newStaff.setUsername(username);
			newStaff.setPassword(password);
			if(StaffManager.duplicateUsername(username, StaffManager.getDataAll()))
			{
				System.out.println("Staff username already exist!");
				continue;
			}
			else
				break;
		}

		
		return newStaff;
	}
	
	/**
     * This method appends the staff into the csv file
	 * @param newStaff A staff object
	 * @throws IOException
     */
	public static void addStaffCSV(Staff newStaff) throws IOException {
		//String StaffPath = new File("Moblima/src/Data/Staff.csv");
		FileWriter writer = new FileWriter("Moblima/src/Data/Staff.csv",true); //Append to Staff.csv
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
