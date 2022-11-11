package entities;
import enums.*;
// Imports all the enums to be used in the Class

/**
 * Represents the class of Seats, which holds the information at the instance a seat is selected to be booked.
 * @author Harish Vasanth
 */


public class Seat {
    private SeatTypes seatType;
    private SeatStatus seatStatus;
    private int row;
    private int col;

    public void setOccupied(){
        this.seatStatus = seatStatus.OCCUPIED;
    }


    public void Seat(SeatTypes seatType,SeatStatus seatStatus, int row , int col){
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.row = row;
        this.col = col;

    }

    public void setRowCol(int row, int col){

        this.row = row;
        this.col = col;
        
    }

    public int getRow() {
    return row;
    }

    public int getCol() {
    return col;
    }

    public SeatTypes getSeatType() {
    return seatType;
    }

    public SeatStatus getSeatStatus() {
    return seatStatus;
    
}

}