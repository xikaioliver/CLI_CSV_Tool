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
		DataFrameInterface df = new CSVReader().readCSV("input-data.csv", ",");
		Random r = new Random();
//		//sort on a few random columns.
		String[] columns = new String[df.getColumns().length];
		System.arraycopy(df.getColumns(), 0, columns, 0, df.getColumns().length);
		int randomColumns = r.nextInt(columns.length-1) + 2;
		sortColumns = new String[randomColumns];
		
		int upper = columns.length - 1;
		for (int i = 0; i < randomColumns; i++) {
			int random = r.nextInt(upper + 1);
			sortColumns[i] = columns[random];
			columns[random] = columns[upper];
			upper --;
		}
		System.out.println("The randomly chosen columns are:");
		for (String item : sortColumns) System.out.print(item + " ");
		System.out.println("");
	}
	
	@Test
	public void testMain() {
		System.out.println("Testing the UI, running with parameters.");
		String[] args = new String[sortColumns.length+1];
		System.arraycopy(sortColumns, 0, args, 1, sortColumns.length);
		args[0] = validFilepath;
		Main.main(args);
		System.out.println("Test finished.");
		
		System.out.println("Testing the UI, reading moreColumnsThanItems.csv");
		args[0] = validFilepath1;
		Main.main(args);
		System.out.println("Test finished.");
		
		System.out.println("Testing the UI, moreItemsThanColumns.csv");
		args[0] = validFilepath2;
		Main.main(args);
		System.out.println("Test finished.");
		
//		Can only check one invalidfilepath one time, because system stops if there is a mistake.
//		To test other invalid files, uncomment the corresponding section.
		args[0] = invalidFile;
		Main.main(args);
//		args[0] = invalidFilepath1;
//		Main.main(args);
//		args[0] = invalidFilepath2;
//		Main.main(args);
		System.out.println("Test finished.");
	}
	
}
