<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserForm</title>
</head>
<body>
<%!String login = "";%>
<%!String name = "";%>
<%!String email = "";%>
<%!String forward = "";%>
<%System.out.println(request.getParameter("update"));%>
<%if (request.getParameter("update") != null){
    login=request.getParameter("login");
    name=request.getParameter("name");
    email=request.getParameter("email");
    forward = request.getContextPath()+"/edit";
%>
<h1 align="center">Обновление данных пользователя.</h1>

<%} else {
    forward = request.getContextPath()+"/new";
%>
<h1 align="center">Добавление нового пользователя.</h1>

<%}%>

<form action="<%=forward%>" method="post">

<table style="border: 1px solid black;" cellpadding="3" cellspacing="1" align="center" border="1">
    <tr>
        <th>Login</th>
        <td><input type="text" name="newLogin" value="<%=login%>"
                   size="45"/></td>
    </tr>
    <tr>
        <th>Name</th>
        <td><input type="text" name="name" size="45" value="<%=name%>"/></td>
    </tr>
    <tr>
        <th>Email</th>
        <td><input type="email" name="email" size="45" value="<%=email%>"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="hidden" name="oldLogin" value="<%=request.getParameter("login")%>">
        <input type="submit" value="Save">
        </td>
    </tr>
</table>
</form>
</body>
<%
login = "";
name = "";
email ="";
%>
</html>
