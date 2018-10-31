package pandas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CSVReader {
	
	//Read a CSV file into a DataFrame.
	//By default, the separator is ','.
	public static DataFrame readCSV(String filepath, String sep){
		
		DataFrame df = new DataFrame();
		
		//Find the file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filepath)));
			String line = "";
			if ((line = br.readLine()) != null) {
				//The first line is the column.
				df.column = line.split(sep);
				for (String item : df.column) {
					if (item == null) return null;
					df.dataframe.put(item, new ArrayList<String>());
				}
				
				int rowLength = 0;
				while ((line = br.readLine()) != null) {
					df.sortIndex.add(rowLength);
					rowLength ++;
	            	String item[] = line.split(sep);
	            	for (int i = 0; i < Math.max(df.column.length, item.length); i++) {
	            		//Allow the items to less than the columns. By default, assume the rest of items are "";
	            		//Do not allow columns to be less than items. Throw IOException and stop reading CSV.
	            		if (i >= item.length) df.dataframe.get(df.column[i]).add("");
	            		else if (i >= df.column.length) throw new IOException();
	            		else df.dataframe.get(df.column[i]).add(item[i]);
	            	}
	            }
			}
			br.close();
		} catch (NullPointerException e) {
			return null;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		return df;
	}
}
