package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    public String filename; 

    public FileManager(String filename){
        this.filename = filename;
    }

    public void  printDataIndex(int index) throws IOException{

        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            try {
                while ((line = br.readLine()) != null) {
                    if (count==0){
                        count++;
                        continue;
                    }
                    String[] values = line.split(",");
                    List<String> list = Arrays.asList(values);
                    
                    
                    System.out.println(list.get(index));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            System.out.println("Database not found!");
        } 
    }
}
