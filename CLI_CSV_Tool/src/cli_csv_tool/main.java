package cli_csv_tool;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import pandas.*;

public class Main {
	
	private static DataFrameInterface df = null;
	
	//Logging system, form a file each day. Initialized in main();
	private static Logger log = Logger.getLogger("ExceptionHandler");
	
	private static boolean execute(String[] args) {
		if (args.length == 0) {
			System.err.println("No command is found.");
			log.log(Level.SEVERE, "No command is found");
			return false;
		}
		else if (args[0].equals("readCSV")) {
			if(args.length != 2) {
				System.err.println("Invalid number of arguments for readCSV.");
				log.log(Level.SEVERE, "Invalid number of arguments for readCSV.");
				return false;
			}
			else {
				DataFrameInterface temp = null;
				//By default, the separator of the CSV file is ','. Otherwise extend this code.
				temp = CSVReader.readCSV(args[1], ",");
				//If the CSV file is invalid. CSV_Reader will handle the IOException and return null.
				//No DataFrame will be read then.
				if (temp != null) {
					df = temp;
					return true;
				}
				else {
					System.err.println("Invalid file or filepath: " + args[1]);
					log.log(Level.SEVERE, "Invalid file or filepath: " + args[1]);
					return false;
				}
			}
		}
		else if (args[0].equals("sort")) {
			if (args.length < 3) {
				System.err.println("Invalid number of arguments for sort.");
				System.err.println("Need at least two columns to sort.");
				log.log(Level.SEVERE, "Invalid number of arguments for sort.");
				return false;
			}
			String[] sortColumns = Arrays.copyOfRange(args, 1, args.length);
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
			else return true;
		}
		else if (args[0].equals("print")) {
			if (args.length == 1) {
				df.print(3);
				return true;
			}
			else if (args.length > 2) {
				System.err.println("Invalid number of arguments for print.");
				log.log(Level.SEVERE, "Invalid number of arguments for print");
				return false;
			}
			else{
				try{
					int rows = Integer.parseInt(args[1]);
					if (rows < 0 || rows > df.length()) {
						System.err.println("Invaild print rows: " + String.valueOf(rows));
						log.log(Level.SEVERE, "Invaild print rows: " + String.valueOf(rows));
						return false;
					}
					df.print(rows);
					return true;
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
	
	//Overload, not used in this challenge but saved as an useful API.
	public static boolean execute (String arg) {
		if (arg == null) {
			System.err.println("Null command is found.");
			log.log(Level.SEVERE, "Null command is found.");
			return false;
		}
		else if (arg.equals("print")) {
			df.print(3);
			return true;
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
		
		if (args.length == 0) {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			try {
				while ((line = stdin.readLine()) != null){
					log.log(Level.INFO, "Input: " + line);
					line = line.trim();
					String[] arguments = line.split("\\s+");
					execute(arguments);
				}
			} catch (IOException e) {
				LogRecord lr = new LogRecord(Level.SEVERE, e.getMessage());
		        lr.setThrown(e);
		        log.log(lr);
			}
		}
		else if (args.length >= 3) {
			//Args: filename, sort_column1, sort_column2, ...
			
			log.log(Level.INFO, "Input: " + args.toString());
			//readCSV
			try {
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
		else {
			log.log(Level.INFO, "Input: " + args.toString());
			System.err.println("Invalid input: " + args.toString());
			log.log(Level.SEVERE, "Invalid input: " + args.toString());
		}	
	}
}
