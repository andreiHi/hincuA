package ru.job4j.servlets.application.methods;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.application.UserStore;
import ru.job4j.servlets.crud.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Начальное отображение.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Get extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Get.class);
    private  final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = new ArrayList<>();
        list.addAll(userStore.selectUsers());
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder("<table border='1' align=center>");
        sb.append("<tr><th>Login</th><th>Name</th><th>Email</th><th>Data</th><th>Update</th><th>Delete</th></tr>");
        for (User user : list) {
            sb.append("<tr>"
                    + " <td>"
                    + user.getLogin()
                    + "</td>"
                    + "<td>"
                    + user.getName()
                    + "</td>"
                    + "<td>"
                    + user.getEmail()
                    + "</td>"
                    + "<td>"
                    + new SimpleDateFormat("dd-MM-YYYY").format(user.getCreateDate().getTime())
                    + "</td>");
            sb.append("<td>"
                    + "<form action='"
                    + req.getContextPath()
                    + "/edit'>"
                    + "<input type='hidden' name='login' value="
                    + user.getLogin()
                    + " />"
                    + "<button type='submit'>Update</button>"
                    + "</form>"
                    +"</td>"
            );
            sb.append("<td>"
                    + "<form action='"
                    + req.getContextPath()
                    + "/delete'>"
                    + "<input type='hidden' name='login' value="
                    + user.getLogin()
                    + " />"
                    + "<button type='submit'>Delete</button>"
                    + "</form>");
            sb.append("</td></tr>");
        }
        sb.append("</table>");
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>User Storage</title>"
                + "</head>"
                + "<body>"
                + "<h1 align=center>Хранилище пользователей</h1>"
                + " <h2 align=center><a href='new' >Добавить нового пользователя.</a></h2>"
                + "<br/>"
                + "<h3 align=center>Список пользователей</h3>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
        list.clear();
    }
}
