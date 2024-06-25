package splitwise.cli;

import java.util.HashMap;
import java.util.Map;

import splitwise.command.Command;

public class CommandInvoker {
	
	private Map<String, Command> commands = new HashMap<>();
	
	public void registerCommand(String key, Command cmd) {
		commands.put(key, cmd);
	}
	
	public void invokeCommand(String line) {
		String[] tokens = line.split(" ");
		Command cmd = commands.get(tokens[0]);
		if(cmd==null) {
			System.out.println("Invalid Command");
			return;
		}
		try {
			cmd.parse(tokens);
			cmd.execute();
		} catch(IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
