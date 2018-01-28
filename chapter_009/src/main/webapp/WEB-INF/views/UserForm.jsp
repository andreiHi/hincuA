<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserForm</title>
</head>
<body>
<h1 align="center">${title}</h1>
<form action="${path}" method="post">

<table style="border: 1px solid black;" cellpadding="3" cellspacing="1" align="center" border="1">
    <tr>
        <th>Login</th>
        <td><input type="text" name="newLogin" value="<c:out value="${user.login}"/>"
                   size="45"/></td>
    </tr>
    <tr>
        <th>Name</th>
        <td><input type="text" name="name" size="45" value="<c:out value="${user.name}"/>"/></td>
    </tr>
    <tr>
        <th>Email</th>
        <td><input type="email" name="email" size="45" value="<c:out value="${user.email}"/>"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="hidden" name="oldLogin" value="<c:out value="${user.login}"/>">
        <input type="submit" value="Save">
        </td>
    </tr>
</table>
</form>
<h2><a href="${pageContext.servletContext.contextPath}">На главную.</a></h2>
</body>
</html>
