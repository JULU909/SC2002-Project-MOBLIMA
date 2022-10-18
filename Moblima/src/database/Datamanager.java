package src.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Datamanager {
    private String filename;
    private String[] data;

    public void Datamanager(String filename) {
		this.filename = filename;
	}

    public void getDataAll() throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> list = Arrays.asList(values);
                System.out.println(list);
            }
    }
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