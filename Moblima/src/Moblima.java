import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import View.BookedHistoryUI;
import View.BookingDisplay;
import View.SearchMovieUI;
import View.StaffLoginUI;
import controllers.LoginController;
import controllers.ShowtimeInitializer;
import entities.*;
//import enums.Day;
import enums.UserType;
import model.*;


/**
 * The entry point of our application, MOBLIMA and redirects the user (new/existing/staff) to the UI class LoginController.
 * @author Holdon, Kit Ye, Harish, Manfred, Jia Wei
 * @version 1.0
 * @since 1.0
 */


public class Moblima {

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ParseException { 
    	ShowtimeInitializer si = new ShowtimeInitializer();
    	si.execute();
        LoginController.execute();
    } 

}