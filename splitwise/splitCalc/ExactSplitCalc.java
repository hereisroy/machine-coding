package splitwise.splitCalc;

import java.util.Map.Entry;

import splitwise.expense.Expense;
import splitwise.user.User;

public class ExactSplitCalc extends SplitCalc {

	@Override
	public void split(Expense exp) {
		User currUser;
		double balance;
		for(Entry<User, Double> entry : exp.getSplit().entrySet()) {
			currUser = entry.getKey();
			balance = entry.getValue();
			exp.getBalance().put(currUser, balance);
		}
	}

}
