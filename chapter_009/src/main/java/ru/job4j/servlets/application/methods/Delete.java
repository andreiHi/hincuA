package ru.job4j.servlets.application.methods;

import ru.job4j.servlets.application.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Удаление пользователя.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Delete extends HttpServlet {
    private UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        this.userStore.deleteUser(login);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <title>Delete User</title>"
                + "</head>"
                + "<body>"
                + " <h2><a href='"
                + req.getContextPath()
                + "/users'>Назад.</a></h2>"
                + "<h3>Пользователь удалён.</h3>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <title>Delete</title>"
                + "</head>"
                + "<body>"
                + "<h1 align='center'>Удалить пользователя с логином: "
                + req.getParameter("login")
                + " ?</h1>"
                + "<table border='1' align='center'>"
                + "    <tr>"
                + "        <td>"
                + "            <form  action='"
                + req.getContextPath()
                + "/delete' method='post'>"
                + "                <input type='hidden' name='login' value="
                + req.getParameter("login")
                + ">"
                + "                <button type='submit'>Yes</button>"
                + "            </form>"
                + "        </td>"
                + "            <form  action='"
                + req.getContextPath()
                + "/users' >"
                + "                <button type='submit'>No</button>"
                + "            </form>"
                + "        </td>"
                + "    </tr>"
                + "</table>"
                + "</body>"
                + "</html>");
                writer.flush();
    }
}
