package Test;

import org.junit.*;
import junit.framework.TestCase;
import pandas.*;
import java.util.Random;

public class DataFrameTest extends TestCase {
	DataFrameInterface df;
	
	@Before
	public void setUp() {
		df = new CSVReader().readCSV("Test/data/input-data.csv", ",");
	}
	
	@Test
	public void testGetColumns() {
		System.out.println("Testing df.getColumns() after reading input-data.csv");
		for (String item : df.getColumns()) System.out.print(item + " ");
		System.out.println("");
		System.out.println("Test finished.");
	}
	
	@Test
	public void testLength() {
		System.out.println("Testing df.length().");
		assertEquals(df.length(), 92);
		System.out.println("Test finished.");
	}
	
	@Test
	public void testGet() {
		Random r = new Random();
		int randomRow = r.nextInt(df.length());
		int randomColumnIndex = r.nextInt(df.getColumns().length);
		String randomColumn = df.getColumns()[randomColumnIndex];
		System.out.println("Testing df.get(" + String.valueOf(randomRow) + ", " + randomColumn + ")");
		System.out.println(df.get(randomRow, randomColumn));
		System.out.println("Test finished");
	}
	
	@Test
	public void testIsInColumns() {
		String isAColumn = "DTS_days__obs";
		String isNotAColumn = "Not_A_Column";
		System.out.println("Testing df.isInColumns().");
		assertTrue(df.isInColumns(isAColumn));
		assertFalse(df.isInColumns(isNotAColumn));
		System.out.println("Test finished.");
	}
	
	@Test
	public void testEquals() {
		String[] columns = new String[] {"RLodg_pl_plot", "SLodg_pl_plot", "REP_NO"};
		System.out.println("Testing df.equals().");
		assertTrue(df.equals(5, 6, columns));
		assertFalse(df.equals(7, 8, columns));
		System.out.println("Test finished.");
	}
	
	@Test
	public void testSort() {
		Random r = new Random();
		//sort on a few random columns.
		String[] columns = new String[df.getColumns().length];
		System.arraycopy(df.getColumns(), 0, columns, 0, df.getColumns().length);
		int randomColumns = r.nextInt(columns.length-1) + 2;
		String[] sortColumns = new String[randomColumns];
		
		int upper = columns.length - 1;
		for (int i = 0; i < randomColumns; i++) {
			int random = r.nextInt(upper + 1);
			sortColumns[i] = columns[random];
			columns[random] = columns[upper];
			upper --;
		}
		
		System.out.println("Testing df.sort(String[] sortColumns))");
		System.out.println("The randomly chosen columns are:");
		for (String item : sortColumns) System.out.print(item + " ");
		System.out.println("");
		assertTrue(df.sort(sortColumns));
		for (int i = 0; i < 3; i++) {
			System.out.println("- row: " + df.get(i, "row"));
			System.out.println("  column: " + df.get(i, "column"));
			System.out.print("  data:");
			for (int j = 0; j < sortColumns.length; j++) {
				System.out.print(" " + sortColumns[j] + "=" + df.get(i, sortColumns[j]));
			}
			System.out.println("");
		}
		String[] notSortColumns = new String[] {"wrongColumn1", "wrongColumn2"};
		assertFalse(df.sort(notSortColumns));
		System.out.println("Test finished.");
	}
}
