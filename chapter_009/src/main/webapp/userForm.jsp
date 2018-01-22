<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserForm</title>
</head>
<body style="background: #8d8d8d">
<%!User user;%>
<%!String login = "";%>
<%!String name = "";%>
<%!String email = "";%>
<%--<% User user = (User) request.getAttribute("user");%>--%>
<%if ((user = (User) request.getAttribute("user"))==null){
%>
<h1 align="center">Добавление нового пользователя.</h1>
<%} else {
    login = user.getLogin();
    name=user.getName();
    email=user.getEmail();
%>

<h1 align="center">Обновление пользователя.</h1>
<%}%>

<form action="" method="post">

<table style="border: 1px solid black;" cellpadding="3" cellspacing="1" align="center" border="1">
    <tr>
        <th>Login</th>
        <td><input type="text" name="newLogin" value="<%=login%>"
                   <%--<%if (user!=null)%>value="<%${user.getName()}%>"--%>
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
        <input type="submit" value="Save"<%user=null;%>>
        </td>
    </tr>
</table>
</form>
</body>
</html>
