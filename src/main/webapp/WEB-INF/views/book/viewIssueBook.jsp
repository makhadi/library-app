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
<div>
<label>Title: ${issueBook.title}</label><br>
<label>Publisher: ${issueBook.publisher}</label><br>
<label>Author: ${issueBook.author}</label><br>
<label>Price: ${issueBook.price}</label><br>
<label>Number of Books: ${issueBook.numOfBooks}</label>
<br><br>
<form action="./issuebook" method="POST">
<input type="TEXT" name="bookId" value="${issueBook.bookId}" hidden>
<BR>
<select name="issueStudent">
<c:forEach items="${studentList}" var="curStudent">
	<c:set var="name" value="${curStudent.firstName} ${curStudent.lastName}" />
	<option value="${curStudent.studentId}">${name}</option>
</c:forEach>
</select>
<input type="submit" name="Issue" value="Issue">
</form>
</div>
</center>
</body>
</html>