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
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		//remove cookie
		Cookie tempCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++){
				if(cookies[i].getName().equals("username")){
					tempCookie = cookies[i];
					tempCookie.setMaxAge(0);
					response.addCookie(tempCookie);
				}
			}
		}
		
		
		//Remove global Username and Profile
		Global.gUsername = null;
		Global.gProfile = null;
		
		//write to profiles.txt and history.txt
		String tempAccName, tempAccType, tempBal, profileline = "";
		Global.gProfileObjects.seek(0);
		for(int i = 0; i < Global.gProfiles.size(); i++){
			profileline += Global.gProfiles.get(i).getUsername() + "," + Integer.toString(Global.gProfiles.get(i).getUid());
			for(int j = 0; j < Global.gProfiles.get(i).getBankAccNum(); j++){
				tempAccName = Global.gProfiles.get(i).getBankAcc(j).getName();
				tempAccType = Global.gProfiles.get(i).getBankAcc(j).getType().toString();
				tempBal = Double.toString(Global.gProfiles.get(i).getBankAcc(j).getBalance());
				profileline += "," + tempAccName + "," + tempAccType + "," + tempBal;
			}
			Global.gProfileObjects.writeBytes(new String(profileline + "\n"));
			profileline = "";
		}
		
		Global.gHistoryObjects.seek(0);
		for(int i = 0; i < Global.gHistory.getHistorySize(); i++){
			Global.gHistoryObjects.writeBytes(new String(Global.gHistory.getUserLine(i) + "," + Global.gHistory.getHistoryLine(i) + "\n"));
		}
		
		//close Profiles.txt and history.txt
		Global.gProfileObjects.close();
		Global.gHistoryObjects.close();
		
		Global.gProfileObjects = null;
		Global.gHistoryObjects = null;
		
		
		PrintWriter out = response.getWriter();
		String title = "Logout Successful.";
	    String docType = "<!DOCTYPE html>\n";
	    out.println(docType +
	        "<html>\n" +
	        "<head><title>" + title + "</title></head>\n" +
	        "<body bgcolor=\"#f0f0f0\">\n" +
	        "<h1 align=\"center\">" + title + "</h1>\n" +
	        "<form action=\"index.html\" method=\"GET\">" +
			"<input type=\"submit\" value=\"Back to login page.\" />"
			+ "</form></body></html>"
	    );//end out.println
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
