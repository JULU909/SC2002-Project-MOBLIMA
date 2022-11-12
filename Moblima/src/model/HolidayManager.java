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
    	ArrayList<LocalDate> holidayDateList = new ArrayList<LocalDate>();
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	String header = "Holiday";
    	while ((line = br.readLine()) != null) {
    		String split[] = line.split(",", 0); 
    		if(split[0].equals(header)) //Ignore header
    			continue;
    		LocalDate holidayDate = LocalDate.parse(split[1]);
    		//Create a movie object
            holidayDateList.add(holidayDate);
    	}
        br.close();
    	return holidayDateList;
    }

	public void addHolidayCSV(Holiday holiday) throws FileNotFoundException, IOException { 
    	//Writer will write into filename, true allows appending
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	FileWriter writer = new FileWriter(filename,true);
    	
    	//Get each attribute out from customer
    	String name = holiday.getHolidayName();
    	LocalDate date = holiday.getHolidayDate();

    	//Write them all into CSV
    	writer.append(name);
    	writer.append(",");
    	writer.append(date.format(formatter));
        writer.append("\n");

    	//cleanup
    	writer.flush();
    	writer.close();
    }

	public void printHolidaysCSV() throws FileNotFoundException, IOException {
		ArrayList<Holiday> list = readHolidayCSV();
		int i=0;
		for (Holiday h : list){
			System.out.println(i + ")  "+ h.toString());
			i++;
		}
	}

	public ArrayList<Holiday> removeHolidayCSV(int i) throws FileNotFoundException, IOException { //Remove movie from array list
		ArrayList<Holiday> list = new ArrayList<Holiday>();
		list = readHolidayCSV();
    	if(i>=0 && i < list.size()) {
    		list.remove(i); // And remove it
			writeHolidayCSV(list);
    		System.out.println("Holiday removed!");
    		return list;
    	}
    	System.out.println("Holiday does not exist! Exiting...");
    	return list; //Return list if movie not present
    }

	public void writeHolidayCSV(ArrayList<Holiday> list) throws FileNotFoundException, IOException{
    	//FileWriter to write to CSV, no true because it's rewriting    	
    	FileWriter writer = new FileWriter(filename);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    	int i =0;
    	while(i!=list.size()) //Go through every movie in array list
    	{
    		//Get every attribute
    		Holiday holiday = list.get(i);
        	String name = holiday.getHolidayName();
			LocalDate date = holiday.getHolidayDate();
        	
        	//And add it to the CSV
			writer.append(name);
			writer.append(",");
			writer.append(date.format(formatter));
			writer.append("\n");
        	i++;
    	}
    	
    	//cleanup
    	writer.flush();
    	writer.close();
    }
}