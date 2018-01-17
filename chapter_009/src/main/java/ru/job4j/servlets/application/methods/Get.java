package ru.job4j.servlets.application.methods;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.application.UserStore;
import ru.job4j.servlets.crud.SQLquery;
import ru.job4j.servlets.crud.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Начальное отображение.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Get extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Get.class);
    private  final UserStore userStore = UserStore.getInstance();
    private List<User> list =  new CopyOnWriteArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        selectUsers();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder("<table>");
        for (User user : list) {
            sb.append("<tr><td>");
            sb.append("<tr><td>" + user);
            sb.append("<form action='"
                    + req.getContextPath()
                    + "/edit' method='put'>"
                    + "<input type='hidden' name='login' value="
                    + user.getLogin()
                    + " />"
                    + "<button type='submit'>Update</button>"
                    + "</form>"
                    + "&nbsp;"

            );
            sb.append("<form action='"
                    + req.getContextPath()
                    + "/delete'>"
                    + "<button type='submit'>Delete</button>"
                    + "</form>");
            sb.append("</td></tr>");
        }
        sb.append("</table>");
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <title>User Storage</title>"
                + "</head>"
                + "<body>"
                + "<h1 align=center>Хранилище пользователей</h1>"
                + "<br/>"
                + " <h2><a href='new'>Добавить нового пользователя.</a></h2>"
                + "<br/>"
                + "<h3 align=left>Список пользователей</h3>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
    }
    public void selectUsers() {
        list.clear();
        try (Connection connection = userStore.getDataSource().getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQLquery.SELECT_ALL_USERS)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setName(rs.getString("name"));
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(rs.getTimestamp("date").getTime());
                        user.setCreateDate(calendar);
                        user.setLogin(rs.getString("login"));
                        user.setEmail(rs.getString("email"));
                        list.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
