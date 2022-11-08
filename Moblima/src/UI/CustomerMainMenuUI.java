package UI;
import java.util.ArrayList;
import java.util.Scanner;
import database.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerMainMenuUI {
	public static void execute(Customer customer) {
        System.out.println("=====================================================================================\n"+
                            "=====================================================================================\n"+
                            "                                   MOBLIMA CUSTOMER MENU                             \n"+
                            "=====================================================================================\n"+
                            "=====================================================================================\n"+
                            "(1) Search/List Movies\n\n"+
                            "(2) Check seat availability and selection of seat/s\n\n(3) Book and purchase ticket\n\n"+
                            "(4) View booking history\n\n"+
                            "(5) Admin\n\n(6) Exit\n\nChoices (1~6): ");
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            System.out.print("\nWhat is your choice:  ");
            choice = sc.nextInt(); sc.next();
            if (choice >=1 && choice <=6) break;
            else throw new Exception();
            // choice = Integer.parseInt(sc.next());
        } catch (Exception e) {
            choice = sc.nextInt();
            if (choice >=1 && choice <=6) break;
            else{
                System.out.println("Please enter a valid option");
            }
        }

    } while (choice<1 || choice >6);

    return choice;

	
}

