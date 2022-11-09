package UI;
import java.util.ArrayList;
import java.util.Scanner;
import database.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// THIS DOESNT WORK RIGHT NOW I JUST COPIED MOBLIMA.JAVA INTO IT. GOAL IS TO MAKE MOBLIMA.JAVA BUT CLEANER. ACTUALLY IMMA PAUSE WORK ON THIS FOR NOW
public class CustomerMainMenuUI {
	public static void execute(Customer customer) {
        int choice=0;
        do {
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
            try {
                System.out.print("\nWhat is your choice:  ");
                choice = sc.nextInt(); sc.next();
                if (choice >=1 && choice <=6) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter a valid option");
            }
        } while (choice<1 || choice>6);

        switch (choice) {
            case 1:
                SearchingMoviesMenuUI.execute();
            case 2:
                System.out.println("=====================================================================================\n");
                System.out.println("                          Viewing Movie Details                                      \n");
                System.out.println("=====================================================================================\n");
                System.out.println("Do you want to View ALL Movie details or View ONE Movie detail? \n1) View All Movie details\n2) View ONE Movie detail (by index, cannot be 0 or negative)\n");
                System.out.println("Your Choice please: ");
                selection = sc.nextInt();
                MovieInfoManager m1 = new MovieInfoManager();
                m1.getDataAll();
                
                if(selection == 1){
                m1.PrintAll();
                }
                else if(selection == 2){
                // Scanner sc = new Scanner(System.in);
                    System.out.println("Select the index of the movie you want to display: ");
                    selection = sc.nextInt();
                    m1.PrintOne(selection);
                }

                break;
            
            case 3:
                purchaseTicket() ;
                break;
            case 4:
                seatDetails();
                break;

            case 5:
                bookingHistory() ;
                break;
            
            case 6:
                movieRanking();
                break;

            case 7:
                admin() ;
                break;
            
            case 8:
                exitDialouge();
            default:
                sc.close();
                break;
        }
        // choice
    } while (true);

	
    }
}

