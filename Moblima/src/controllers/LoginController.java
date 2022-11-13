package controllers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import View.*;
import model.*;


/**	Control Class that manages the Login of MOBLIMA
 * @author Kit Ye
 * @version 1.0
 * @since 2022-11-13
 */


public class LoginController {

	/**
     * This method controls and displays the Customer Login Display, prompting the Customer to choose between Registering as new user, logining in as existing user or logining in as Staff
     * @throws IOException
     * @throws InterruptedException
     * @throws FileNotFoundException
     * @throws ParseException
     */
	public static void execute() throws FileNotFoundException, IOException, InterruptedException, ParseException {
		LoginDisplay ld = new LoginDisplay();
		ld.printBanner();
		Scanner sc = new Scanner(System.in);
		int choice=ld.getMenuOption();
			
			switch (choice) {
				case 1:
					CustomerLoginController.execute();
					break;
				case 2:
					CustomerRegistrationController.execute();
					break;
				case 3:
					StaffLoginUI.execute();
					break;
				case 4:
					ld.printExitText();
					break;
			}
	}
}