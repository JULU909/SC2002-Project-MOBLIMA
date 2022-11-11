package controllers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import View.*;
import model.*;

public class LoginController {
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