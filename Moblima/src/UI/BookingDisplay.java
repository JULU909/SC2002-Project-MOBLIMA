package UI;
import enums.*;
import system.Cineplex;

import java.util.Arrays;
import java.util.Scanner;
import system.Seat;
public class BookingDisplay {
    




public int askCineplex(){
    Scanner sc = new Scanner(System.in);
    CineplexTypes [] list ;
    list = CineplexTypes.values();
    System.out.println( "Please select cineplex : ");
    System.out.println( "------------------------ ");
    for (int i = 0 ; i < list.length; i++){
        System.out.println(i+1 + " : " + list[i]);
    }

    int choice = 0 ;
    choice = sc.nextInt();
    return choice;
}

public Seat[] askSeats(int number){
    
    Scanner sc = new Scanner(System.in);
    Seat [] seatList = new Seat [number];
    
    System.out.println( "Please Enter your seat numbers (EG : A1) : ");
    System.out.println( "------------------------------------------- ");
    for (int i = 0 ; i < number; i++){
        String seat = sc.nextLine();
        seatList[i] = new Seat();
        seatList[i].Seat(null, null, seat.charAt(0), seat.charAt(0));
    }
    
    return seatList;
}

public int askTickets(){
    Scanner sc = new Scanner(System.in);
    
    
    System.out.println( "Please Enter number of seats you wanna book : ");
    System.out.println( "--------------------------------------------- ");
    int choice = 0 ;
    choice = sc.nextInt();
    return choice;
}


public int askMovie(String [] movies){
    Scanner sc = new Scanner(System.in);
    int count= 0;
    System.out.println("Please select the number corresponding to a movie : ");
    System.out.println( "-------------------------------------------------- ");
    while(movies[count] != null){
        System.out.println(i+1 + " :  " + movies[count]);
        count++;
    }
    int choice = 0 ;
    choice = sc.nextInt();
    return choice;
}




}