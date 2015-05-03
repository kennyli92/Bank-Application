package com.bankapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewBalance
 */
@WebServlet("/ViewBalance")
public class ViewBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBalance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accBalances = "";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Account Balances Page";
	    String docType = "<!DOCTYPE html>\n";
	    for(int i = 0; i < Global.gProfile.getBankAccNum(); i++){
        	accBalances = accBalances + "<li>Account name: " +
        					Global.gProfile.getBankAcc(i).getName() + "<br>" +
        					"Account Type: " + Global.gProfile.getBankAcc(i).getType().toString() + "<br>" +
        					"Account Balance: " + Double.toString(Global.gProfile.getBankAcc(i).getBalance()) +
        					"</li><br><br>";
        	System.out.println(accBalances);
        }
	    
	    out.println(docType +
	        "<html>\n" +
	        "<head><title>" + title + "</title></head>\n" +
	        "<body bgcolor=\"#f0f0f0\">\n" +
	        "<h1 align=\"center\">" + title + "</h1>\n" +
	        "<ul>\n" + accBalances
	    	+ "</ul>\n"
	    	+ "<FORM> <INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>"
	        + "</body></html>"
	    );//end out.println
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
