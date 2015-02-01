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
<p>${bookissued}</p>
<p>${issuewhilebookissue}</p>
</center>
</body>
