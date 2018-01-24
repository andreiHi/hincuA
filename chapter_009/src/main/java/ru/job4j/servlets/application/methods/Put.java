package ru.job4j.servlets.application.methods;

import ru.job4j.servlets.application.UserStore;
import ru.job4j.servlets.crud.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Обновление пользователя.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Put extends HttpServlet {
    private static UserStore userStore = UserStore.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("method", "update");
        String login = req.getParameter("newLogin");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String oldLogin = req.getParameter("oldLogin");
        if (login.isEmpty() || name.isEmpty() || email.isEmpty()) {
            req.setAttribute("state", "emptyEdit");
        } else {
            User user = new User(login, name, email);
            userStore.update(user, oldLogin);
            req.setAttribute("state", "successEdit");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/responsePage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        userStore.close();
    }
}
