<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!private String namePage;%>
<%!private String stateOfExequ;%>
<%!private String repeat;%>
<%!private boolean delete;%>

<html>
<head>
    <title>Answer</title>
</head>
<body>
<%
    String method = (String) request.getAttribute("method");
    if ("add".equals(method)) {
        namePage = "Добавление нового пользователя.";
        if (request.getAttribute("state").equals("empty")) {
            stateOfExequ = "Не все данные были заполнены.";
            repeat = "Повторить добавление";
        } else if (request.getAttribute("state").equals("success")) {
            stateOfExequ = "Пользователь был успешно добавлен.";
        } else if (request.getAttribute("state").equals("exist")) {
            stateOfExequ = "Пользователь с данным логином уже существует";
            repeat = "Повторить добавление.";
        }
    } else if ("update".equals(method)) {
        namePage = "Обновление данных пользователя";
        if ("successEdit".equals(request.getAttribute("state"))) {
            stateOfExequ = "Данные пользователя успешно обновлены";
        } else if ("emptyEdit".equals(request.getAttribute("state"))) {
            stateOfExequ = "Не все данные были заполнены.";
            repeat = "Повторить изменение данных.";
        }
    } else if ("delete".equals(method)) {
        namePage = "Удаление пользователя";
        stateOfExequ = String.format("Удалить пользователя с логином: %s ?", request.getParameter("login"));
        delete = true;
    } else if ("deleteSuccess".equals(method)) {
        namePage = "Удаление пользователя";
        stateOfExequ = "Пользователь был успешно удален.";
    }
%>


<h1 align="center"><%=namePage%></h1>
<h2 align="center"><%=stateOfExequ%></h2>
<%if (!delete) {%>
<h2 align="center">
    <a href="<%=request.getContextPath()%>/">На главную.</a>
    <%if (repeat != null) {%>
    &nbsp;&nbsp;&nbsp;
    <%--<a href="<%=request.getContextPath()%>/WEB-INF/views/UserForm.jsp"><%=repeat%></a>--%>
    <form action="<%=request.getContextPath()%>/forward" method="post">
        <input type="submit" name="update" value="<%=repeat%>"/>
    </form>
    <%}%>
</h2>
<%} else {  %>
<table align="center">
    <tr >
        <td>
            <form  action="<%=request.getContextPath()%>/delete" method="post">
                <input type="hidden" name="login" value="<%=request.getParameter("login")%>">
                <input type="submit"  value="Да">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/">
                <input type="submit" value="Нет">
            </form>
        </td>
    </tr>
</table>
<%}%>
</body>
<%repeat = null;%>
<%delete = false;%>
<%stateOfExequ = null;%>
</html>
