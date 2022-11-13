package model;
import java.util.ArrayList;

import entities.User;

/**
 * This class manages everything related to the database
 * @author 
 * @version 1.0
 * @since 2022-11-13
 */

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
	
	/**
    *This method deletes the User from the database
    @param user user object that we want to delete
    @param database a List of array of the User object
    */
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

	/**
    *This method  is a supplimentary method used by deleteUser to find the position of the user in the database
    @param user user object that we want to find
    @param database a List of array of the User object
    */
	public static int findUserPos(User user, ArrayList<User> database) { //find position
		int j;
		for(j=0;j<database.size();j++) 
			if(database.get(j).getUsername() == user.getUsername()) //find user member
				return j;
		return -1;
	}
}
