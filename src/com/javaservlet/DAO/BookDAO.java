package com.javaservlet.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaservlet.Beans.Book;



public class BookDAO {
	
	public static Book login (Connection conn, String Username , String Password)
	{
		Book check = null;
		String sql = "select *  from User where Username=? and Password=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,Username);
			pst.setString(2,Password);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				
				check = new Book(rs.getString("Username"),rs.getString("Password"));
			}
		} catch (Exception e) {
			
		}
				
		return check;
	}
	
	public static List<Book> getAllBook(Connection conn)
	{	
		List<Book> listBook = new ArrayList<>();
		String sql = "select * from book";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs  = pst.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("bookid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				float price = rs.getFloat("price");
				Book book  =  new Book(id, title, author, price);
				listBook.add(book);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listBook;
		
	}
	// insert book
	public static boolean insertBook(Connection conn, Book book) {
		boolean rowInstered = false;
		String sql ="insert into book(title, author, price) values (?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,book.getTitle());
			pst.setString(2,book.getAuthor());
			pst.setFloat(3,book.getPrice());
			 if(pst.executeUpdate()> 0 )
				 rowInstered =  true;	
			 pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowInstered;
		
		
	}
	// update book
	public static boolean updateBook(Connection conn,Book book)
	{		
		String sql = "update book set title = ? , author = ?,price = ? where bookid = ?";

		
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,book.getTitle());
			pst.setString(2,book.getAuthor());
			pst.setFloat(3,book.getPrice());
			pst.setInt(4, book.getId());
			if(pst.executeUpdate() > 0)
				return  true;
			pst.close();
		} catch (Exception e) {
			
		}
		return false;
	}
	//  get id of book
	public static Book getIdBook(Connection conn,int id)
	{
		Book book  = null;
		String sql ="select * from book where bookid =?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				 String title = rs.getString("title");
				 String author = rs.getString("author");
				 float price = rs.getFloat("price");
				 book = new Book(id,title, author, price);
				 
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return book;
		
	}
	public static boolean deleteBook(Connection conn, Book book) {
		String sql = "delete from book where bookid=?";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1,book.getId());
			 if(pst.executeUpdate() > 0)
				return true;
			 pst.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	

}
