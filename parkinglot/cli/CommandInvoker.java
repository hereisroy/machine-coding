package parkinglot.cli;

import java.util.HashMap;
import java.util.Map;

import parkinglot.cli.command.Command;

public class CommandInvoker {
	Map<String, Command> cmdMap = new HashMap<>();
	
	public void register(String cmdType, Command cmd) {
		cmdMap.put(cmdType, cmd);
	}
	
	public void invoke(String line) {
		String[] tokens = line.split(" ");
		String cmdType;
		try {
			cmdType = tokens[0];
			Command cmd = cmdMap.get(cmdType);
			if(cmd==null)
				throw new IllegalArgumentException("Unsupported Command. Please enter a valid command.");
			cmd.parse(tokens);
			cmd.execute();
		} catch(IllegalArgumentException illArgEx) {
			System.out.println(illArgEx.getMessage());
		}
	}
}
