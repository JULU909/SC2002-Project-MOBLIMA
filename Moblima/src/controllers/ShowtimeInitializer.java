package controllers;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 
import java.util.Calendar;

import entities.Showtime;
import model.ShowtimeManager; 

public class ShowtimeInitializer { 
	/**
	 * Initialiser to add showtimes when program is launched.
	 * Ensures that showtimes are present for the user to interact with
	 * whenever they open this program
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
   public void execute() throws FileNotFoundException, IOException{ 
       LocalDate date =  LocalDate.now(); 
       Calendar cal = Calendar.getInstance(); 
       LocalDate [] bookingDates = new LocalDate[7]; 
       DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ddMMuu"); 
       DateTimeFormatter dayFormatters = DateTimeFormatter.ofPattern("EEEE"); 
       ShowtimeManager sm = new ShowtimeManager("Moblima/src/Data/Showtimes.csv");
        
       for (int i = 0 ; i <7 ; i ++){ 
           bookingDates[i] = LocalDate.now().plusDays(i); 
           String modifiedDate = bookingDates[i].format(formatters); 
           String day = bookingDates[i].format(dayFormatters); 
           // u have to use the modifiedDate on line 20 to add showtimes for the 4 movies we have. 
           Showtime temp = new Showtime(1130,Integer.parseInt(modifiedDate),"ALPHA","BlackAdam");
           temp.setCinemaType("GOLD");
           sm.addShowtimecsv(temp);
           Showtime temp2 = new Showtime(1230,Integer.parseInt(modifiedDate),"BETA","HellRaiser");
           
           temp2.setCinemaType("STANDARD");
           sm.addShowtimecsv(temp2);
        
           Showtime temp3 = new Showtime(1330,Integer.parseInt(modifiedDate),"GAMMA","Top Gun");
           temp3.setCinemaType("PREMIUM");
           sm.addShowtimecsv(temp3);
           
           Showtime temp4 = new Showtime(1630,Integer.parseInt(modifiedDate),"ALPHA", "Alien");
           temp4.setCinemaType("GOLD");
           sm.addShowtimecsv(temp4);
            
       } 
   }

}