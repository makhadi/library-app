<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<html>
<head>
<title>Login Details</title>
<link rel="stylesheet" type="text/css" href="./resources/nav.css" />
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
<p>${timeoutmessage}</p>
<h1>Please enter the details below: </h1>

<form:form action="./libraryhome" method="POST" commandName="login">
  <table>
    <tr>
  	<td><fmt:message key="emailidLabel" /></td>
        <td><form:input path="emailId" />
            <form:errors path="emailId" cssClass="error"/>
        </td>
    </tr>
    <tr>
    <tr>
  	<td><fmt:message key="passwordLabel" /></td>
        <td><form:input type="password" path="password" />
            <form:errors path="password" cssClass="error"/>
        </td>
    </tr>
    <tr>
    <tr>
  	<td><p Class="error">${incorrectlogin}</p></td>
    </tr>
    <tr>
    <td><input type="submit" value="<fmt:message key="submitBtn" />"> </td>
    </tr>
    </table>
  </form:form>
</center>
</body>
</html>