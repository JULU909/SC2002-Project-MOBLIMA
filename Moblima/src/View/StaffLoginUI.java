package View;
import java.util.ArrayList;

import java.util.Scanner;

import controllers.LoginController;
import entities.Staff;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Displays the UI for staff to login
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class StaffLoginUI {
	
    public String filepath = "Moblima/src/Data/Staff.csv"; 

	public static void execute() throws IOException, InterruptedException, FileNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);
		System.out.println("----Login to Staff Cinema database----");
		System.out.println("Enter username: ");
		String username = sc.nextLine();
		System.out.println("Enter password: ");
		String password = sc.nextLine();
        ArrayList<Staff> database;
        try{
             database = StaffManager.getDataAll();
        }  catch (FileNotFoundException f) {database = new ArrayList<Staff>();} catch (IOException f) {database = new ArrayList<Staff>();}
        
        if (StaffManager.validateStaff(username, password, database)){
            System.out.println("Login success!");
            try {
                StaffSettingsUI.settingsText();
            } catch (Exception e) {System.out.println("Error! Database not found!");}
            
            
        }

        else {
            System.out.println("Login failed!");
            // go back to previous menu
            LoginController.execute();
        }
		sc.close();
	}

}