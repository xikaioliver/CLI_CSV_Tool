PROGS=cli_csv_tool/Main.class

all: $(PROGS)

%.class: %.java
	javac $*.java

.PHONY: run clean
run:
	@javac cli_csv_tool/Main.java
	@java cli_csv_tool.Main $(filter-out $@,$(MAKECMDGOALS))
%:
	@:

clean:
	rm -f $(PROGS) *.class
