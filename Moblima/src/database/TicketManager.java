package database;
import java.util.ArrayList;
import system.Ticket;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.List;

import javax.sound.midi.SysexMessage;

import enums.SeatStatus;
import enums.SeatTypes;

import java.util.Arrays;
import system.*;
import java.io.InputStream;
import java.lang.reflect.Array;



public class TicketManager {
    private String filename;
    private Customer customer;
    private ArrayList<Ticket> data;

    public TicketManager(String string) {
        this.filename = filename;
    }

    

    public void uploadTicket(Ticket ticket) throws IOException{
        
        FileWriter fileWriter = new FileWriter("Moblima/src/Data/TicketsBooked.csv",true);

        BufferedWriter bufferWrt = new BufferedWriter(fileWriter);
        bufferWrt.newLine();
        StringBuilder line = new StringBuilder();
        line.append(ticket.getID());
        line.append(',');
        line.append(ticket.getCustomer().getUsername());
        line.append(',');
        line.append(ticket.getShowtime().getCineplex());
        line.append(',');
        line.append(ticket.getShowtime().getMovie());
        line.append(',');
        line.append(ticket.getShowtime().getTime());
        line.append(',');
        line.append(ticket.getDate());
        line.append(',');

        for(int i = 0 ; i < ticket.getSeats().size(); i++){
            line.append(String.format("%02d",ticket.getSeats().get(i).getCol()));
            line.append(ticket.getSeats().get(i).getRow() );
        }
        line.append(',');
        line.append(ticket.getPrice());
        line.append(',');
        bufferWrt.write(line.toString());
        bufferWrt.close();
    }

    public ArrayList <Seat> getAllBookedSeats(String cineplex, String time , String date , String movie) throws IOException {
        ArrayList <Seat> bookedSeats = new ArrayList();
        int numSeats = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Moblima/src/Data/TicketsBooked.csv"))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                
                if (list.get(2).equals(cineplex) && list.get(3).equals(movie) && list.get(4).equals(time) && list.get(5).equals(date)){
                        
                        String seats = list.get(6).toString();
                        for (int i = 0 ; i < (list.get(6).length())/4 ; i++){
                            

                            Seat temp = new Seat();
                            
                            String col = seats.substring(0,2);
                            String row = seats.substring(2,4);

                            seats = seats.substring(4);
                            temp.Seat(SeatTypes.SINGLE, SeatStatus.OCCUPIED, Integer.valueOf(row), Integer.valueOf(col));
                            bookedSeats.add(temp);

                            
                        }
                }
                
               
               }
            }

            return bookedSeats;
        }


        public ArrayList <Ticket> getUserTickets(String username) throws IOException {

            ArrayList <Ticket> bookedTickets = new ArrayList();
           
            int numTickets = 0;
            
            try (BufferedReader br = new BufferedReader(new FileReader("Moblima/src/Data/TicketsBooked.csv"))) {
                String line;
                int count = 0;
    
                while ((line = br.readLine()) != null) {
                    if (count==0){
                        count++;
                        continue;
                    }
                    String[] values = line.split(",");
                    List<String> list = Arrays.asList(values);
                    
                    if (list.get(1).equals(username)){
                        ArrayList <Seat> bookedSeats = new ArrayList();
                        String seats = list.get(6).toString();
                        int z = 0 ;
                        for (int i = 0 ; i < (list.get(6).length())/4 ; i++){
                            
                            Seat tempSeat = new Seat();
                            String col = seats.substring(0,2);
                            String row = seats.substring(2,4);
                            seats = seats.substring(4);
                            tempSeat.Seat(SeatTypes.SINGLE, SeatStatus.OCCUPIED, Integer.valueOf(row), Integer.valueOf(col));
                            bookedSeats.add(tempSeat);
                           
                        }
                        int date =  Integer.valueOf(list.get(5));
                        int time = Integer.valueOf(list.get(4));
                        String cineplex = list.get(2);
                        String movie = list.get(3);
                        
                        Double cost = Double.valueOf(list.get(6));
                        Showtime tempShowtime = new Showtime(time, date,cineplex , movie);
                        Ticket tempTicket = new Ticket(customer, cost, bookedSeats , tempShowtime , date );
                        bookedTickets.add(tempTicket);
                    }


        }
    }
    return bookedTickets;
    
    }
}



