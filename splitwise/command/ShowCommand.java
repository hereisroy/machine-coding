package splitwise.command;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import splitwise.expense.ExpenseManager;
import splitwise.user.User;

public class ShowCommand implements Command {
	
	User user;

	@Override
	public void parse(String[] tokens) throws IllegalArgumentException {
		if(tokens.length!=1 && tokens.length!=2) 
			throw new IllegalArgumentException("Invalid number of parameters");
		if(tokens.length==1) user = null;
		else {
			ExpenseManager manager = ExpenseManager.getInstance();
			user = manager.getUser(tokens[1]);
			if(user==null)
				throw new IllegalArgumentException("Invalid userId");
		}
	}

	@Override
	public void execute() {
		ExpenseManager manager = ExpenseManager.getInstance();
		if(user==null) {
			LinkedHashMap<User, LinkedHashMap<User, Double>> balanceSheet;
			balanceSheet = manager.getBalanceSheet();
			User userX, userY;
			double balance;
			LinkedHashMap<User, Double> currUserBalanceSheet;
			for(Entry<User, LinkedHashMap<User, Double>> entry : balanceSheet.entrySet()) {
				userX = entry.getKey();
				currUserBalanceSheet = entry.getValue();
				for(Entry<User, Double> entry2 : currUserBalanceSheet.entrySet()) {
					userY = entry2.getKey();
					balance = entry2.getValue();
					System.out.printf("%s owes %s: %.2f\n", userX.getName(), userY.getName(), balance);
				}
			}
			
			if(balanceSheet.size()==0)
				System.out.println("No balances");
		} else {
			LinkedHashMap<User, Double> balanceSheet;
			balanceSheet = manager.getBalanceForUser(user);
			User userX, userY;
			double balance;
			for(Entry<User, Double> entry : balanceSheet.entrySet()) {
				balance = entry.getValue();
				if(balance>0) {
					userX = user;
					userY = entry.getKey();
				} else {
					userX = entry.getKey();
					userY = user;
					balance *= -1;
				}
				
				System.out.printf("%s owes %s: %.2f\n", userX.getName(), userY.getName(), balance);
			}
			
			if(balanceSheet.size()==0)
				System.out.println("No balances");
		}
		
	}

}
