package entities;
import java.io.IOException;
import java.util.ArrayList;

import enums.*;
import model.ShowtimeManager;
import model.TicketManager;
/**
 * Represents the class of Shpwtimes, which holds the information of the several showtimes available.
 * @author Harish Vasanth
 * @version 1.0
 * @since 2022-11-13
 */


public class Showtime {
    private int time;
    private int date;
    private String cineplex;
    private Seat [][] layout;
    private String movie;
    private String cinemaType;



/**
 * This constructor initialises the seats of the cineplex , date of showtime , time of showtime and the movie previewed.
 * @param time 
 * @param date
 * @param cineplex 
 * @param movie 
 */

public Showtime(int time, int date, String cineplex , String movie){
    this.date = date;
    this.time = time;
    this.cineplex = cineplex;
    this.layout = new Seat[11][32];
    this.movie = movie;
    
    for (int x = 0 ;  x < this.layout.length ; x++){
        for(int y = 0 ; y < this.layout[x].length; y++){
             this.layout[x][y] = new Seat();
             if (y == 6 || y == 23){
                this.layout[x][y].Seat(SeatTypes.SINGLE, SeatStatus.EMPTY, x+65, y);
                continue;
             }
             if (x >= 9){
                this.layout[x][y].Seat(SeatTypes.COUPLE, SeatStatus.VACANT, x+65, y);
                continue;
             }
             if (x == 7 | x == 8){
                this.layout[x][y].Seat(SeatTypes.DELUXE, SeatStatus.VACANT, x+65, y);
                continue;
             }
             this.layout[x][y].Seat(SeatTypes.SINGLE, SeatStatus.VACANT, x+65, y);
        }
}
}



/**
 * This methods prints the Layout of the cineplex
 */

public void printLayout(){
    int characters = 'A';        
    
    int n = 10;
    int m = 100;
        for (int i = 1; i <= n; i++)
        {
            System.out.print("  ");
            for (int j = 1; j <= m; j++)
            {
                if (i == 1 || i == n)           
                    System.out.print("-");     
                else if(j == 1 || j == m)  
                    System.out.print("|"); 
                else
                    System.out.print(" ");           
            }
            System.out.println();
        }
    for (int x = 0 ;  x < layout.length ; x++){
        System.out.printf("%c ", characters);
        characters++;
            for(int y = 0 ; y < layout[x].length; y++){
                if (layout[x][y].getSeatType() == SeatTypes.SINGLE){
                if (layout[x][y].getSeatStatus() == SeatStatus.VACANT){
                    System.out.print("[ ]");
                }
                else if ((layout[x][y].getSeatStatus() == SeatStatus.OCCUPIED)){
                    System.out.print("[X]");
                }
                else{
                    System.out.print("      ");
                }

            }

            else if(layout[x][y].getSeatType() == SeatTypes.COUPLE){
                
                    if (layout[x][y].getSeatStatus() == SeatStatus.VACANT){
                        System.out.print("[    ]");
                    }
                    else if ((layout[x][y].getSeatStatus() == SeatStatus.OCCUPIED)){
                        System.out.print("[X  X]");
                    }
                    else{
                        System.out.print("      ");
                    }
                    y++;
            }

            else if(layout[x][y].getSeatType() == SeatTypes.DELUXE){
                
                if (layout[x][y].getSeatStatus() == SeatStatus.VACANT){
                    System.out.print("[/]");
                }
                else if ((layout[x][y].getSeatStatus() == SeatStatus.OCCUPIED)){
                    System.out.print("[X]");
                }
                else{
                    System.out.print("   ");
                }
                
        }
            }

            System.out.println();
    }
    System.out.println("[X] Indicates the seat is previously booked , [ ] Indicates that the seat is avaliable , ");
    System.out.println("[    ] Indicates a couple seat              , [/] Indicates a deluxe seat ");
    System.out.println("Couple & Deluxe seats charge a surplus ");


   
}

/**
 * This method sets the Layout of the cineplex 
 */

public void setLayout() throws IOException{  


    ArrayList <Seat> bookedSeats = new ArrayList<>();
    TicketManager n = new TicketManager("Moblima/src/Data/TicketsBooked.csv");
    bookedSeats = n.getAllBookedSeats(cineplex , Integer.toString(time), Integer.toString(date) , movie);
    for (int i  = 0 ; i < bookedSeats.size() ; i ++){
        int row  = bookedSeats.get(i).getRow();
        int col = bookedSeats.get(i).getCol();
   
        if ((col)>= 7){
            col +=1;
        }
        if ((col)>= 25){
            col +=2;
        }
        this.layout[row-65][col-1].setOccupied();
    }
   
}
    



/**
 * The methods below are relevant getter and setter methods for seatStatus, date, time, Movie, Cineplex, Title, Showtime.
 */
/**
 * This method sets the cinema type of a specific showtime.
 * @param type Sets cinema type of showtime
 */
public void setCinemaType(String type){
    this.cinemaType = type;
}
/**
 * This method gets the cinema type of a specific showtime.
 * @return Cinema type of showtime
 */
public String getCinemaType(){
    return this.cinemaType;
}
/**
 * This method returns the seat status detail of a specific seat based on row and column number.
 * @param row The row of the seat
 * @param col The column of the seat
 */
public SeatStatus getSeatStatus(int row , int col ){
    if ((row)>= 7){
        row +=1;
    }
    if ((row)>= 25){
        row +=2;
    }
    return this.layout[col-65][row-1].getSeatStatus();
}
/**
 * This method returns the date detail of a specific showtime.
 * @return The date of the showtime
 */
public int getDate() {
	return this.date;
}
/**
 * This method sets the date of a specific showtime.
 * @param date The date to be set
 */
public void setDate(int date) {
	this.date = date;
}
/**
 * This method returns the time detail of a specific showtime.
 * @return The time of the show time
 */
public int getTime(){
    return this.time;
}
/**
 * This method sets the time of a specific showtime.
 * @param time The time to set
 */
public void setTime(int time) {
	this.time = time;
}
/**
 * This method returns the movie detail of a specific showtime.
 * @return Name of movie
 */
public String getMovie(){
    return this.movie;
}
/**
 * This method sets the movie name a specific showtime.
 * @param movie The name of the movie
 */
public void setMovie(String movie) {
	this.movie = movie;
}
/**
 * This method returns the cineplex detail of a specific showtime.
 * @return String
 */
public String getCineplex(){
    return this.cineplex;
}

/**
 * This method sets the showtime cineplex of a specific showtime.
 * @param cineplex The name of the cineplex
 */
public void setCineplex(String cineplex) {
	this.cineplex = cineplex;
}
/**
 * This method returns the showtime details of a specific showtime.
 * @return String
 */
public String getShowTimeDetails(){
    return String.format("Time: %s\nDate: %s\nCineplex: %s\nMovie Title: %s\n ", time, date, cineplex, movie);
}
/**
 * This method returns the seat type of a specific seat for the showtime.
 * @param row row of the seat
 * @param col column of the seat
 * @return The seat type of the seat
 */
public SeatTypes getSeatType(int col , int row ){
    return this.layout[row-65][col-1].getSeatType();
}
/**
 * This method returns the title of the movie for the movie
 * @return The title of the string
 */

public String returnTitle(){
    return String.format("Movie: %s",movie);
}

/**
 * This method checks for the validity of a seat and returns an indicator
 * @param row row of the seat
 * @param col col of the seat
 */

public int validSeat(int row , int col){
    if (row >  11 || row < 0){
        return -1;
    }
    else if (col-65 > 32 || col-65 < 0){
        return -1;
    }
    else if(this.layout[row-65][col-1].getSeatStatus() == SeatStatus.EMPTY){
        return -1;
    }
    return 1;
}
}