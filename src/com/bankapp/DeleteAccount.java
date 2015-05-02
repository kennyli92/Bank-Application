package com.bankapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String delName = request.getParameter("delName");
		boolean removeBankAccSuccess =  false;
		response.setContentType("text/html");
		String docType = "<!DOCTYPE html>\n";
		String htmlSelectAccName = null;
		String title = null;
		PrintWriter out = response.getWriter();
		
		for(int i = 0; i < Global.gProfile.getBankAccNum(); i++){
			if(Global.gProfile.getBankAcc(i).getName().equals(delName)){
				removeBankAccSuccess = Global.gProfile.removeBankAcc(Global.gProfile.getBankAcc(i));
			}
		}
		
		title = "Welcome " + Global.gUsername + "!";
		  for(int i = 0; i < Global.gProfile.getBankAccNum(); i++){
			  if(i == 0){
				  htmlSelectAccName = "<option selected>" + Global.gProfile.getBankAcc(i).getName();
				  continue;
			  }
			  htmlSelectAccName += "<option>" + Global.gProfile.getBankAcc(i).getName();
		  }
		  
		  if(removeBankAccSuccess){
		      out.println(docType +
		                "<html>\n" +
		                "<head><title>" + title + "</title></head>\n" +
		                "<body bgcolor=\"#f0f0f0\">\n" +
		                "<h1 align=\"center\">" + title + "</h1>\n" +
		                "<h1 align=\"center\">" + "Account: " + delName + " Deleted!" + "</h1>\n" +
		                "<ul>\n" +
		                
		                "<li><form action=\"Transfer\" method=\"GET\">" +
		                "From: Account Name <Select name=\"fromName\">" +
		                htmlSelectAccName + "</select><br>" +
		                " Money Amount <input type=\"text\" name=\"fromAmount\"><br>" + 
		                "To: Account Name <Select name=\"toName\"><br>" +
		                htmlSelectAccName + "</select><br>" +
		                "<input type=\"submit\" value=\"Transfer Fund\" />"
		                + "</form></li><br>" +
		                
		                
						"<li><form action=\"AddAccount\" method=\"GET\">" +
						"Account Name <input type=\"text\" name=\"accName\"><br>" +
						"Account Type: <Select name=\"accType\">" +
						"<option selected>CHECKING<option>SAVING<option>CD</select><br>" + 
						"<input type=\"submit\" value=\"Add Account\" />"
						+ "</form></li><br>" +
						
						"<li><form action=\"DeleteAccount\" method=\"GET\">" +
						"Account Name <Select name=\"delName\"><br>" +
						htmlSelectAccName + "</select><br>" +
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
			  out.println(docType +
		                "<html>\n" +
		                "<head><title>" + title + "</title></head>\n" +
		                "<body bgcolor=\"#f0f0f0\">\n" +
		                "<h1 align=\"center\">" + title + "</h1>\n" +
		                "<h1 align=\"center\">" + "Account: " + delName + " Balance Not Empty -- Delete Failed" + "</h1>\n" +
		                "<ul>\n" +
		                
		                "<li><form action=\"Transfer\" method=\"GET\">" +
		                "From: Account Name <Select name=\"fromName\">" +
		                htmlSelectAccName + "</select><br>" +
		                " Money Amount <input type=\"text\" name=\"fromAmount\"><br>" + 
		                "To: Account Name <Select name=\"toName\"><br>" +
		                htmlSelectAccName + "</select><br>" +
		                "<input type=\"submit\" value=\"Transfer Fund\" />"
		                + "</form></li><br>" +
		                
		                
						"<li><form action=\"AddAccount\" method=\"GET\">" +
						"Account Name <input type=\"text\" name=\"accName\"><br>" +
						"Account Type: <Select name=\"accType\">" +
						"<option selected>CHECKING<option>SAVING<option>CD</select><br>" + 
						"<input type=\"submit\" value=\"Add Account\" />"
						+ "</form></li><br>" +
						
						"<li><form action=\"DeleteAccount\" method=\"GET\">" +
						"Account Name <Select name=\"delName\"><br>" +
						htmlSelectAccName + "</select><br>" +
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
