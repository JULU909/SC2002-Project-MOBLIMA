package database;
import java.util.ArrayList;

public class Database {
	/*
     * Adds, deletes, and finds position of a user within the database
     */
	public static void addToDatabase(User user, ArrayList<User> database) {
		int j = findUserPos(user, database);
		if(j!=-1)
			{
				database.set(j, user); //update credentials if user changes password
				System.out.println(user.getUserType() + " " + user.getUsername() + " successfully added.");
				return;
			}

		database.add(user); //if user is being added for the first time (they're not in the ArrayList)
		System.out.println(user.getUserType() + " " + user.getUsername() + " successfully added.");
		return;
	}
	
	public static void deleteUser(User user, ArrayList<User> database) {
		int j = findUserPos(user, database);
		if(j!=-1)
		{
			database.remove(j); //and remove them
			System.out.println(user.getUserType() + " " + user.getUsername() + " successfully removed.");
			return;
		}

		System.out.println("User not on database!"); //if not on database (j reaches size)
		return;
		
	}
	
	public static int findUserPos(User user, ArrayList<User> database) { //find position
		int j;
		for(j=0;j<database.size();j++) 
			if(database.get(j).getUsername() == user.getUsername()) //find user member
				return j;
		return -1;
	}
}
