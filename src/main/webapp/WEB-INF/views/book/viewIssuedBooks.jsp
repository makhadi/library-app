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
    <c:when test='${booksIssued == null || empty booksIssued}'>
         No books issued
    </c:when>
    <c:otherwise>
<div id="tableview">
    <table>
    <div id="columnview">
		<tr>
  			<th>Title</th>
  			<th>Student</th> 
  			<th>Issued Date</th>
  			<th>Due Date</th>
		</tr>
		<form action="viewissuebook" method="POST">
		<c:forEach items="${booksIssued}" var="curBook">
		<tr>
			<c:forEach items="${curBook}" var="entry">
  					<td>${entry.value}</td>
			</c:forEach>
		</tr>
			<br>
		</c:forEach>
		</form>
	</table>
    </c:otherwise>
    </c:choose>
</div>
</center>
</body>
</html>