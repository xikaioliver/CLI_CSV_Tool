package cli_csv_tool;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

//The class provides some API for DataFrame.

public class DataFrame {
	
	//DataaFrame is a Map<String column, ArrayList>. Each column is an ArrayList.
	private String column[];
	private ArrayList<Integer> sortIndex = new ArrayList<Integer> ();
	private HashMap<String, ArrayList> dataframe = new HashMap<String, ArrayList>();
	
	//Read a CSV file.
	public void read_csv(String filepath, String sep){
		
		//Find the file.
		File csv = new File(filepath);
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//Read each line, put the item in the corresponding column. Keep the sortIndex updated.
		String line = "";
		try {
			if ((line = br.readLine()) != null){
				//The first line is the column.
				column = line.split(sep);
				for (String item : column){
					dataframe.put(item, new ArrayList<String>());
				}
				
				int rowLength = 0;
				while ((line = br.readLine()) != null)
	            {
					sortIndex.add(rowLength);
					rowLength ++;
	            	String item[] = line.split(sep);
	            	for (int i = 0; i < column.length; i++){
	            		if (i > item.length) dataframe.get(column[i]).add(null);
	            		else dataframe.get(column[i]).add(item[i]);
	            	}
	            }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Force a column to change to another type. Every element in the same column must be the same type.
	//So far, this DataFrame class just accept String and Double. To add other types, extend this code. 
	public void astype(String column, String type){
		if (type == "Double"){
			ArrayList<Double> newColumn = new ArrayList<Double>();
			int i = 0;
			for (i = 0; i < dataframe.get(column).size(); i++){
				try{
					double newItem = Double.parseDouble((String) dataframe.get(column).get(i));
					newColumn.add(newItem);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					break;
				}
			}
			if (i == dataframe.get(column).size()) dataframe.put(column, newColumn);
		}
		if (type == "String"){
			ArrayList<String> newColumn = new ArrayList<String>();
			for (Object item : dataframe.get(column)){
				String newItem = item.toString();
				newColumn.add(newItem);
			}
			dataframe.put(column, newColumn);
		}
	}
}
