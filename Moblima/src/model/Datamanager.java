package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


// Datamanager file reads any csv file, and displays all data or displays 1 data row
// public void Datamanager(String Filename)
// getDataAll()
// getDataRow(int Row)

public interface Datamanager {
    String filename;
    String[] data;

    public static void Datamanager(String filename) {
		this.filename = filename;
	}

    
    public void getDataRow(int row) throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                System.out.println(list.get(row));
            }
    }
}
    




}