# CLI_CSV_Tool
This is a small command line interface tool to read and sort CSV data.

# Architecture
This command line tool is based on the idea of DataFrame provided in pandas library in Python.
Please note that it will be much easier to implement this by simply calling the pandas library.
All codes implemented in this Java version is original.
Package pandas in this code is designed to be independent library.

# Deploy
Download or git clone the project to a local repository.
open terminal and cd to CLI_CSV_Tool/src

To compile, input: 

  make
  
To compile and run, input: 

  make run filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ...
  
To run after compile, input:

  java cli_csv_tool.Main filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ...

You can also just run the tool without parameters.
  java cli_csv_tool.Main
The tool will open and wait for further interactions.
Some APIs are as follows:
  readCSV filepath.csv
  sort column_1 column_2 ...
  print rows (this allows you to print any legitamate rows of the data in YAML format)
  exit
