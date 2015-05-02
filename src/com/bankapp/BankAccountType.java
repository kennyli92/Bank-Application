package com.bankapp;

public enum BankAccountType {
	CHECKING ("CHECKING"), 
	SAVING ("SAVING"), 
	CD ("CD");
	
	private final String type;
	
	private BankAccountType(String accType){
		type = accType;
	}
	
	public String toString(){
		return type;
	}
}
