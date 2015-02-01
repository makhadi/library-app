<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<html>
<head>
<title>Library</title>
<link rel="stylesheet" type="text/css" href="../resources/nav.css" />
</head>
<body>
<%@ include file="./libraryHomeNavbar.jsp"%>
<center>
<h1>
	Welcome  <font color="blue"/>${librarian.firstName} ${librarian.lastName}</font>
</h1>
<br>
<img src="${context}/resources/images/library1.jpg" width="400" height="400">
</center>
</body>
</html>