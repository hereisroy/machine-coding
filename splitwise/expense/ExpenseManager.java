package splitwise.expense;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import splitwise.splitCalc.SplitCalc;
import splitwise.splitCalc.SplitCalcManager;
import splitwise.splitCalc.UnsupportedSplitTypeException;
import splitwise.user.User;

public class ExpenseManager {
	
	List<Expense> expenses;
	private HashMap<String, User> users = new HashMap<>();
	private LinkedHashMap<User, LinkedHashMap<User, Double>> balanceSheet = new LinkedHashMap<>();
	
	private ExpenseManager() {}
	
	private static class ExpenseManagerHolder{
		private static final ExpenseManager instance = new ExpenseManager();
	}
	
	public static ExpenseManager getInstance() {
		return ExpenseManagerHolder.instance;
	}
	
	public void addMember(User newMember) {
		users.put(newMember.getUserId(), newMember);
		LinkedHashMap<User, Double> newMemberBalances = new LinkedHashMap<>();
		LinkedHashMap<User, Double> currMemberBalances;
		User currMember;
		for(Entry<User, LinkedHashMap<User, Double>> entry : balanceSheet.entrySet()) {
			currMember = entry.getKey();
			currMemberBalances = entry.getValue();
			currMemberBalances.put(newMember, 0D);
			newMemberBalances.put(currMember, 0D);
		}
		balanceSheet.put(newMember, newMemberBalances);
	}
	
	public User getUser(String uid) {
		return users.get(uid);
	}
	
	public void addExpense(Expense exp) throws UnsupportedSplitTypeException {
		SplitCalcManager calcManager = SplitCalcManager.getInstance();
		SplitCalc calc = calcManager.getCalc(exp.getSplitType());
		if(calc==null) throw new UnsupportedSplitTypeException("Unsupported Split Type");
		calc.split(exp);
		User currUser, payedByUser = exp.getPayedBy();
		double balance, existingBalance;
		for(Entry<User, Double> entry : exp.getBalance().entrySet()) {
			currUser = entry.getKey(); 
			if(currUser==payedByUser) continue;
			balance = entry.getValue();
			existingBalance = balanceSheet.get(currUser).get(payedByUser);
			balanceSheet.get(currUser).put(payedByUser, existingBalance+balance);
			existingBalance = balanceSheet.get(payedByUser).get(currUser);
			balanceSheet.get(payedByUser).put(currUser, existingBalance-balance);
		}
	}
	
	
	public LinkedHashMap<User, LinkedHashMap<User, Double>> getBalanceSheet(){
		LinkedHashMap<User, LinkedHashMap<User, Double>> res = new LinkedHashMap<>();
		User currUser, owesToUser;
		LinkedHashMap<User, Double> currUserBalance = new LinkedHashMap<>();
		double balance;
		for(Entry<User, LinkedHashMap<User, Double>> entry1 : balanceSheet.entrySet()) {
			currUser = entry1.getKey();
			for(Entry<User, Double> entry2 : entry1.getValue().entrySet()) {
				owesToUser = entry2.getKey();
				balance = entry2.getValue();
				if(balance<=0) continue;
				currUserBalance.put(owesToUser, balance);
			}
			if(currUserBalance.size()>0) {
				res.put(currUser, currUserBalance);
				currUserBalance = new LinkedHashMap<>();
			}
		}
		
		return res;
	}
	
	public LinkedHashMap<User, Double> getBalanceForUser(User userX){
		LinkedHashMap<User, Double> res = new LinkedHashMap<>();
		User userY;
		double balance;
		for(Entry<User, Double> entry : balanceSheet.get(userX).entrySet()) {
			userY = entry.getKey();
			balance = entry.getValue();
			if(balance==0) continue;
			res.put(userY, balance);
		}
		return res;
	}
	
}
