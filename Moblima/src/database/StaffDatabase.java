package src;
import java.util.ArrayList;

public class StaffDatabase {
	public static void addToDatabase(Staff staff, ArrayList<Staff> staffers) {
		int j;
		for(j=0;j<staffers.size();j++) {
			if(staffers.get(j).getfirstName() == staff.getfirstName() && staffers.get(j).getlastName() == staff.getlastName()) //update credentials if staff changes password
			{
				staffers.set(j, staff); //updated credentials
				System.out.println("Staff " + staff.getfirstName() + " " + staff.getlastName() + " successfully added.");
				return;
			}
		}
		
		staffers.add(staff); //if staff is being added for the first time (they're not in the ArrayList)
		System.out.println("Staff " + staff.getfirstName() + " " + staff.getlastName() + " successfully added.");
		return;
	}
	
	public static void deleteStaff(Staff staff, ArrayList<Staff> staffers) {
		int j;
		for(j=0;j<staffers.size();j++) {
			if(staffers.get(j).getfirstName() == staff.getfirstName() && staffers.get(j).getlastName() == staff.getlastName()) //find staff member
			{
				staffers.remove(j); //and remove them
				System.out.println("Staff " + staff.getfirstName() + " " + staff.getlastName() + " successfully removed.");
				return;
			}	
		}
		
		System.out.println("Staff not on database!"); //if not on database (j reaches size)
		return;
		
	}
}
