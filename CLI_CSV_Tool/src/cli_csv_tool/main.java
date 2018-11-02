package cli_csv_tool;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import pandas.*;


//This class works as UI. It handles all interactions with the user.
public class Main {
	
	private static DataFrameInterface df = null;
	private static String[] sortColumns = new String[0];
	//Logging system, form a file each day. Initialized in main();
	private static Logger log = Logger.getLogger("ExceptionHandler");
	
	//Print the result in YAML format.
	//If rows is bigger than df.length, print all rows by default.
	private static void print(int rows) {
		for (int i = 0; i < Math.min(rows, df.length()); i++) {
			System.out.println("- row: " + df.get(i, "row"));
			System.out.println("  column: " + df.get(i, "column"));
			System.out.print("  data:");
			for (int j = 0; j < sortColumns.length; j++) {
				System.out.print(" " + sortColumns[j] + "=" + df.get(i, sortColumns[j]));
			}
			System.out.println("");
		}
	}
	
	//Return value shows whether the execution is successful or not.
	private static boolean execute(String[] args) {
		
		if (args.length == 0) {
			System.err.println("No command is found.");
			log.log(Level.SEVERE, "No command is found");
			return false;
		}
		
		else if (args[0].equals("readCSV")) {
			
			//ReadCSV needs one and only one parameter: filepath.
			if(args.length != 2) {
				System.err.println("Invalid number of arguments for readCSV.");
				log.log(Level.SEVERE, "Invalid number of arguments for readCSV.");
				return false;
			}
			
			else {
				DataFrameInterface temp = null;
				//By default, the separator of the CSV file is ','. Otherwise extend this code.
				CSVReaderInterface csvReader = new CSVReader();
				temp = csvReader.readCSV(args[1], ",");
				//If the CSV file is invalid. CSV_Reader will handle the IOException and return null.
				//No DataFrame will be read then.
				if (temp != null) {
					df = temp;
					//Data inconsistency: there are columns that have no data. More columns than items.
					//Or there are items that have no column names. More items than columns.
					if (csvReader.hasInconsistency()) {
						System.err.println("Read successfully but there is some inconsistency in the CSV.");
						log.log(Level.WARNING, "Data inconsistency in CSV file: " + args[1]);
					}
					return true;
				}
				else {
					System.err.println("Invalid file or filepath: " + args[1]);
					log.log(Level.SEVERE, "Invalid file or filepath: " + args[1]);
					return false;
				}
			}
		}
		
		//Assume you can sort the dataframe based on 'row' column and/or 'column' column. Otherwise extend this code.
		else if (args[0].equals("sort")) {
			
			//Sort must has at least two parameters.
			if (args.length < 3) {
				System.err.println("Invalid number of arguments for sort.");
				System.err.println("Need at least two columns to sort.");
				log.log(Level.SEVERE, "Invalid number of arguments for sort.");
				return false;
			}
			
			String[] sortColumns = Arrays.copyOfRange(args, 1, args.length);
			
			//Sort failure, find the reason.
			if (!df.sort(sortColumns)) {
				for (int i = 0; i < sortColumns.length; i++) {
					if (sortColumns[i] == null){
						System.err.println("There is a null in sortColumns.");
						log.log(Level.SEVERE, "There is a null in sortColumns.");
						return false;
					}
					else if (!df.isInColumns(sortColumns[i])) {
						System.err.println(sortColumns[i] + " is not a column.");
						log.log(Level.SEVERE, sortColumns[i] + " is not a column.");
						return false;
					}
				}
				System.err.println("Unknown failure at execute, sort.");
				log.log(Level.SEVERE, "Unknown failure at execute, sort");
				return false;
			}
			
			//Sort successfully.
			else {
				Main.sortColumns = sortColumns;
				return true;
			}
		}
		
		else if (args[0].equals("print")) {
			
			//By default, print the top 3 rows.
			if (args.length == 1) {
				if (df.length() < 3) {
					System.err.println("Less than 3 rows. Print all rows.");
					log.log(Level.WARNING, "Less than 3 rows. Print all rows.");
					print(df.length());
					return true;
				}
				else {
					print(3);
					return true;
				}
			}
			else if (args.length > 2) {
				System.err.println("Invalid number of arguments for print.");
				log.log(Level.SEVERE, "Invalid number of arguments for print");
				return false;
			}
			
			//Print can has an optional parameter.
			else{
				try{
					int rows = Integer.parseInt(args[1]);
					if (rows < 0 || rows > df.length()) {
						System.err.println("Invaild print rows: " + String.valueOf(rows));
						System.err.println("The dataframe has " + String.valueOf(df.length()) + " rows in total.");
						log.log(Level.SEVERE, "Invaild print rows: " + String.valueOf(rows));
						return false;
					}
					else {
						print(rows);
						return true;
					}
					
					//The parameter passed to print cannot be parsed into an integer.
				} catch (NumberFormatException e) {
					System.err.println("Invalid argument for print: " + args[1]);
					log.log(Level.SEVERE, "Invalid argument for print: " + args[1]);
					String msg = String.format(e.getMessage());
			        LogRecord lr = new LogRecord(Level.SEVERE, msg);
			        lr.setThrown(e);
			        log.log(lr);
					return false;
				}
			}
		}
		
		else if (args[0].equals("exit")) {
			System.exit(0);
			return true;
		}
		
		else{
			log.log(Level.SEVERE, "Invalid command: " + args[0]);
			System.err.println("Invalid command: " + args[0]);
			return false;
		}
	}
	
	//Overload.
	public static boolean execute (String arg) {
		if (arg == null) {
			System.err.println("Null command is found.");
			log.log(Level.SEVERE, "Null command is found.");
			return false;
		}
		else if (arg.equals("print")) {
			if (df.length() < 3) {
				System.err.println("Less than 3 rows. Print all rows.");
				log.log(Level.WARNING, "Less than 3 rows. Print all rows.");
				print(df.length());
				return true;
			}
			else {
				print(3);
				return true;
			}
		}
		else if (arg.equals("exit")) {
			System.exit(0);
			return true;
		}
		else if (arg.equals("readCSV") || arg.equals("sort")) {
			System.err.println("Lack arguments for: \"" + arg + "\".");
			log.log(Level.SEVERE, "Lack arguments for: \"" + arg + "\".");
			return false;
		}
		else {
			System.err.println("Ivalid command: \"" + arg + "\".");
			log.log(Level.SEVERE, "Ivalid command: \"" + arg + "\".");
			return false;
		}
	}
	
	public static void main(String[] args){
		
		//Set up logging system.
		try {
        	StringBuffer logPath = new StringBuffer();
        	logPath.append("./Log/");
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	logPath.append(sdf.format(new Date())+".log");
            FileHandler handler = new FileHandler(logPath.toString(),true);
            handler.setFormatter(new SimpleFormatter());
            log.addHandler(handler);
            log.setUseParentHandlers(false);
        } catch(Exception e) {
        	System.err.println("Serious corruption in logging system.");
        	System.exit(0);
        }
		
		Thread.setDefaultUncaughtExceptionHandler (new UncaughtExceptionHandler());
		
		log.log(Level.INFO, "----------------------------------------");
		
		//Run without parameters. Wait for further interaction.
		if (args.length == 0) {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			try {
				while ((line = stdin.readLine()) != null) {
					log.log(Level.INFO, "Input: " + line);
					
					//Filter all kinds of non-standard input.
					//Filter the spacings before and after the input.
					line = line.trim();
					
					//Split the input with spacing (1 or more).
					//There might be spacings in the columns' names. If so, surround the column name with ""
					String[] arguments = line.split("(\\s+\"|\"\\s+|\"\\s+\"|\")");
					if (arguments.length == 1) arguments = arguments[0].split("\\s+");
					execute(arguments);
				}
			} catch (IOException e) {
				//Failure in reading inputs. Log and Stop.
				LogRecord lr = new LogRecord(Level.SEVERE, e.getMessage());
		        lr.setThrown(e);
		        log.log(lr);
			}
		}
		//Run with parameters. Execute task:" read a CSV, sort and then print the top 3 rows".
		else if (args.length >= 3) {
			//Args: filename, sort_column1, sort_column2, ...
			
			//Log input.
			String input ="";
			for (String item : args) input = input + " " + item + " ";
			log.log(Level.INFO, "Input: " + input);
			
			try {
				//Change the input into standard format to call the execute().
				String[] readCSV = new String[2];
				readCSV[0] = "readCSV";
				readCSV[1] = args[0];
				String[] sort = new String[args.length];
				sort[0] = "sort";
				System.arraycopy(args, 1, sort, 1, args.length-1);
				
				//readCSV
				if (!execute(readCSV)) System.exit(0);
				
				//sort the dataframe;
				if (!execute (sort)) System.exit(0);
				
				//print the first 3 lines.
				if (!execute("print")) System.exit(0);
				
				//If there are hidden rows that are equal to the third row after sort, ask if to show them all.
				else {
					int i = 3;
					for (i = 3; i < df.length(); i++)
						if (!df.equals (2, i, sortColumns)) break;
					if (i != 3) {
						System.out.println("There are hidden rows that are equal to the third row after sort, do you want to show them all? (y/n)");
						log.log(Level.WARNING, "Hidden rows after sort");
						BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
						try {
							String line = new String();
							while ((line = stdin.readLine()) != null) {
								log.log(Level.INFO, "Input: " + line);
								line = line.trim();
								if (line.equals("Y") || line.equals("y")) {
									execute(new String[] {"print", String.valueOf(i)});
									break;
								}
								else if (line.equals("N") || line.equals("n")) break;
								else System.out.println("Invalid input. Please input Y/y or N/n.");
							}
						} catch (IOException e) {
							//Failure in reading inputs. Log and Stop.
							LogRecord lr = new LogRecord(Level.SEVERE, e.getMessage());
					        lr.setThrown(e);
					        log.log(lr);
						}
					}
				}
				
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Invalid input.");
				log.log(Level.SEVERE, "Invalid input.");
				String msg = String.format(e.getMessage());
		        LogRecord lr = new LogRecord(Level.SEVERE, msg);
		        lr.setThrown(e);
		        log.log(lr);
				System.exit(0);
			}		
		}
		//Run with parameters but the number is invalid. Log and stop.
		else {
			String input ="";
			for (String item : args) input = input + item;
			log.log(Level.INFO, "Input: " + input);
			System.err.println("Invalid input: " + input);
			System.err.println("You need at least 2 columns to sort.");
			log.log(Level.SEVERE, "Invalid input: " + input);
		}	
	}
}
