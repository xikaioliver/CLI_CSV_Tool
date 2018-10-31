package pandas;

public interface DataFrameInterface {
	public boolean print(int rows);
	public boolean isInColumns(String column);
	public int length();
	public boolean sort(String sortColumn);
	public boolean sort(String[] sortColumns);
}
