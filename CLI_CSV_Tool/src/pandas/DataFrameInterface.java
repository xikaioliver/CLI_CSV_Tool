package pandas;

public interface DataFrameInterface {
	public String[] getColumns();
	public String get(int row, String column);
	public boolean isInColumns(String column);
	public int length();
	public boolean sort(String sortColumn);
	public boolean sort(String[] sortColumns);
	//Check is the two rows are equals in the specified columns.
	public boolean equals (int row1, int row2, String[] columns);
}
