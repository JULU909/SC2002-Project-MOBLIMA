package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.io.FileWriter; //for writing to csv
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HolidayManager extends Datamanager {
    private String filename;

    public final static String FILENAME = "Moblima/src/Data/holidays.csv";

    public HolidayManager(){
        this.filename = FILENAME;
    }

    public HolidayManager(String filename) {
        this.filename = filename;
    }
    
    public ArrayList<Holiday> readHolidayCSV() throws FileNotFoundException, IOException { //Read CSV
    	ArrayList<Holiday> list = new ArrayList<Holiday>(); //Create array list of movies
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Holiday";
    	while ((line = br.readLine()) != null) {
    		String split[] = line.split(",", 11); 
    		if(split[0].equals(header)) //Ignore header
    			continue;
    		String holidayName = split[0];
    		LocalDate holidayDate = LocalDate.parse(split[1]);
    		//Create a movie object
    		Holiday tempHoliday = new Holiday(holidayName, holidayDate);
    		//And add it to the array list
    		list.add(tempHoliday);
    	}
        br.close();
    	return list;
    }

    public ArrayList<LocalDate> readHolidayDataCSV() throws FileNotFoundException, IOException { //Read CSV
    	ArrayList<LocalDate> holidayDateList = new ArrayList<LocalDate>(); //Create array list of movies
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Holiday";
    	while ((line = br.readLine()) != null) {
    		String split[] = line.split(",", 11); 
    		if(split[0].equals(header)) //Ignore header
    			continue;
    		LocalDate holidayDate = LocalDate.parse(split[1]);
    		//Create a movie object
            holidayDateList.add(holidayDate);
    	}
        br.close();
    	return holidayDateList;
    }
}