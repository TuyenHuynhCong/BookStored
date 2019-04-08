<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Management</title>
<style>
.right {
    text-align: right;
    border: 3px solid black;
}
</style>
</head>
<body>

	<center>
		<h1>Book Management</h1>
		<div class="right">
				<a href="login.jsp">Logout</a>
				</div>
		<h2>
			<a href="/BookStored/new">Add New Book</a> &nbsp; &nbsp; &nbsp; 
			<a href="/BookStored/list">List
				All Book</a>
				
		</h2>
		
	</center>

	<div align="center">
		<table border = "1" cellpadding="5">
			<caption>
				<h2>List of Books</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${listBook}" var="book">
				<tr>
					<td><c:out value="${book.id}"></c:out></td>
					<td><c:out value="${book.title}"></c:out></td>
					<td><c:out value="${book.author}"></c:out></td>
					<td><c:out value="${book.price}"></c:out></td>
					<td>
					<a href="/BookStored/edit?id=<c:out value="${book.id}"/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/BookStored/delete?id=<c:out value="${book.id}"/>">Delete</a> 
                     </td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>