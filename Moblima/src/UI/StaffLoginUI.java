package UI;
import java.util.ArrayList;
import java.util.Scanner;
import database.*;
import enums.UserType;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StaffLoginUI {
    public String filepath = "Moblima/src/Data/Staff.csv";

	public static void execute() throws FileNotFoundException, IOException, InterruptedException {
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
            Staff staff = StaffManager.findStaff(username, database);
            try {
                SettingsUI.settingsText();
            } catch (Exception e) {System.out.println("Error! Database not found!");}
            
            
        }

        else {
            System.out.println("Login failed!");
            // go back to previous menu
            LoginUI.execute();
        }
		sc.close();
	}

}