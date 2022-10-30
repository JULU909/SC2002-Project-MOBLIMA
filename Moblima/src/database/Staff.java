package database;
import java.util.Scanner;
import enums.UserType;

public class Staff extends User{
	Staff(String firstName, String lastName){
		super(firstName,lastName);
		super.setUserType(UserType.STAFF);
	}

}
