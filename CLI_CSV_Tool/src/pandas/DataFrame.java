package pandas;
import java.io.*;
import java.util.*;

//The class provides some API for DataFrame.

public class DataFrame {
	
	//DataaFrame is a Map<String column, ArrayList>. Each column is an ArrayList.
	//There are three indexes in the DataFrame. The index of the ArayList would always be in natural sequence, start from 0.
	//User can specify one column to be index for use.
	//The sortIndex shows the order of the rows. This is the one used in the solution.
	protected String column[];
	protected ArrayList<Integer> sortIndex = new ArrayList<Integer> ();
	protected HashMap<String, ArrayList<String>> dataframe = new HashMap<String, ArrayList<String>>();
	
	//Not used in the solution any more. Still keep as an API.
	//Force a column to change to another type. Every element in the same column must be the same type.
	//So far, this DataFrame class just accept String and Double. To add other types, extend this code. 
//	public void astype(String column, String type){
//		if (type == "Double"){
//			ArrayList<Double> newColumn = new ArrayList<Double>();
//			int i = 0;
//			for (i = 0; i < dataframe.get(column).size(); i++){
//				try{
//					double newItem = Double.parseDouble((String) dataframe.get(column).get(i));
//					newColumn.add(newItem);
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
//					break;
//				}
//			}
//			if (i == dataframe.get(column).size()) dataframe.put(column, newColumn);
//		}
//		if (type == "String"){
//			ArrayList<String> newColumn = new ArrayList<String>();
//			for (Object item : dataframe.get(column)){
//				String newItem = item.toString();
//				newColumn.add(newItem);
//			}
//			dataframe.put(column, newColumn);
//		}
//	}
	
	//print the first few lines of the DataFrame, specified by the argument 'rows'.
	public void print(int rows){
		for (int i = 0; i < rows; i++){
			int index = sortIndex.get(i);
			for (int j = 0; j < column.length; j++){
				if (j == 0) System.out.println("- " + column[j] + ": " + dataframe.get(column[j]).get(index).toString());
				else System.out.println("  " + column[j] + ": " + dataframe.get(column[j]).get(index).toString());
			}
		}
	}
	
	public boolean isInColumns(String column){
		for (int i = 0; i < this.column.length; i++)
			if (this.column[i].equals(column)) return true;
		return false;
	}
	
	public int length(){
		return sortIndex.size();
	}
	
	//sort the DataFrame based on the specified columns.
	//By default, in order to be comparable, the element in the column need to be numbers.
	//If it is not a number or it is empty, then it should be sort to back.
	//This sort algorithm is stable.
	public void sort(String[] sortColumns){
		Collections.sort(sortIndex, new Comparator< Integer >() {
			@Override
			public int compare(Integer lhs, Integer rhs) {
				Double left, right;
				for (String column : sortColumns){
					try{
						left = Double.parseDouble(dataframe.get(column).get(lhs));
					} catch (NumberFormatException e) {
						try{
							right = Double.parseDouble(dataframe.get(column).get(rhs));
						} catch (NumberFormatException e1) {
							return -1;
						}
						return 1;
					}
					try{
						right = Double.parseDouble(dataframe.get(column).get(rhs));
					} catch (NumberFormatException e) {
						return -1;
					}
					if (left < right) return 1;
					else if (left > right) return -1;
				}
				return 0;
			}
		});
	}
}
