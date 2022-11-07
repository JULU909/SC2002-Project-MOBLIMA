package database;
import java.util.Scanner;
import enums.UserType;

public class Staff extends User{
	Staff(String username){
		super(username);
		super.setUserType(UserType.STAFF);
	}

}
