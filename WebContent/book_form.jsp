<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1> BOOK MANAGEMENT</h1>
		<h2>
			<a href="/BookStored/new">Add New Book</a> &nbsp;&nbsp;&nbsp;
			<a href="/BookStored/list">List All Book</a>
			</h2>
	</center>
	<div align="center">
	<c:if test="${book != null }">
		<form action="update" method ="post">
	</c:if>
	<c:if test="${book == null }">
		<form action="insert" method ="post">
	</c:if>
	<table border="1" cellpadding="5">
		<caption>
		
		<c:if test="${book != null }">
		<h2> Edit Book</h2>
	</c:if>
		<c:if test ="${book == null}">
		<h2> Add New Book</h2>
		</c:if>
	
		</caption>
		<c:if test="${book!=null}">
		<input type="hidden" name = "id"  value ="<c:out  value="${book.id }"/>"/>
		
		</c:if>
		<tr> 
			<th>Title :</th>
			<td><input type ="text" name ="title" size="50" value ="<c:out  value="${book.title}"/>"/></td>
		</tr>
		<tr> 
			<th>Author :</th>
			<td><input type ="text" name ="author"  size="50" value= "<c:out  value="${book.author}"/>" /></td>
		</tr>
		<tr> 
			<th>Price :</th>
			<td><input type ="text" name ="price"  size="50" value= "<c:out  value="${book.price}"/>"/></td>
		</tr>
		
		
		<tr> 
			
			<td colspan="2" align="center">
			<input type ="submit" value="Save"/>
			</td>
		</tr>
	</table>
	</form>
			
	</div>
</body>
</html>