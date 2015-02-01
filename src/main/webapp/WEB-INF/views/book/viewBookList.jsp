<%@ include file="../include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" %>
<html>
<head>
<title>Library</title>
<link rel="stylesheet" type="text/css" href="../resources/nav.css" />
</head>
<body>
<%@ include file="../libraryHomeNavbar.jsp"%>
<center>
<c:choose>
    <c:when test='${books == null || empty books}'>
         No books available
    </c:when>
    <c:otherwise>
<div id="tableview">
    <table>
    <div id="columnview">
		<tr>
  			<th>Title</th>
  			<th>Publisher</th> 
  			<th>Author</th>
  			<th>Price</th>
  			<th>Number of Books</th>
		</tr>
		<c:forEach items="${books}" var="curBook">
		<form action="viewissuebook" method="POST">
		<tr>
  			<td>${curBook.title}</td>
  			<td>${curBook.publisher}</td>
  			<td>${curBook.author}</td>
  			<td>${curBook.price}</td>
  			<td>${curBook.numOfBooks}</td>
  			<input type="text" name="issuebookid" value="${curBook.bookId}" hidden>
  			<td><input type="submit" name="issueBook" value="IssueBook"></td>
  			<td><input type="submit" name="editbook" value="Edit"></td>
  			<td><input type="submit" name="deletebook" value="Delete"></td>
		</tr>
		</form>
		</c:forEach>
	</table>
    </c:otherwise>
    </c:choose>
</div>
</center>
</body>
</html>