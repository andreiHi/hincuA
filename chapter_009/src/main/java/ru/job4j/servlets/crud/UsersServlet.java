package ru.job4j.servlets.crud;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersServlet.class);
    private final UserStore userStore = UserStore.getIstance();
    private User user;
    /**
     * обновление данных пользователя.
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException ex.
     * @throws IOException ex.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Удаление пользователя.
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException ex.
     * @throws IOException ex.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Получение пользователя по имени.
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException ex.
     * @throws IOException ex.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(user.toString());
        printWriter.flush();
    }

    /**
     * Добавление нового пользователя.
     * @param req запрос к серверу.
     * @param resp ответ сервера.
     * @throws ServletException еx.
     * @throws IOException еx.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.user = new User();
        this.user.setLogin(req.getParameter("login"));
        this.user.setName(req.getParameter("name"));
        this.user.setEmail(req.getParameter("email"));
        this.user.setCreateDate(Calendar.getInstance());
        doGet(req, resp);
    }
}
