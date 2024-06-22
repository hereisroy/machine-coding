package parkinglot.cli.command;

public interface Command {
	
	void parse(String[] tokens) throws IllegalArgumentException;
	void execute();
	
}
