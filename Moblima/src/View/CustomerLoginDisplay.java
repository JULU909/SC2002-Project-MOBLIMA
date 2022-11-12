package View;

import java.util.Scanner;

public class CustomerLoginDisplay {
    public String getUsername(){
        Scanner sc = new Scanner(System.in);
		System.out.println("----Login to Customer Cinema database----");
		System.out.println("Enter username: ");
		String username = sc.nextLine();
        return username;
    }

    public String getPassword(){
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter password: ");
		String password = sc.nextLine();
		
        return password;
    }

    public void printSuccess(){
        System.out.println("Login success!");
    }
    public void printFailure(){
        System.out.println("Login failed!");
    }
}
