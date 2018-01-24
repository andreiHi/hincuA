package ru.job4j.servlets.application.methods;

import ru.job4j.servlets.application.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        req.setAttribute("method", "deleteSuccess");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/responsePage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        userStore.close();
    }
}
