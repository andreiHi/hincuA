<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page import="ru.job4j.servlets.application.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1 align="center">Хранилище пользователей</h1>
<h2 align="center"><a href="userForm.jsp" name="new">Добавить нового пользователя</a></h2>
<h3 align="center">Список пользователей</h3>


<table style="border: 1px solid black;" cellpadding="3" cellspacing="1" align="center" border="1" >
    <tr>
        <th>id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Email</th>
        <th>Date</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <% for (User user : UserStore.getInstance().selectUsers()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/forward" method="post">
            <input type="hidden" name="login" value="<%=user.getLogin()%>">
            <input type="hidden" name="name" value="<%=user.getName()%>">
            <input type="hidden" name="email" value="<%=user.getEmail()%>">
            <input type="submit" name="update" value="Update">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/forward" method="post">
            <input type="hidden" name="login" value="<%=user.getLogin()%>">
            <input type="submit" name="delete" value="Delete">
            </form>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
