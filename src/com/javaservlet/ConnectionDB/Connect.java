package com.javaservlet.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {
	
	private static String jdbcURL;
	private static String jdbcUsername;
	private static String jdbcPassword;
	private  static Connection jdbcConnection;
	
	
	public Connect(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	protected static void connect() throws SQLException{
		if(jdbcConnection==null|| jdbcConnection.isClosed())
		{
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
			} catch (Exception e) {
				throw new SQLException(e);
			}
			jdbcConnection=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			if(jdbcConnection!=null) System.out.println("thanh cong");
			else System.out.println("that bai");
			
		}
	}
	public static ResultSet checkLogin(String User,String Password) throws SQLException{
		ResultSet rs=null;
    	PreparedStatement pst=null;
    	connect();
    	String sql="SELECT * FROM User WHERE Username=? AND  Password=?";
        try{
        	pst=jdbcConnection.prepareStatement(sql);
            pst.setString(1, User);
            pst.setString(2, Password);
            return rs=pst.executeQuery();
       }catch(Exception e){
           return rs=null;
       }
	}
	 protected void disconnect() throws SQLException {
	        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
	            jdbcConnection.close();
	        }
	    }
}
