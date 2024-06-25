package splitwise.splitCalc;

import java.util.Map.Entry;

import splitwise.expense.Expense;
import splitwise.user.User;

public class PercentSplitCalc extends SplitCalc {

	@Override
	public void split(Expense exp) {
		double total = exp.getAmount(), percent, balance;
		User currUser;
		for(Entry<User, Double> entry : exp.getSplit().entrySet()) {
			currUser = entry.getKey();
			percent = entry.getValue();
			balance = roundOff(total*percent/100);
			exp.getBalance().put(currUser, balance);
		}
	}

}
