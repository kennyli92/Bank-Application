package com.bankapp;

import java.util.List;
import java.util.ArrayList;

public class HistoryRecord {
	private List<String> historyList;
	private List<String> userList;
	
	public HistoryRecord(){
		historyList = new ArrayList<String>();
		userList = new ArrayList<String>();
	}
	
	public void addTransferHistory(String user, String fromName, String toName, double fromAmt){
		userList.add(new String(user));
		historyList.add(new String("Transfer fund: " + Double.toString(fromAmt) + " from " + fromName + " to " + toName));
	}
	
	public void addAddAccHistory(String user, String accName, String accType){
		userList.add(new String(user));
		historyList.add(new String("Added Account: " + accName + " of " + accType + " type."));
	}
	
	public void addDelAccHistory(String user, String accName, String accType){
		userList.add(new String(user));
		historyList.add(new String("Deleted Account: " + accName + " of " + accType + " type."));
	}
	
	public String viewHistory(String user){
		String history = "";
		for(int i = 0; i < historyList.size(); i++){
			if(userList.get(i).equals(user)){
				history += historyList.get(i) + "<br>";
			}
		}
		return history;
	}
}
