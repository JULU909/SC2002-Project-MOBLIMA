package model;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.List;

import javax.sound.midi.SysexMessage;

import entities.*;
import enums.SeatStatus;
import enums.SeatTypes;

import java.util.Arrays;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.ParseException;



public class TicketManager {
    private String filename;
    private Customer customer;
    private ArrayList<Ticket> data;

    public TicketManager(String string) {
        this.filename = filename;
    }

    

    public void uploadTicket(Ticket ticket) throws IOException, ParseException{
        
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
                        
                        Double cost = Double.valueOf(list.get(7));
                        Showtime tempShowtime = new Showtime(time, date,cineplex , movie);
                        Ticket tempTicket = new Ticket(customer, cost, bookedSeats , tempShowtime , date );
                        bookedTickets.add(tempTicket);
                    }


        }
    }
    return bookedTickets;
    
    }

    public ArrayList<Ticket> searchByMovie(ArrayList<Ticket> tickets, String movie){
        ArrayList<Ticket> matchingTickets = new ArrayList<Ticket>();
       for (int i = 0 ; i < tickets.size(); i++){
            //debug : System.out.printf("%d",getLevenshteinDistance(user_inputed, lmaoArr_indexString));
            if(getLevenshteinDistance(movie, tickets.get(i).getShowtime().getMovie()) <3){
                matchingTickets.add(tickets.get(i));
            }
            else{
                continue;
            }
        }
        return matchingTickets;
    }

    

    public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
    
        /*
           The difference between this impl. and the previous is that, rather
           than creating and retaining a matrix of size s.length() + 1 by t.length() + 1,
           we maintain two single-dimensional arrays of length s.length() + 1.  The first, d,
           is the 'current working' distance array that maintains the newest distance cost
           counts as we iterate through the characters of String s.  Each time we increment
           the index of String t we are comparing, d is copied to p, the second int[].  Doing so
           allows us to retain the previous cost counts as required by the algorithm (taking
           the minimum of the cost count to the left, up one, and diagonally up and to the left
           of the current cost count being calculated).  (Note that the arrays aren't really
           copied anymore, just switched...this is clearly much better than cloning an array
           or doing a System.arraycopy() each time  through the outer loop.)
    
           Effectively, the difference between the two implementations is this one does not
           cause an out of memory condition when calculating the LD over two very large strings.
         */
    
        int n = s.length(); // length of s
        int m = t.length(); // length of t
    
        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }
    
        if (n > m) {
            // swap the input strings to consume less memory
            final CharSequence tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }
    
        int p[] = new int[n + 1]; //'previous' cost array, horizontally
        int d[] = new int[n + 1]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d
    
        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t
    
        char t_j; // jth character of t
    
        int cost; // cost
    
        for (i = 0; i <= n; i++) {
            p[i] = i;
        }
    
        for (j = 1; j <= m; j++) {
            t_j = t.charAt(j - 1);
            d[0] = j;
    
            for (i = 1; i <= n; i++) {
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }
    
            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }
    
        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }
    
}



