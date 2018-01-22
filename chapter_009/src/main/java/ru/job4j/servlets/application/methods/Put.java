package ru.job4j.servlets.application.methods;

import ru.job4j.servlets.application.UserStore;
import ru.job4j.servlets.crud.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Обновление пользователя.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Put extends HttpServlet {
    private static UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("userForm.jsp");
        User user = userStore.getUser(req.getParameter("login"));
        System.out.println(user);
        req.setAttribute("user", user);
        dispatcher.forward(req, resp);
//        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
//        writer.append("<!DOCTYPE html>"
//                + "<html lang='en'>"
//                + "<head>"
//                + "    <meta charset='UTF-8'>"
//                + "    <title>Update</title>"
//                + " <h1 align=center>Обновление данных пользователя.</a></h1>"
//                + "</head>"
//                + "<body>"
//                + "<h3><form action='"
//                + req.getContextPath()
//                + "/edit' method='post' align = center>"
//                + "Old  Login : <input type='text' name='oldlogin' value ='"
//                + req.getParameter("login")
//                + "'>"
//                + "<br/>"
//                + "New Login : <input type='text' name='login'>"
//                + "<br/>"
//                + "New Name  : <input type='text' name='name'>"
//                + "<br/>"
//                + "New Email : <input type='text' name='email'>"
//                + "<br/>"
//                + "<button type='submit'>Update</button>"
//                + "</form></h3>"
//                + "</body>"
//                + "</html>");
//        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        String login = req.getParameter("newLogin");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String oldLogin = req.getParameter("login");
        if (login.isEmpty() || name.isEmpty() || email.isEmpty()) {
            doGet(req, resp);
        } else {
            User user = new User(login, name, email);
            userStore.update(user, oldLogin);
            writer.append("<!DOCTYPE html>"
                    + "<html lang='en'>"
                    + "<head>"
                    + "    <meta charset='UTF-8'>"
                    + "    <title>Update User</title>"
                    + "</head>"
                    + "<body>"
                    + " <h2><a href='"
                    + req.getContextPath()
                    + "/users'>Назад.</a></h2>"
                    + "<br/>"
                    + "<h3>Данные пользователя обновлены.</h3>"
                    + "</body>"
                    + "</html>");
            writer.flush();
        }

    }
}
