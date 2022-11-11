package system;
import java.io.IOException;
import java.util.ArrayList;

import database.ShowtimeManager;
import database.TicketManager;
import enums.*;

public class Showtime {
    private int time;
    private int date;
    private String cineplex;
    private Seat [][] layout;
    private String movie;
    private String cinemaType;




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

public void setCinemaType(String type){
    this.cinemaType = type;
}

public String getCinemaType(String type){
    return this.cinemaType;
}

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


       /* int count = 0;
        System.out.print("    ");
    for (int i = 0 ; i < layout[0].length ; i++){
        if (layout[0][i].getSeatStatus() == SeatStatus.VACANT || layout[0][i].getSeatStatus() == SeatStatus.OCCUPIED){
            System.out.print(count+ " ");
            count++;
        }
        else{
            System.out.print("      ");
        }
    }
    */
}

public void setLayout() throws IOException{  


    ArrayList <Seat> bookedSeats = new ArrayList<>();
    TicketManager n = new TicketManager("Moblima/src/Data/TicketsBooked.csv");
    bookedSeats = n.getAllBookedSeats(cineplex , Integer.toString(time), Integer.toString(date) , movie);
    for (int i  = 0 ; i < bookedSeats.size() ; i ++){
        int row  = bookedSeats.get(i).getRow();
        int col = bookedSeats.get(i).getCol();
   

        this.layout[row-65][col-1].Seat(SeatTypes.SINGLE, SeatStatus.OCCUPIED, row, col);
    }
   
}
    




public void checkAvailability(){


}

public int getDate() {
	return this.date;
}

public void setDate(int date) {
	this.date = date;
}

public int getTime(){
    return this.time;
}

public void setTime(int time) {
	this.time = time;
}

public String getMovie(){
    return this.movie;
}

public void setMovie(String movie) {
	this.movie = movie;
}

public String getCineplex(){
    return this.cineplex;
}

public void setCineplex(String cineplex) {
	this.cineplex = cineplex;
}

public String getShowTimeDetails(){
    return String.format("Time: %s\nDate: %s\nCineplex: %s\nMovie Title: %s\n ", time, date, cineplex, movie);
}

public String returnTitle(){
    return String.format("Movie: %s",movie);
}
}