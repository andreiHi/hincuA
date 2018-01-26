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
 * Добавление нового пользователя.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Post extends HttpServlet {
    private  final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("newLogin");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        req.setAttribute("method", "add");
        if (login.isEmpty() || name.isEmpty() || email.isEmpty()) {
            req.setAttribute("state", "empty");
        } else {
            User user = this.userStore.getUser(login);
            if (user != null) {
                req.setAttribute("state", "exist");
            } else {
                user  = new User(login, name, email);
                this.userStore.addNewUser(user);
                req.setAttribute("state", "success");
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/responsePage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        userStore.close();
    }
}
