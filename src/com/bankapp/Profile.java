package com.bankapp;

import java.util.List;
import java.util.ArrayList;

import com.bankapp.BankAccount;

public class Profile {
	private int uid;
	private String username;
	private List<BankAccount> bankAccs;
	
	//constructor
	public Profile(String user, int id){
		username = user;
		uid = id;
		bankAccs = new ArrayList<BankAccount>();
	}
	
	public String getUsername(){
		return username;
	}
	
	public int getUid(){
		return uid;
	}
	
	public void addBankAcc(BankAccount acc){
		bankAccs.add(acc);
	}
	
	public BankAccount getBankAcc(int i){
		return bankAccs.get(i);
	}
	
	public int getBankAccNum(){
		return bankAccs.size();
	}
	
	//if true, redirect user to html page with "delete account success" message
	//otherwise, redirect user to html page with "Balance not empty" message
	public boolean removeBankAcc(BankAccount acc){
		if(bankAccs.get(bankAccs.indexOf(acc)).removable()){
			bankAccs.remove(acc);
			return true;
		}else{
			return false;
		}
			
	}
	
	public double getSumBalance(){
		double sum = 0.0;
		for(int i = 0; i < bankAccs.size(); i++){
			sum += bankAccs.get(i).getBalance();
		}
		return sum;
	}
	
	
}
