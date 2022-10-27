package src.system;
import src.enums.*;

public class Showtime {
    private int time;
    private int date;
    private String cineplex;
    private Seat [][] layout;
    private String movie;




public void Showtime(int time, int date, String string , String movie){
    this.date = date;
    this.time = time;
    this.cineplex = string;
    this.layout = new Seat[11][32];
    this.movie = movie;
    for (int x = 0 ;  x < this.layout.length ; x++){
        for(int y = 0 ; y < this.layout[x].length; y++){
             this.layout[x][y] = new Seat();
             if (y == 7 || y == 24){
                this.layout[x][y].Seat(SeatTypes.SINGLE, SeatStatus.EMPTY, x, y);
                continue;
             }
             this.layout[x][y].Seat(SeatTypes.SINGLE, SeatStatus.VACANT, x, y);
        }
}
}


public void printLayout(){
    int characters = 'A';        
    for (int x = 0 ;  x < layout.length ; x++){
        System.out.printf("%c ", characters);
        characters++;
            for(int y = 0 ; y < layout[x].length; y++){
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

            System.out.println();
    }
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

public void checkAvailability(int row, int column){  



    



}

public int getTime(){
    return this.time;
}



public String getMovie(){
    return this.movie;
}
}