package Test;

import org.junit.*;
import junit.framework.TestCase;
import pandas.*;

public class CSVReaderTest extends TestCase {
	
	@Test
	public void testReadCSV(){
		
		//Test read different kinds of valid file/filepath.
		String validFilepath = "Test/data/input-data.csv";
		String validFilepath1 = "Test/data/moreColumnsThanItems.csv";
		String validFilepath2 = "Test/data/moreItemsThanColumns.csv";
		System.out.println("ReadCSV: " + validFilepath);
		CSVReader csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(validFilepath, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + validFilepath1);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(validFilepath1, ","));
		assertTrue(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + validFilepath2);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(validFilepath2, ","));
		assertTrue(csvReader.hasInconsistency());
		
		//Test read different kinds of invalid file/filepath.
		String invalidFile = "Test/data/empty.csv";
		String invalidFilepath1 = "Test/data/notACSV.doc";
		String invalidFilepath2 = "Test/data/noSuchAFile.csv";
		System.out.println("ReadCSV: " + invalidFile);
		csvReader = new CSVReader();
		assertNull(csvReader.readCSV(invalidFile, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + invalidFilepath1);
		csvReader = new CSVReader();
		assertNull(csvReader.readCSV(invalidFilepath1, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + invalidFilepath2);
		csvReader = new CSVReader();
		assertNull(csvReader.readCSV(invalidFilepath2, ","));
		assertFalse(csvReader.hasInconsistency());
		
		//Test CSV files that have: 0 row, 1 row, 2 rows, 3rows and 4 rows. 
		//As the tool has to print the top three rows of the dataframe.
		//Test CSV files that have: 1 column, 2 columns (named as 'row' or 'column'), 3 columns, 4 columns and 5 columns.
		//As based on the requirements, there has to be at least 'row', 'column', and/or another 2 columns used to sort the dataframe.
		//Assume can sort on 'row' or 'column'.
		String row0Column1 ="Test/data/01.csv";
		String row0Column2 ="Test/data/02.csv";
		String row0Column3 ="Test/data/03.csv";
		String row0Column4 ="Test/data/04.csv";
		String row0Column5 ="Test/data/05.csv";
		String row1Column1 ="Test/data/11.csv";
		String row1Column2 ="Test/data/12.csv";
		String row1Column3 ="Test/data/13.csv";
		String row1Column4 ="Test/data/14.csv";
		String row1Column5 ="Test/data/15.csv";
		String row2Column1 ="Test/data/21.csv";
		String row2Column2 ="Test/data/22.csv";
		String row2Column3 ="Test/data/23.csv";
		String row2Column4 ="Test/data/24.csv";
		String row2Column5 ="Test/data/25.csv";
		String row3Column1 ="Test/data/31.csv";
		String row3Column2 ="Test/data/32.csv";
		String row3Column3 ="Test/data/33.csv";
		String row3Column4 ="Test/data/34.csv";
		String row3Column5 ="Test/data/35.csv";
		String row4Column1 ="Test/data/41.csv";
		String row4Column2 ="Test/data/42.csv";
		String row4Column3 ="Test/data/43.csv";
		String row4Column4 ="Test/data/44.csv";
		String row4Column5 ="Test/data/45.csv";
		System.out.println("ReadCSV: " + row0Column1);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row0Column1, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row0Column2);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row0Column2, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row0Column3);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row0Column3, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row0Column4);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row0Column4, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row0Column5);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row0Column5, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row1Column1);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row1Column1, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row1Column2);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row1Column2, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row1Column3);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row1Column3, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row1Column4);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row1Column4, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row1Column5);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row1Column5, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row2Column1);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row2Column1, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row2Column2);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row2Column2, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row2Column3);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row2Column3, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row2Column4);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row2Column4, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row2Column5);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row2Column5, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row3Column1);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row3Column1, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row3Column2);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row3Column2, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row3Column3);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row3Column3, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row3Column4);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row3Column4, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row3Column5);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row3Column5, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row4Column1);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row4Column1, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row4Column2);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row4Column2, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row4Column3);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row4Column3, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row4Column4);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row4Column4, ","));
		assertFalse(csvReader.hasInconsistency());
		System.out.println("ReadCSV: " + row4Column5);
		csvReader = new CSVReader();
		assertNotNull(csvReader.readCSV(row4Column5, ","));
		assertFalse(csvReader.hasInconsistency());
	}
	
}
