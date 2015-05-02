package com.bankapp;

import java.util.List;
import java.util.ArrayList;

public class HistoryRecord {
	private List<String> historyList;
	
	public HistoryRecord(){
		historyList = new ArrayList<String>();
	}
	
	public void addTransferHistory(String fromName, String toName, double fromAmt){
		historyList.add(new String("Transfer fund: " + Double.toString(fromAmt) + " from " + fromName + " to " + toName));
	}
	
	public void addAddAccHistory(String accName, String accType){
		historyList.add(new String("Added Account: " + accName + " of " + accType + " type."));
	}
	
	public void addDelAccHistory(String accName, String accType){
		historyList.add(new String("Deleted Account: " + accName + " of " + accType + " type."));
	}
	
	public String viewHistory(){
		String history = "";
		for(int i = 0; i < historyList.size(); i++){
			history += historyList.get(i) + "<br>";
		}
		return history;
	}
}
