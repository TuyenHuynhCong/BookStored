package com.javaservlet.Controller;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.State;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
	    String Username = request.getParameter("Username");
	    String Password = request.getParameter("Password");
	    try {
	    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	 String URL = "jdbc:sqlserver://localhost:1433;databaseName=BookStore;user=tuyen;password=1234";
	    	Connection conn=DriverManager.getConnection(URL);
	    	PreparedStatement pst = conn.prepareStatement("select * from User where Username='"+Username+"'and Password='"+Password+"'");
	    	ResultSet rs=pst.executeQuery(); 
	    	if(rs.next())
	    	{
	    	response.sendRedirect("book_form.jsp");
	    	}
	    	else
	    	{
	    		response.sendRedirect("Error.jsp");
	    		
	    	}
		} catch (Exception e) {
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
