package src;
import java.util.ArrayList;
import java.util.Scanner;

public class Staff extends Person{
	private String password;
	Staff(String firstName, String lastName){
		super(firstName,lastName);
		password = "default";
	}
	
	public void setPassword() {
		System.out.println("Enter current password (default if first time): \n");
		Scanner input = new Scanner(System.in);
		String password = input.nextLine();
		if(password != this.password) //make sure staff is setting their own password
		{
			System.out.println("Password incorrect! Exiting...");
			return;
		}
		System.out.println("Enter new password: \n");
		this.password = input.nextLine(); //update staff's password attribute
		System.out.println("New password set! \n");
		return;
	}
	
	public void addToDatabase(Staff staff, ArrayList<Staff> staffers) {
		StaffDatabase.addToDatabase(staff, staffers);
	}

}
