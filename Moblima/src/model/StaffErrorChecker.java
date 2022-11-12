package model;

import java.util.ArrayList;
/**
 * This class helps to check for errors when inputting attributes at staff settings
 * @author Tham Holdon
 *
 */
public class StaffErrorChecker {
	/**
	 * This function checks if the format of run time given is correct
	 * @param runtime, the string inputed
	 * @return true if the format is correct, false otherwise
	 */
	public static boolean checkRuntime(String runtime) {
		if(runtime.length()!=5) {
			System.out.println("Invalid format! Runtime format is xHxxM, x is a digit.");
			return false;
		}
		Character H = runtime.charAt(1);
		Character M = runtime.charAt(4);
		if(H.equals('H') && M.equals('M') && Character.isDigit(runtime.charAt(0)) && Character.isDigit(runtime.charAt(2)) && Character.isDigit(runtime.charAt(3)))
			return true;
		else
		{
			System.out.println("Invalid format! Runtime format is xHxxM, x is a digit.");
			return false;
		}
			
	}
	/**
	 * This function ensures that there is a cast member given in the movie
	 * @param cast, the array list of cast in the movie
	 * @return true if there is more than 1 cast member, false otherwise
	 */
	public static boolean checkCast(ArrayList<String> cast) {
		if(cast.size() == 0)
			return false;
		else
			return true;
	}
}
