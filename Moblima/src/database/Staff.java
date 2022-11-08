package database;
import java.util.Scanner;
import enums.UserType;

public class Staff extends User{
	Staff(String username, String password){
		super(username,password);
		super.setUserType(UserType.STAFF);
	}

}
