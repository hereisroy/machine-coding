package splitwise.command;

import java.util.LinkedHashMap;

import splitwise.expense.Expense;
import splitwise.expense.ExpenseManager;
import splitwise.splitCalc.UnsupportedSplitTypeException;
import splitwise.user.User;

public class AddExpenseCommand implements Command {
	
	User payedBy;
	double amount;
	String splitType;
	LinkedHashMap<User, Double> split;

	@Override
	public void parse(String[] tokens) throws IllegalArgumentException {
		try {
			int index = 1;
			ExpenseManager expManager = ExpenseManager.getInstance();
			payedBy = expManager.getUser(tokens[index++]);
			if(payedBy==null) throw new IllegalArgumentException("Invalid UserId");
			amount = Double.parseDouble(tokens[index++]);
			int numUsers = Integer.parseInt(tokens[index++]);
			int splitTypeIndex = numUsers + index;
			split = new LinkedHashMap<>();
			User currUser;
			while(index<splitTypeIndex) {
				currUser = expManager.getUser(tokens[index++]);
				if(currUser==null) throw new IllegalArgumentException("Invalid UserId in expense members");
				split.put(currUser, 0D);
			}
			splitType = tokens[index++];
			if(!splitType.equalsIgnoreCase("EQUAL")) {
				double splitValue, sum=0;
				for(User user : split.keySet()) {
					splitValue = Double.parseDouble(tokens[index++]);
					split.put(user, splitValue);
					sum+=splitValue;
				}
				if(splitType.equalsIgnoreCase("EXACT") && sum!=amount)
					throw new IllegalArgumentException("Invalid ammounts for EXACT split");
				if(splitType.equalsIgnoreCase("PERCENT") && sum!=100)
					throw new IllegalArgumentException("Invalid percentages for split");
			}
		} catch(NumberFormatException nfex) {
			throw new IllegalArgumentException("Invalid Arguments to add Expense");
		}	
	}

	@Override
	public void execute() {
		try {
			ExpenseManager expManager = ExpenseManager.getInstance();
			Expense exp = new Expense(payedBy, amount, splitType, split);
			expManager.addExpense(exp);
		} catch(UnsupportedSplitTypeException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
