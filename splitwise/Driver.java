package splitwise;

import java.util.Scanner;

import splitwise.cli.CommandInvoker;
import splitwise.command.AddExpenseCommand;
import splitwise.command.ShowCommand;
import splitwise.expense.ExpenseManager;
import splitwise.splitCalc.EquallySplitCalc;
import splitwise.splitCalc.ExactSplitCalc;
import splitwise.splitCalc.PercentSplitCalc;
import splitwise.splitCalc.SplitCalcManager;
import splitwise.user.User;

public class Driver {

	static CommandInvoker invoker;
	static Scanner inp;

	static {
		invoker = new CommandInvoker();
		invoker.registerCommand("EXPENSE", new AddExpenseCommand());
		invoker.registerCommand("SHOW", new ShowCommand());
		inp = new Scanner(System.in);
	}

	public static void main(String args[]) {
	
		ExpenseManager manager = ExpenseManager.getInstance();
		manager.addMember(new User("u1", "user1", "user1@hereisroy.online", "1001547180"));
		manager.addMember(new User("u2", "user2", "user2@hereisroy.online", "2001547180"));
		manager.addMember(new User("u3", "user3", "user3@hereisroy.online", "3001547180"));
		manager.addMember(new User("u4", "user4", "user4@hereisroy.online", "4001547180"));

		SplitCalcManager calcManager = SplitCalcManager.getInstance();
		calcManager.registerCalc("EQUAL", new EquallySplitCalc());
		calcManager.registerCalc("EXACT", new ExactSplitCalc());
		calcManager.registerCalc("PERCENT", new PercentSplitCalc());

		System.out.println("Welcome to Splitwise");
		String line;
		while(true) {
			System.out.print(">");
			line = inp.nextLine();
			if(line.equalsIgnoreCase("exit")) {
				System.out.println("Thanks for using SplitWise. Bye Bye.");
				break;
			}
			invoker.invokeCommand(line);
		}
	}

}
