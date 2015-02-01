<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<html>
<head>
	<title>Registration Details</title>
	<link rel="stylesheet" type="text/css" href="./resources/nav.css" />
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
<h1><fmt:message key="registrationForm.title" /></h1>
  <form:form action="./processregistrationform" method="POST" commandName="librarian">
  <table>
  	<tr>
  	<td><fmt:message key="lastnameLabel" /></td>
        <td><form:input path="lastName" />
            <form:errors path="lastName" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="firstnameLabel" /></td>
        <td><form:input path="firstName" />
            <form:errors path="firstName" cssClass="error"/>
        </td>
    </tr>
    <tr>
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
    <td><input type="submit" value="<fmt:message key="submitBtn" />"> </td>
    </tr>
    </table>
  </form:form>
 </center>
</body>
</html>
