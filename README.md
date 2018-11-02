# CLI_CSV_Tool
This is a small command line interface tool to read and sort CSV data. <br/>

# Architecture
This command line tool is based on the idea of DataFrame provided in pandas library in Python. <br/>
Please note that it will be much easier to implement this by simply calling the pandas library. <br/>
All codes implemented in this Java version is original. <br/>
Package pandas in this code is designed to be independent library. <br/>

# Linux and Mac OS
## Deploy and Compile.
Download or git clone the project to a local repository. <br/>
open terminal and cd to CLI_CSV_Tool/src. <br/>

To compile, input:<br/>
　　make <br/>

## Run the Tool
There are two modes provided in this tool. You can run with prameters, when the tool will automatically read a CSV file, sort on specified columns, print the top 3 results and stop. <br/>
You can also run without parameter. In that case, the tool accepts a few function calls later and provide more freedom. <br/>

To compile and run, input: <br/>
　　make run filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ... <br/>
For example: make run input-data.csv EH_cm PH_cm <br/>

To run after compile, input:<br/>
　　java cli_csv_tool.Main filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ... <br/>

You can also just run the tool without parameters.<br/>
　　java cli_csv_tool.Main <br/>

Note: If there are any spacings in a parameter, you need to put it in the "". <br/>
For example, java cli_csv_tool.Main input-data.csv "GW_g__ FieldWB" EarsHvst_ears_plot <br/>
Spacings befor, after or between parameters are not strictly limited. <br/>
  
The tool will open and wait for further interactions.<br/>
Some APIs are as follows: <br/>

　　readCSV filepath.csv <br/>
　　sort column_1 column_2 ... (Needs at least two columns) <br/>
　　print rows(optional) (print certain top rows of data. By default, print top 3 rows without parameter). <br/>
　　exit <br/>

## Clean files
The API to clean runtime files:

  make clean (clean all class files) <br/>
  make clean_log (clean logs) <br/>
  make clean_all (clean all runtime files) <br/>
  
# Windows
The way to use this tool under Windows is generally the same as it is under Linux or Mac OS. However, makefile requires more work to deply under Windows. Therefore, the easiest way is to compile and run directly. <br/>

## Compile <br/>
javac cli_csv_tool\Main.java <br/>

## Run
java cli_csv_tool.Main filepath.csv column_to_sort_1 column_to_sort_2 column_to_sort_3 ... <br/>
or <br/>
java cli_csv_tool.Main <br/>

# Logs
The tool generate log files daily, stored in the Log folder. <br/>

# Tests
Some JUnit tests have been implemented under the Test folder. <br/>
To run tests under Linux or Mac OS: <br/> 
make test_CSVReader (test CSVReader) <br/>
make test_DataFrame (test DataFrame) <br/>
make test_Main (test Main UI class) <br/>

To run tests under Windows: <br/>
javac -cp .:lib/junit-4.10.jar Test/CSVReaderTestRunner.java <br/>
java -cp .:lib/junit-4.10.jar Test.CSVReaderTestRunner <br/>
javac -cp .:lib/junit-4.10.jar Test/DataFrameTestRunner.java <br/>
java -cp .:lib/junit-4.10.jar Test.DataFrameTestRunner <br/>
javac -cp .:lib/junit-4.10.jar Test/MainTestRunner.java <br/>
java -cp .:lib/junit-4.10.jar Test.MainTestRunner <br/>

# Note
You can still sort on "row" and/or "column" when they are not data. There will be no warning or data corruption. <br/>
You can only sort on the columns whoses data can be parsed into numbers (doublee, single, integer). Other types are generally uncomparable. However, you will not get a mistake message if you try to sort on them. The tool will give up sorting on these columns and try to sort based on other parameters. <br/>
Empty items and other unparsable items will be sorted to the back. <br/>
