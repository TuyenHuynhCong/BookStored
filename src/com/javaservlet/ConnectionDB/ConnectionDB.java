package com.javaservlet.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
	 private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
	            + "databaseName=BookStore;";
	           
	    private static String USER_NAME = "tuyen";
	    private static String PASSWORD = "1234";
	    public static Connection getConnectionDB() {
	    	Connection conn = null;
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
	            System.out.println("connect successfully!");
	        } catch (Exception ex) {
	            System.out.println("connect failure!");
	            ex.printStackTrace();
	        }
	        return conn;
	    }
		    public static void main(String[] args) {
				Connection conn;
				System.out.println(getConnectionDB());
			}
}
