package pandas;
import java.util.*;

class DataFrame implements DataFrameInterface {
	
	//DataaFrame is a Map<column, ArrayList>. Each column is an ArrayList of String.

	protected String column[] = new String[0];
	protected ArrayList<Integer> sortIndex = new ArrayList<Integer> ();
	protected HashMap<String, ArrayList<String>> dataframe = new HashMap<String, ArrayList<String>>();
	
	//print the first few lines of the DataFrame, specified by the argument 'rows'.
//	public boolean print(int rows) {
//		if (rows < 0 || rows > length()) return false;
//		else {
//			for (int i = 0; i < rows; i++) {
//				int index = sortIndex.get(i);
//				for (int j = 0; j < column.length; j++) {
//					if (j == 0) System.out.println("- " + column[j] + ": " + dataframe.get(column[j]).get(index).toString());
//					else System.out.println("  " + column[j] + ": " + dataframe.get(column[j]).get(index).toString());
//				}
//			}
//			return true;
//		}
//	}
	
	public String[] getColumns() {
		return column;
	}
	
	public String get(int row, String column) {
		if (row < 0 || row >= length()) return null;
		else if (!isInColumns(column)) return null;
		else return dataframe.get(column).get(sortIndex.get(row));
	}
	
	public boolean isInColumns(String column) {
		if (column == null) return false;
		for (int i = 0; i < this.column.length; i++)
			if (this.column[i].equals(column)) return true;
		return false;
	}
	
	public int length() {
		return sortIndex.size();
	}
	
	//sort the DataFrame based on the specified columns.
	//By default, in order to be comparable, the element in the column need to be numbers.
	//If it is not a number or it is empty (""), then it should be sort to the back.
	//This sort algorithm is stable.
	public boolean sort(String[] sortColumns) {
		for (int i = 0; i < sortColumns.length; i++) {
			if (!isInColumns(sortColumns[i])) return false;
		}
		Collections.sort(sortIndex, new Comparator< Integer >() {
			@Override
			public int compare(Integer lhs, Integer rhs) {
				Double left, right;
				for (String column : sortColumns ){
					try {
						left = Double.parseDouble(dataframe.get(column).get(lhs));
					} catch (NumberFormatException e) {
						try {
							right = Double.parseDouble(dataframe.get(column).get(rhs));
						} catch (NumberFormatException e1) {
							continue;
						}
						return 1;
					}
					try {
						right = Double.parseDouble(dataframe.get(column).get(rhs));
					} catch (NumberFormatException e) {
						return -1;
					}
					if (left < right) return 1;
					else if (left > right) return -1;
					else continue;
				}
				return 0;
			}
		});
		return true;
	}
	
	public boolean sort(String sortColumn) {
		if (!isInColumns(sortColumn)) return false;
		Collections.sort(sortIndex, new Comparator< Integer >() {
			@Override
			public int compare(Integer lhs, Integer rhs) {
				Double left, right;
				try {
					left = Double.parseDouble(dataframe.get(sortColumn).get(lhs));
				} catch (NumberFormatException e) {
					try {
						right = Double.parseDouble(dataframe.get(sortColumn).get(rhs));
					} catch (NumberFormatException e1) {
						return 0;
					}
					return 1;
				}
				try {
					right = Double.parseDouble(dataframe.get(sortColumn).get(rhs));
				} catch (NumberFormatException e) {
					return -1;
				}
				if (left < right) return 1;
				else if (left > right) return -1;
				else return 0;
			}
		});
		return true;
	}
	
	public boolean equals (int row1, int row2, String[] columns) {
		for (String column: columns) {
			if (dataframe.get(column).get(sortIndex.get(row1)).equals(dataframe.get(column).get(sortIndex.get(row2)))) continue;
			else return false;
		}
		return true;
	}
}
