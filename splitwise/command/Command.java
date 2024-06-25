package splitwise.command;

public interface Command {
	
	void parse(String[] tokens) throws IllegalArgumentException;
	void execute();
}
