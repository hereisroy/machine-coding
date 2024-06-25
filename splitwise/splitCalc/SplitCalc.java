package splitwise.splitCalc;

import splitwise.expense.Expense;

public abstract class SplitCalc {
	
	public abstract void split(Expense exp);
	
	protected double roundOff(double num) {
		return (double)Math.round(num*100)/100;
	}
	
}
