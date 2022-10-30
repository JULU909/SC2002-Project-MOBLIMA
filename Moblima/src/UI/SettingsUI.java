package UI;
import java.util.Scanner;

public class SettingsUI {
	public static void settingsText() {
		System.out.println("----Cineplex Settings ---- ");
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while(choice<8) {
			System.out.println("1) Set day.");
			System.out.println("2) Add movie.");
			System.out.println("3) Update movie.");
			System.out.println("4) Remove movie.");
			System.out.println("5) Add show time.");
			System.out.println("6) Edit show time.");
			System.out.println("7) Remove show time.");
			System.out.println("8) List top movies.");
			System.out.println("9) Exit.");
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4: 
				break;
			case 5:
				break;
			case 6:
				break;
			case 7: 
				break;
			case 8:
				break;
			default:
				return;
			
			}
		}
		
	}

}
