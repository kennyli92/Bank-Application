package com.bankapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddAccount
 */
@WebServlet("/AddAccount")
public class AddAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean matchFlag = false;
		String accName = request.getParameter("accName");
		BankAccountType accType = BankAccountType.valueOf(request.getParameter("accType"));
		response.setContentType("text/html");
		String docType = "<!DOCTYPE html>\n";
		PrintWriter out = response.getWriter();
		String title = "Welcome " + Global.gUsername + "!";
		//Changes matchFlag to true if existing account name matches with submitted account name
		for(int i = 0; i < Global.gProfile.getBankAccNum(); i++){
			if(accName.equals(Global.gProfile.getBankAcc(i).getName())){
				matchFlag = true;
			}
		}
		if(matchFlag){
			out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1 align=\"center\">" + title + "</h1>\n" +
	                "<h1 align=\"center\">" + "Account name already exist!" + "</h1>\n" +
	                
	                "<ul>\n" +
	                
	                "<li><form action=\"Transfer\" method=\"GET\">" +
	                "From: Account Name <input type=\"text\" name=\"fromName\">" +
	                " Money Amount <input type=\"text\" name=\"fromAmount\"><br>" + 
	                "To: Account Name <input type=\"text\" name=\"toName\"><br>" +
	                "<input type=\"submit\" value=\"Transfer Fund\" />"
	                + "</form></li><br>" +
	                
	                
					"<li><form action=\"AddAccount\" method=\"GET\">" +
					"Account Name <input type=\"text\" name=\"accName\"><br>" +
					"Account Type: <Select name=\"accType\">" +
					"<option selected>CHECKING<option>SAVING<option>CD</select><br>" + 
					"<input type=\"submit\" value=\"Add Account\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"DeleteAccount\" method=\"GET\">" +
					"Account Name <input type=\"text\" name=\"delName\"><br>" +
					"<input type=\"submit\" value=\"Delete Account\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"ViewBalance\" method=\"GET\">" +
					"<input type=\"submit\" value=\"View Account Balances\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"SumBalance\" method=\"GET\">" +
					"<input type=\"submit\" value=\"View Sum Account Balance\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"History\" method=\"GET\">" +
					"<input type=\"submit\" value=\"View History\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"Logout\" method=\"GET\">" +
					"<input type=\"submit\" value=\"Logout\" />"
					+ "</form></li>" +
	                "</ul>\n" +
	                "</body></html>");
		}else{
			//create new bank acc and add to gProfile
			Global.gProfile.addBankAcc(new BankAccount(accName, accType, 0.0));
			
			out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1 align=\"center\">" + title + "</h1>\n" +
	                "<h1 align=\"center\">" + "Account Successfully Created!" + "</h1>\n" +
	                
	                "<ul>\n" +
	                
	                "<li><form action=\"Transfer\" method=\"GET\">" +
	                "From: Account Name <input type=\"text\" name=\"fromName\">" +
	                " Money Amount <input type=\"text\" name=\"fromAmount\"><br>" + 
	                "To: Account Name <input type=\"text\" name=\"toName\"><br>" +
	                "<input type=\"submit\" value=\"Transfer Fund\" />"
	                + "</form></li><br>" +
	                
	                
					"<li><form action=\"AddAccount\" method=\"GET\">" +
					"Account Name <input type=\"text\" name=\"accName\"><br>" +
					"Account Type: <Select name=\"accType\">" +
					"<option selected>CHECKING<option>SAVING<option>CD</select><br>" + 
					"<input type=\"submit\" value=\"Add Account\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"DeleteAccount\" method=\"GET\">" +
					"Account Name <input type=\"text\" name=\"delName\"><br>" +
					"<input type=\"submit\" value=\"Delete Account\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"ViewBalance\" method=\"GET\">" +
					"<input type=\"submit\" value=\"View Account Balances\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"SumBalance\" method=\"GET\">" +
					"<input type=\"submit\" value=\"View Sum Account Balance\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"History\" method=\"GET\">" +
					"<input type=\"submit\" value=\"View History\" />"
					+ "</form></li><br>" +
					
					"<li><form action=\"Logout\" method=\"GET\">" +
					"<input type=\"submit\" value=\"Logout\" />"
					+ "</form></li>" +
	                "</ul>\n" +
	                "</body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
