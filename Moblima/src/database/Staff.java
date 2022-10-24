package src;
import java.util.Scanner;

public class Staff extends User{
	Staff(String firstName, String lastName){
		super(firstName,lastName);
		super.setUserType(userType.STAFF);
	}

}
