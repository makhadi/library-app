<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<html>
<head>
	<title>NPU Library</title>
	<link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
<h1>Welcome to NPU Library</h1>
<p>${logoutmessage}</p>
<img src="${context}/resources/images/library.jpg" width="400" height="400">
<br>
<br>
<br>
</center>
</div>
</body>
</html>