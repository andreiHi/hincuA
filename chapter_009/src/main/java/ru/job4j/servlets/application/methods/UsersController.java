package ru.job4j.servlets.application.methods;

import ru.job4j.servlets.application.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Начальное отображение.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UsersController extends HttpServlet {
    private  final UserStorage userStorage = UserStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        synchronized (session) {
            if (session == null || session.getAttribute("login") == null) {
                resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
            } else {
                req.setAttribute("users", userStorage.selectUsers());
                req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
        userStorage.close();
    }
}
