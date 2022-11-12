package model;

import java.util.ArrayList;

public class StaffErrorChecker {
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
	
	public static boolean checkCast(ArrayList<String> cast) {
		if(cast.size() == 0)
			return false;
		else
			return true;
	}
}
