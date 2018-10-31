package cli_csv_tool;

import java.io.*;
import java.util.*;

import pandas.*;

public class Main {
	
	private static DataFrame df = null;
	
	private static void execute(String[] args){
		if (args[0].equals("readCSV")){
			//If there is no arguments for read_csv or the argument is not a CSV file path.
			if (args.length == 1 || !args[1].endsWith(".csv")) 
				System.err.println("Warning: invalid filepath! Please input a valid CSV filepath.");
			else if(args.length > 2) System.out.println("Warning: redundant arguments! readCSV only accept one argument.");
			else{
				DataFrame temp;
				//If the file path is not found.
				try {
					temp = CSVReader.readCSV(args[1], ",");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.err.println("Warning: invalid filepath! Please input a valid CSV filepath.");
					return;
				}
				//If the CSV file is invalid. CSV_Reader will return null to handle the IOException.
				//No DataFrame will be read then.
				if (temp == null) System.err.println("Warning: invalid CSV file! Please input a valid CSV filepath.");
				else df = temp;
			}
			return;
		}
		else if (args[0].equals("sort")){
			if (args.length < 3){
				System.err.println("Warning: need at least two columns to sort!");
				return;
			}
			for (int i = 1; i < args.length; i++){
				if (!df.isInColumns(args[i])){
					System.err.println("Warning: " + args[i] + " is not a column!");
					return;
				}
			}
			df.sort(Arrays.copyOfRange(args, 1, args.length));
			return;
		}
		else if (args[0].equals("print")){
			if (args.length == 1) df.print(3);
			else if (args.length > 2) System.err.println("Warning: print only accept one argument!");
			else{
				try{
					int rows = Integer.parseInt(args[1]);
					if (rows > df.length()) System.err.println("Warning: the argument for print is larger than the total rows!");
				} catch (NumberFormatException e) {
					System.err.println("Warning: the argument for print must be an integer!");
				}
			}
			return;
		}
		else if (args[0].equals("exit")) System.exit(0);
		else System.err.println("Warning: " + args[0] + " is not a valid command!");
		
	}

	public static void main(String[] args){
		//By default, the separator of the CSV file is ','. Otherwise extend this code.
		
		if (args.length > 0){
			//Args: filename, sort_column1, sort_column2, ...
			String[] readCSV = new String[2];
			readCSV[0] = "readCSV";
			readCSV[1] = args[0];
			String[] sort = new String[args.length];
			sort[0] = "sort";
			System.arraycopy(args, 1, sort, 1, args.length-1);
			execute(readCSV);
			if (df == null) System.exit(0);
			execute(sort);
			String[] print = new String[1];
			print[0] = "print";
			execute(print);
		}
		else{
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			try {
				while ((line = stdin.readLine()) != null){
					line = line.trim();
					String[] arguments = line.split(" ");
					execute(arguments);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
