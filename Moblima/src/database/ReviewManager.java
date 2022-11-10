package database;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReviewManager {
	private String filename;

    public final static String FILENAME = new File("reviewInfo.csv").getAbsolutePath(); //"Moblima/src/Data/reviewInfo.csv";
    
    public ReviewManager(){
        this.filename = FILENAME;
    }

    public ReviewManager(String filename) {
        this.filename = filename;
    }
	public void addReviewCSV(Review review) throws FileNotFoundException, IOException{
    	//Writer will write into filename, true allows appending
    	FileWriter writer = new FileWriter(filename,true);
    	
    	
		int rating = review.getRating();
		String prose = review.getProse();
		String title = review.getTitle();
		String username = review.getReviewer().getUsername();
		
		writer.append(String.valueOf(rating));
		writer.append(",");
		writer.append(prose);
		writer.append(",");
		writer.append(title);
		writer.append(",");
		writer.append(username);
		writer.append("\n");
		
    	//cleanup
    	writer.flush();
    	writer.close();
		
	}
	
	public ArrayList<String[]> readReviewCSV()  throws FileNotFoundException, IOException{
		ArrayList<String[]> list = new ArrayList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
    	String line;
    	
    	while ((line = br.readLine()) != null) {
    		String[] split = line.split(",",5);
    		list.add(split);
    	}
    	return list;
    	
	}
	
	public void printReviews(ArrayList<String[]> list, String title) {
		int i = 0;
		while(i!=list.size()) {
			if(list.get(i)[2].equals(title))
			{
				System.out.println(list.get(i)[3] +"'s review of "+ title + " : " + list.get(i)[1]);
				System.out.println("Rating: " + list.get(i)[0]);
			}
			i++;	
		}
	}
/*public static void main (String[] args) throws FileNotFoundException, IOException {
	ReviewManager rm = new ReviewManager();
	ArrayList<String[]> list = rm.readReviewCSV();
	rm.printReviews(list, "top gun");
}*/
}
