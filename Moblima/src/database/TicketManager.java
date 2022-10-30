package database;
import java.util.ArrayList;
import system.Ticket;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class TicketManager {
    private String filename;
    private ArrayList<Ticket> data;

    public TicketManager(String string) {
        this.filename = filename;
    }

    

    public void uploadTicket(Ticket ticket) throws IOException{
        
        FileWriter fileWriter = new FileWriter("Moblima/src/Data/TicketsBooked.csv",true);

        BufferedWriter bufferWrt = new BufferedWriter(fileWriter);

        StringBuilder line = new StringBuilder();
        line.append(ticket.getID());
        line.append(',');
        line.append(ticket.getUser().getfirstName());
        line.append(',');
        line.append(ticket.getShowtime().getCineplex());
        line.append(',');
        line.append(ticket.getShowtime().getMovie());
        line.append(',');
        for(int i = 0 ; i < ticket.getSeats().size(); i++){
            line.append(ticket.getSeats().get(i).getCol());
            line.append(ticket.getSeats().get(i).getRow());
        }
        line.append(',');
        bufferWrt.write(line.toString());
        bufferWrt.close();
    }

}



