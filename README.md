# CLI_CSV_Tool
This is a small command line interface tool to read and sort CSV data.

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
　　make

## Run the Tool
There are two modes provided in this tool. You either run with aprameters, when the tool will automatically read a CSV file, sort on specified columns, print the top 3 results and stop. <br/>
You can also run without parameter. In that case, the tool accepts a few function calls later and provide more freedom.

To compile and run, input: <br/>
　　make run filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ...

To run after compile, input:<br/>
　　java cli_csv_tool.Main filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ...

You can also just run the tool without parameters.<br/>
　　java cli_csv_tool.Main
  
The tool will open and wait for further interactions.<br/>
Some APIs are as follows:

　　readCSV filepath.csv
  
　　sort column_1 column_2 ...
  
　　print rows (this allows you to print any legitamate rows of the data in YAML format)
  
　　exit

#Note
You can still sort on "row" and/or "column" when they are not data. There will be no warning or data corruption. <br/>
You can only sort on the columns whoses data can be parsed into numbers (doublee, single, integer). Other types are generally uncomparable. However, you will not get a mistake message if you try to sort on them. The tool will give up sorting on these columns and try to sort based on other parameters. <br/>
Empty items and other unparsable items will be sorted to the back.
