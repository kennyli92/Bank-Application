package com.bankapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateProfile
 */
@WebServlet("/CreateProfile")
public class CreateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int uid = Global.gProfiles.size() + 1;
		String profileName = request.getParameter("newUsername");
		Global.gProfiles.add(new Profile(profileName, uid));
		
		//Check if cookie exist
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			//Add cookie here
		    Cookie sessionName = new Cookie("username", profileName);
		    sessionName.setMaxAge(60*15);
		    response.addCookie(sessionName);
		}else{
			for(int i = 0; i < cookies.length; i++){
				if(cookies[i].getName().equals("username") && !cookies[i].getValue().equals(profileName)){
					response.sendRedirect("cookieExistFailLogin.html");
					return;
				}
			}
		}
			
		Global.gUsername = profileName;
		
		if(profileName.equals("")){
			response.sendRedirect("failLogin.html");
		}else{
			String htmlSelectAccName = null;
			for(int i = 0; i < Global.gProfile.getBankAccNum(); i++){
				  if(i == 0){
					  htmlSelectAccName = "<option selected>" + Global.gProfile.getBankAcc(i).getName();
					  continue;
				  }
				  htmlSelectAccName += "<option>" + Global.gProfile.getBankAcc(i).getName();
			}
			
			response.setContentType("text/html");
			String docType = "<!DOCTYPE html>\n";
			String title = "Welcome " + Global.gUsername + "!";
			PrintWriter out = response.getWriter();
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
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
