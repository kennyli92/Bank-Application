package com.bankapp;

import com.bankapp.BankAccountType;

public class BankAccount {
	private BankAccountType type;
	private double balance;
	private String name;
	
	//constructor
	public BankAccount(String accName, BankAccountType accType, double bal){
		name = accName;
		type = accType;
		balance = bal;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void addBalance(double amount) {
		balance += amount;
	}
	
	public BankAccountType getType(){
		return type;
	}
	
	public boolean transferFund(double amount, BankAccount receiver){
		if(this.balance < amount){
			return false;
		}
		this.balance -= amount;
		receiver.addBalance(amount);
		return true;
		//update randomAccessFile
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String aName){
		name = aName;
	}
	
	public boolean removable(){
		if(balance == 0.0){
			return true;
		}
		return false;
	}
	//viewHistory method
	
	
}
