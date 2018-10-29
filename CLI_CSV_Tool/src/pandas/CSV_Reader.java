package pandas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSV_Reader {
	
	private static DataFrame df;
	
	//Read a CSV file.
	public static DataFrame read_csv(String filepath, String sep) throws FileNotFoundException{
		
		df = new DataFrame();
		
		//Find the file.
		File csv = new File(filepath);
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(csv));
		
		//Read each line, put the item in the corresponding column. Keep the sortIndex updated.
		String line = "";
		try {
			if ((line = br.readLine()) != null){
				//The first line is the column.
				df.column = line.split(sep);
				for (String item : df.column){
					df.dataframe.put(item, new ArrayList<String>());
				}
				
				int rowLength = 0;
				while ((line = br.readLine()) != null)
	            {
					df.sortIndex.add(rowLength);
					rowLength ++;
	            	String item[] = line.split(sep);
	            	for (int i = 0; i < df.column.length; i++){
	            		if (i > item.length) df.dataframe.get(df.column[i]).add(null);
	            		else df.dataframe.get(df.column[i]).add(item[i]);
	            	}
	            }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return df;
	}
}
