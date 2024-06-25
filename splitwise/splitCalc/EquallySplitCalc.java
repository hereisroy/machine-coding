package splitwise.splitCalc;

import splitwise.expense.Expense;
import splitwise.user.User;

public class EquallySplitCalc extends SplitCalc {

	@Override
	public void split(Expense exp) {
		int memCount = exp.getSplit().size();
		double perHeadAmount = roundOff(exp.getAmount()/memCount);
		for(User user : exp.getSplit().keySet())
			exp.getBalance().put(user, perHeadAmount);
	}

}
