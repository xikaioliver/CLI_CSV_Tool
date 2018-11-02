package Test;

import org.junit.*;
import junit.framework.TestCase;
import pandas.*;
import cli_csv_tool.*;
import java.util.Random;

public class MainTest extends TestCase {
	String validFilepath = "Test/data/input-data.csv";
	String validFilepath1 = "Test/data/moreColumnsThanItems.csv";
	String validFilepath2 = "Test/data/moreItemsThanColumns.csv";
	String invalidFile = "Test/data/empty.csv";
	String invalidFilepath1 = "Test/data/notACSV.doc";
	String invalidFilepath2 = "Test/data/noSuchAFile.csv";
	
	String[] sortColumns = null;
	
	@Before
	public void setUp() {
		DataFrameInterface df = new CSVReader().readCSV("Test/data/input-data.csv", ",");
		Random r = new Random();
		//sort on a few random columns.
		String[] columns = new String[df.getColumns().length];
		System.arraycopy(df.getColumns(), 0, columns, 0, df.getColumns().length);
		int randomColumns = r.nextInt(columns.length);
		sortColumns = new String[randomColumns];
		for (int i = 0; i < randomColumns; i++) {
			int random = r.nextInt(columns.length);
			sortColumns[i] = columns[random];
			columns[random] = (random == 0) ? columns[random+1] : columns[random-1];
		}
		
	}
	
	@Test
	public void testMain() {
		System.out.println("Testing the UI, running with parameters.");
		String[] args = new String[sortColumns.length+1];
		System.arraycopy(sortColumns, 0, args, 1, sortColumns.length);
		args[0] = validFilepath;
		Main.main(args);
		args[0] = validFilepath1;
		Main.main(args);
		args[0] = validFilepath2;
		Main.main(args);
//		args[0] = invalidFile;
//		Main.main(args);
//		args[0] = invalidFilepath1;
//		Main.main(args);
//		args[0] = invalidFilepath2;
//		Main.main(args);
		System.out.println("Test finished.");
	}
	
}
