PROGS=cli_csv_tool/Main.class

all: $(PROGS)

%.class: %.java
	javac $*.java

.PHONY: run clean clean_log clean_all test_CSVReaer test_DataFrame test_Main
run:
	@javac cli_csv_tool/Main.java
	@java cli_csv_tool.Main $(filter-out $@,$(MAKECMDGOALS))
%:
	@:

clean:
	rm -f cli_csv_tool/*.class
	rm -f pandas/*.class
	rm -f Test/*.class

clean_log:
	rm -f Log/*.log*

clean_all:
	rm -f cli_csv_tool/*.class
	rm -f pandas/*.class
	rm -f Test/*.class
	rm -f Log/*.log*

test_CSVReader:
	@javac -cp .:lib/junit-4.10.jar Test/CSVReaderTestRunner.java
	@java -cp .:lib/junit-4.10.jar Test.CSVReaderTestRunner

test_DataFrame:
	@javac -cp .:lib/junit-4.10.jar Test/DataFrameTestRunner.java
	@java -cp .:lib/junit-4.10.jar Test.DataFrameTestRunner

test_Main:
	@javac -cp .:lib/junit-4.10.jar Test/MainTestRunner.java
	@java -cp .:lib/junit-4.10.jar Test.MainTestRunner
