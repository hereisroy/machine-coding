package splitwise.expense;

import java.util.LinkedHashMap;

import splitwise.user.User;

public class Expense {
	
	private User payedBy;
	private double amount;
	private String splitType;
	private LinkedHashMap<User, Double> split;
	private LinkedHashMap<User, Double> balance;
	
	
	
	public Expense(User payedBy, double amount, String splitType, LinkedHashMap<User, Double> split) {
		super();
		this.payedBy = payedBy;
		this.amount = amount;
		this.splitType = splitType;
		this.split = split;
		this.balance = new LinkedHashMap<>();
	}
	public User getPayedBy() {
		return payedBy;
	}
	public void setPayedBy(User payedBy) {
		this.payedBy = payedBy;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSplitType() {
		return splitType;
	}
	public LinkedHashMap<User, Double> getSplit() {
		return split;
	}
	public void setSplit(LinkedHashMap<User, Double> split) {
		this.split = split;
	}
	public LinkedHashMap<User, Double> getBalance() {
		return balance;
	}
	public void setBalance(LinkedHashMap<User, Double> balance) {
		this.balance = balance;
	}
}
