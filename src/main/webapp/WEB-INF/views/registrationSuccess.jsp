<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<html>
<head>
<title>Registration Result</title>
<link rel="stylesheet" type="text/css" href="../resources/nav.css" />
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
<h2>Your information has been successfully saved:</h2>
<h3><font COLOR="#00008B">

</font>
</h3>
<a href="/libraryapp/login">Login</a>
</center>
</body>
</html>