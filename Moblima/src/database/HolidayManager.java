package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.io.FileWriter; //for writing to csv
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HolidayManager {
    private String filename;
    private Holiday[] data;

    public final static String FILENAME = "Moblima/src/Data/holidays.csv";

    public Scanner sc = new Scanner(System.in);

    public HolidayManager(){
        this.filename = FILENAME;
    }

    public HolidayManager(String filename) {
        this.filename = filename;
    }

    public int getLength() throws FileNotFoundException, IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {}
        return count;
    }

    public boolean checkValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public ArrayList<Holiday> getDataAll() throws FileNotFoundException, IOException {
        ArrayList<Holiday> data = new ArrayList<Holiday>(getLength());
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count==0){
                    count++;
                    continue;
                }
                String[] values = line.split(",", -1);
                List<String> list = Arrays.asList(values);
                String holidayName = list.get(0);
                LocalDate startDate = LocalDate.parse(list.get(1));
                LocalDate endDate = LocalDate.parse(list.get(2));
                
                // int index, String title, String showingStatus, String synopsis, String director, ArrayList<String> cast, double averageRating, AgeRating ageRating,ArrayList<Review> reviews, String genre, String runTime)
                    data.add(new Holiday(holidayName, startDate, endDate));

                count++;
            }
        } catch (IOException e) {}
        return data;
    }

    public void writeToCSV(ArrayList<Holiday> holidays) throws FileNotFoundException, IOException {
        int length = getLength();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Holiday Name, Start Date, End Date").append("\n");;
        for (Holiday holiday : holidays)
            stringBuilder.append(holiday.getHolidayName() + "," + holiday.getStartDateString() + "," + holiday.getEndDateString()).append("\n");;
        FileWriter fw = new FileWriter(FILENAME);
        for (int i = 0; i < stringBuilder.length(); i++)
            fw.write(stringBuilder.charAt(i));
    }

    // IDK HOW TO WRITE TO CSV FILES 

    public void addHolidayCSV() throws IOException, FileNotFoundException{
        System.out.println("Enter name of holiday to be added: ");
        while (!sc.hasNext()) {
            System.out.println("Please enter a string value!");
            sc.next(); // Remove newline character
        }
        String holidayName = sc.nextLine();
        String startDate;
        String endDate;
        System.out.println("Enter start date (format YYYY-MM-DD): ");
        while(true){
            while (!sc.hasNext()) {
                System.out.println("Please enter a string value!");
                sc.next(); // Remove newline character
            }
            startDate = sc.nextLine();
            if(checkValidDate(startDate)) break;
            System.out.println("Please enter a date in the format YYYY-MM-DD!");
        }
        
        while(true){
            System.out.println("Enter end date (YYYY-MM-DD): ");
            while (!sc.hasNext()) {
                System.out.println("Please enter a string value!");
                sc.next(); // Remove newline character
            }
            endDate = sc.nextLine();
            if(checkValidDate(startDate)) break;
            System.out.println("Please enter a date in the format YYYY-MM-DD!");
        }
        if (!checkDuplicateHoliday(getDataAll(), holidayName)){
            FileWriter writer = new FileWriter(filename,true);

    	    //Write them all into CSV
    	    writer.append(holidayName);
    	    writer.append(",");
            writer.append(startDate);
    	    writer.append(",");
            writer.append(endDate);
    	    writer.append(",");
            writer.append("\n");

            //cleanup
    	    writer.flush();
    	    writer.close();
        }
    }
    /*
    public ArrayList<Movie> removeMovieCSV(ArrayList<Movie> list, String title) throws FileNotFoundException, IOException{ //Remove movie from array list
    	int i = findMovieCSV(title,list); //Find position of movie
    	if(i!=-1) {
    		list.remove(i); // And remove it
    		System.out.println("Movie removed!");
    		return list;
    	}
    	System.out.println("Movie does not exist! Exiting...");
		writeMovieCSV(list);
    	return list; //Return list if movie not present
    }
*/
    public void removeHolidayCSV(String holidayName){
        System.out.println("Enter name of holiday to delete: ");
        while (!sc.hasNext()) {
            System.out.println("Please enter a string value!");
            sc.next(); // Remove newline character
        }
        String holidayName = sc.nextLine();
        ArrayList<Holiday> holidays = new ArrayList<Holiday>();
        holidays = getDataAll();
        int i;
        for(i = 0; i < holidays.size(); i++){
            if(holidays.get(i).getHolidayName().equals(holidayName)){
                holidays.remove(i);
                break;
            }
        }
        if (i == holidaysArr.size())
            System.out.println("Holiday not found!");
        else try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            output.writeObject(holidaysArr);
            output.flush();
            output.close();
        } catch (IOException e) {}
    }

    private boolean checkDuplicateHoliday(ArrayList<Holiday> holidays, String newHolidayName){
        for(Holiday holiday : holidays){
            if(holiday.getHolidayName().equals(newHolidayName)) 
                return true;
        }
        return false;
    }

    public void printHolidays(ArrayList<Holiday> holidays){
        for(Holiday holiday : holidays){
            System.out.println(holiday.toString());
        }
    }

    
}
