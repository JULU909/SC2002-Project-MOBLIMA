import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import View.BookedHistoryUI;
import View.BookingDisplay;
import View.LoginUI;
import View.SearchMovieUI;
import View.StaffLoginUI;
import entities.*;
import enums.Day;
import enums.UserType;
import model.*;

public class Moblima2 {

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ParseException {
        
        LoginUI.execute();
    } 

}