package com.bankapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.RandomAccessFile;
import java.util.ArrayList;


/**
 * Servlet implementation class Startup
 */
@WebServlet("/Startup")
public class Startup extends HttpServlet {
	private static final long serialVersionUID = 1L;    
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() throws ServletException {
		Global.gProfiles = new ArrayList<Profile>();
		Global.gHistory = new HistoryRecord();
		String tempProfileLine = null;
		String tempHistoryLine = null;
		String[] profileTokens;
		String[] historyTokens;
		String accName;
		BankAccountType accType;
		double accBal;
		int lineNum = 0;
		
		try {
			//put profiles.txt data into Global variable gProfileObjects
			Global.gProfileObjects = new RandomAccessFile("C:\\Users\\Kenny Li\\Documents\\Bank_Application\\src\\com\\bankapp\\profiles.txt", "rw");
			Global.gProfileObjects.seek(0);
			
			//put history.txt data into Global variable gHistoryObjects
			Global.gProfileObjects = new RandomAccessFile("C:\\Users\\Kenny Li\\Documents\\Bank_Application\\src\\com\\bankapp\\history.txt", "rw");
			Global.gProfileObjects.seek(0);
		
			while((tempProfileLine = Global.gProfileObjects.readLine()) != null){
				System.out.println(tempProfileLine);

				profileTokens = tempProfileLine.split(",");
				
				Global.gProfiles.add(new Profile(profileTokens[0], Integer.parseInt(profileTokens[1])));
				
				for(int i = 2; i < profileTokens.length; i += 3){
					accName = profileTokens[i];
					accType = BankAccountType.valueOf(profileTokens[i+1]);
					accBal = Double.parseDouble(profileTokens[i+2]);
					Global.gProfiles.get(lineNum).addBankAcc(new BankAccount(accName, accType, accBal));
				}
				lineNum++;
			}//end reading profiles.txt
			
			lineNum = 0;
			while((tempHistoryLine = Global.gHistoryObjects.readLine()) != null){
				historyTokens = tempHistoryLine.split(",");
				Global.gHistory.addHistory(historyTokens[0], historyTokens[1]);
				lineNum++;
			}
			
			//profileObjects.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-------- Bank Application Initiated --------");
	}
	
    public Startup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
