package database;
import enums.UserType;

public class Staff extends User{
	public Staff(String username, String password){
		super(username,password);
		super.setUserType(UserType.STAFF);
	}
}
