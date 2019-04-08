package com.javaservlet.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaservlet.Beans.Book;

import com.javaservlet.ConnectionDB.ConnectionDB;
import com.javaservlet.DAO.BookDAO;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = ConnectionDB.getConnectionDB();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print(ConnectionDB.getConnectionDB());

		String action = request.getServletPath();
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertBook(request, response);
			break;
		case "/delete":
			deleteBook(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateBook(request, response);
			break;
		case "/logout":
			logout(request,response);
			break;
		case "/list":
			
			listBook(request, response);
			
			break;
		
		default:
		login(request,response);
		break;
		}

	}

	

	
private void login(HttpServletRequest request, HttpServletResponse response)  throws ServletException,IOException{
	Connection conn = ConnectionDB.getConnectionDB();
	String user = request.getParameter("Username");
	
	String pass = request.getParameter("Password");
	if(BookDAO.login(conn, user, pass)==null)
	{	
		
		//listBook(request, response);
		response.sendRedirect("book_list.jsp");
		System.out.println("login");
	}else
	{
		
	response.sendRedirect("Error.jsp");
	
			}
		
	}

private void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Book> listBook = BookDAO.getAllBook(conn);
			request.setAttribute("listBook", listBook);
			RequestDispatcher rd = request.getRequestDispatcher("book_list.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) {
		//Connection conn = ConnectionDB.getConnectionDB(); 
		
		
		try {
			Connection conn = ConnectionDB.getConnectionDB(); 
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			float price =Float.parseFloat(request.getParameter("price"));
			Book book = new Book(id, title, author, price);
			BookDAO.updateBook(conn, book);
			RequestDispatcher rd =request.getRequestDispatcher("list");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			//Connection conn = ConnectionDB.getConnectionDB();
			int id = Integer.parseInt(request.getParameter("id"));
			//Book exitsBook = BookDAO.getIdBook(conn, id);
			Book exitstBook = BookDAO.getIdBook(conn, id);
			request.setAttribute("book", exitstBook);
			RequestDispatcher rd = request.getRequestDispatcher("book_form.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
		
	
		try {
			Connection conn= ConnectionDB.getConnectionDB();
			int id = Integer.parseInt(request.getParameter("id"));
			Book book = new Book(id);
			BookDAO.deleteBook(conn, book);
			RequestDispatcher rd= request.getRequestDispatcher("list");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) {
	
		try {
		Connection conn = ConnectionDB.getConnectionDB();
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			float price = Float.parseFloat(request.getParameter("price"));
			Book book = new Book(title, author, price);
			BookDAO.insertBook(conn, book);
			RequestDispatcher rd = request.getRequestDispatcher("list");
			rd.forward(request, response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher("book_form.jsp");
			rd.forward(request,response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
