package src;

public class Person {
	private String firstName;
	private String lastName;
	Person(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getfirstName() {
		return this.firstName;
	}
	
	public String getlastName() {
		return this.lastName;
	}

}
