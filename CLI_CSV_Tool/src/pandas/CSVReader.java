package pandas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader implements CSVReaderInterface{
	
	//Indicates if there is any corruption in the CSV.
	private boolean inconsistency = false;
	
	public boolean hasInconsistency() {
		return inconsistency;
	}
	
	//Read a CSV file into a DataFrame.
	//By default, the separator is ','.
	public DataFrame readCSV(String filepath, String sep) {
		
		DataFrame df = new DataFrame();
		
		if (!filepath.endsWith(".csv")) return null;
		
		//Find the file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filepath)));
			String line = "";
			if ((line = br.readLine()) != null) {
				//The first line is the column.
				if (line.equals("")) return null;
				else df.column = line.replaceAll(sep + "+", sep).split(sep);
				for (String item : df.column) {
					if (item == null) return null;
					else df.dataframe.put(item, new ArrayList<String>());
				}
				
				//Read the items.
				int rowLength = 0;
				//Each line is a row.
				while ((line = br.readLine()) != null) {
					df.sortIndex.add(rowLength);
					rowLength ++;
	            	String item[] = line.split(sep);
	            	for (int i = 0; i < Math.max(df.column.length, item.length); i++) {
	            		
	            		//Allow the items to less than the columns. By default, assume the rest of items are "";
	            		//Allow columns to be less than items. Record that there is some data corruption in the CSV.
	            		if (i >= item.length) {
	            			df.dataframe.get(df.column[i]).add("");
	            			inconsistency = true;
	            			continue;
	            		}
	            		else if (i >= df.column.length) {
	            			inconsistency = true;
	            			continue;
	            		}
	            		else df.dataframe.get(df.column[i]).add(item[i]);
	            	}
	            }
			}
			else return null;
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
