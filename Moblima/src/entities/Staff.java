package entities;
import enums.UserType;

/**
 * Staff class is child of Parent class User 
 * @author
 * @version 1.0
 * @since 2022-11-13
 */

public class Staff extends User{
	
	public Staff(String username, String password){
		super(username,password);
		super.setUserType(UserType.STAFF);
	}
}
