package ru.job4j.servlets.application.methods;

import ru.job4j.servlets.application.UserStorage;
import ru.job4j.servlets.crud.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * /edit
 * Обновление пользователя.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Put extends HttpServlet {
    private static UserStorage userStorage = UserStorage.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("method", "update");
        String login = req.getParameter("newLogin");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String oldLogin = req.getParameter("oldLogin");
        User user;
        if (login.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("title", "Заполните все данные.");
            user = new User(oldLogin, name, email, password);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/UserForm.jsp").forward(req, resp);
        } else {
            user = new User(login, name, email, password);
            userStorage.update(user, oldLogin);
            req.setAttribute("title", "Данные пользователя успешно обновлены.");
            req.getRequestDispatcher("/WEB-INF/views/responsePage.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        userStorage.close();
    }
}
