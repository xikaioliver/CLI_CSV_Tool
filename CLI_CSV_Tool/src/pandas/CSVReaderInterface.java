package pandas;

public interface CSVReaderInterface {
	public boolean hasInconsistency();
	public DataFrame readCSV(String filepath, String sep);
}
