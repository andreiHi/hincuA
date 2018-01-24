package ru.job4j.servlets.application.methods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ForwardServlet extends DispatcherServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("update") != null) {
            super.forward("/userForm.jsp", req, resp);
        }
        if (req.getParameter("delete") != null) {
            req.setAttribute("method", "delete");
            super.forward("/responsePage.jsp", req, resp);
        }
    }
}
