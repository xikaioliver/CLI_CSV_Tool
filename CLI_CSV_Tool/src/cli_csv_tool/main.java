package cli_csv_tool;

import java.io.FileNotFoundException;
import java.util.Arrays;

import pandas.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		DataFrame df = new DataFrame();
		
		//Args: filename, sort_column1, sort_column2, ...
		String filename = args[0];
		String[] sortColumns = Arrays.copyOfRange(args, 1, args.length );
		
		//By default, the separator of the CSV file is ','. Otherwise extend this code.
		if (args[0].endsWith(".csv")){
			df = CSV_Reader.read_csv(args[0], ",");
			df.sort(sortColumns);
			df.print(3);
		}
	}

}
