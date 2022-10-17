package src.UI;
import src.enums.*;
import src.system.Cineplex;

public class BookingDisplay {
    




public int askCineplex(){

    CineplexTypes [] list ;
    list = CineplexTypes.values();
    System.out.println( "Please select cineplex : ");
    System.out.println( "------------------------ ");
    for (int i = 0 ; i < list.length; i++){
        System.out.println(i+1 + " : " + list[i]);
    }

    int choice = 0 ;

    return choice;
}


}