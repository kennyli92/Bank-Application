package com.bankapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		Profile tempProfile;
		boolean userFoundFlag = false;
		response.setContentType("text/html");
		String docType = "<!DOCTYPE html>\n";
		String htmlSelectAccName = null;
		String title = null;
		boolean matchFlag = false;
	      PrintWriter out = response.getWriter();
	      String username = request.getParameter("username");
	     if(Global.gProfileObjects == null){
	    	 Global.gProfileObjects = new RandomAccessFile("profiles.txt", "rw");
	    	 Global.gProfileObjects.seek(0);
	     }
	     if(Global.gHistoryObjects == null){
	    	 Global.gHistoryObjects = new RandomAccessFile("history.txt", "rw");
	    	 Global.gHistoryObjects.seek(0);
	     }
	     
	     for(int i = 0; i < Global.gProfiles.size(); i++){
	    	  if(username.equals(Global.gProfiles.get(i).getUsername())){
	    		  userFoundFlag = true;
	    		  break;
	    	  }
	      }
	    
	    if(!userFoundFlag){
		    //Check if cookie exist
			Cookie[] cookies = request.getCookies();
			if(cookies == null){
				//Add cookie here
			    Cookie sessionName = new Cookie("username", username);
			    sessionName.setMaxAge(60*15);
			    response.addCookie(sessionName);
			}else{
				for(int i = 0; i < cookies.length; i++){
					if(cookies[i].getName().equals("username") && !cookies[i].getValue().equals(username)){
						response.sendRedirect("cookieExistFailLogin.html");
						return;
					}
				}
			}
	    }
	      
	      Global.gUsername = username;
	      //find profile of user and create global Profile object
	      for(int i = 0; i < Global.gProfiles.size(); i++){
	    	  if((tempProfile = Global.gProfiles.get(i)).getUsername().equals(Global.gUsername)){
	    		  Global.gProfile = tempProfile;
	    		  matchFlag = true;
	    		  break;
	    	  }
	      }
	      if(username.equals("")){
	    	  matchFlag = false;
	      }
	      
	      
	      if(matchFlag){
			  title = "Welcome " + Global.gUsername + "!";
			  for(int i = 0; i < Global.gProfile.getBankAccNum(); i++){
				  if(i == 0){
					  htmlSelectAccName = "<option selected>" + Global.gProfile.getBankAcc(i).getName();
					  continue;
				  }
				  htmlSelectAccName += "<option>" + Global.gProfile.getBankAcc(i).getName();
			  }
			  
			  
		      out.println(docType +
		                "<html>\n" +
		                "<head><title>" + title + "</title></head>\n" +
		                "<body bgcolor=\"#f0f0f0\">\n" +
		                "<h1 align=\"center\">" + title + "</h1>\n" +
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
	      }else if(userFoundFlag || !matchFlag){
	    	  response.sendRedirect("failLogin.html");
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
