<%@ include file="../include.jsp"%>
<html>
<head>
	<title>Book Details</title>
	<link rel="stylesheet" type="text/css" href="../resources/nav.css" />
</head>
<body>
<%@ include file="../libraryHomeNavbar.jsp"%>
<center>
<h1><fmt:message key="addBookForm.title" /></h1>
<form:form action="./processaddbookform" method="POST" commandName="book">
	<table>
  	<tr>
  	<td><fmt:message key="titleLabel" /></td>
        <td><form:input path="title" />
            <form:errors path="title" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="publisherLabel" /></td>
        <td><form:input path="publisher" />
            <form:errors path="publisher" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="authorLabel" /></td>
        <td><form:input path="author" />
            <form:errors path="author" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="priceLabel" /></td>
        <td><form:input path="price" />
            <form:errors path="price" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="numOfBooksLabel" /></td>
        <td><form:input path="numOfBooks" />
            <form:errors path="numOfBooks" cssClass="error"/>
        </td>
    </tr>
    <tr>
    <td><input type="submit" value="<fmt:message key="submitBtn" />"> </td>
    </tr>
    </table>
</form:form>
</center>
</body>
</html>