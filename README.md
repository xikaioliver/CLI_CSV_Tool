# CLI_CSV_Tool
This is a small command line interface tool to read and sort CSV data.

# Architecture
This command line tool is based on the idea of DataFrame provided in pandas library in Python.
Please note that it will be much easier to implement this by simply calling the pandas library.
All codes implemented in this Java version is original.

# Deploy
Download or git clone the project to a local repository.
open terminal and cd to CLI_CSV_Tool/src

To compile, input 
  make
  
To compile and run, input 
  make run filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ...
  
To run after compile, input
  java cli_csv_tool.Main filepath column_to_sort_1 column_to_sort_2 column_to_sort_3 ...
