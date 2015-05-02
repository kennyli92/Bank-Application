package com.bankapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankapp.Profile;

import java.io.RandomAccessFile;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class Startup
 */
@WebServlet("/Startup")
public class Startup extends HttpServlet {
	private static final long serialVersionUID = 1L;    
	public static List<Profile> profiles;
	public static RandomAccessFile profileObjects;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() throws ServletException {
		profiles = new ArrayList<Profile>();
		String tempProfileLine = "default";
		String[] profileTokens;
		String accName;
		BankAccountType accType;
		double accBal;
		int lineNum = 0;
		
		try {
			System.out.println("1");
			profileObjects = new RandomAccessFile("profiles.txt", "rw");
			System.out.println("2");
			profileObjects.seek(0);
			System.out.println(Long.toString(profileObjects.length()));
			while((tempProfileLine = profileObjects.readLine()) != null){
				System.out.println(tempProfileLine);
				System.out.println("3");
				profileTokens = tempProfileLine.split(",");
				profiles.add(new Profile(profileTokens[0], Integer.parseInt(profileTokens[1])));
				
				for(int i = 2; i < profiles.size(); i += 3){
					accName = profileTokens[i];
					accType = BankAccountType.valueOf(profileTokens[i+1]);
					accBal = Double.parseDouble(profileTokens[i+2]);
					profiles.get(lineNum).addBankAcc(new BankAccount(accName, accType, accBal));
				}
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
