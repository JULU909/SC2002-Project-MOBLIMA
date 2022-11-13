package entities;
import enums.*;
// Imports all the enums to be used in the Class

/**
 * Represents the class of Seats, which holds the information at the instance a seat is selected to be booked.
 * @author Harish Vasanth
 * @version 1.0
 * @since 2022-11-13
 */

/**
     * Constructor for seat object. Sets the seat type , status, row and column
     */
public class Seat {
    private SeatTypes seatType;
    private SeatStatus seatStatus;
    private int row;
    private int col;

    
 /**
     * Setting the row and column of the specific seat booked.
     * @param seatType the type of seat
     * @param seatStatus the status of the seat
     * @param row the row of the seat
     * @param col the column of the seat
     */

    public void Seat(SeatTypes seatType,SeatStatus seatStatus, int row , int col){
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.row = row;
        this.col = col;

    }
    /**
     * Setting the seat status as occupied so it appears as booked.
     * 
     * 
   */
    public void setOccupied(){
        this.seatStatus = seatStatus.OCCUPIED;
    }

    /**
     Relevant getter and setter functions for Row, Column, SeatType and seatStatus
    */
    /**
     * Setting the row and column of the specific seat booked.
     * @param row The row of the seat
     * @param col The column of the seat
     */
    public void setRowCol(int row, int col){

        this.row = row;
        this.col = col;
        
    }
    /**
     * Getting the row of the specific seat booked.
     * @return int
     */
    public int getRow() {
    return row;
    }
    /**
     * Getting the coloumn of the specific seat booked.
     * @return int
     */
    public int getCol() {
    return col;
    }
     /**
     * Getting the seat type of the specific seat booked.
     * @return SeatTypes
     */
    public SeatTypes getSeatType() {
    return seatType;
    }
    /**
     * Getting the seat status of the specific seat booked.
     * @return SeatStatus
     */
    public SeatStatus getSeatStatus() {
    return seatStatus;
    
}

}