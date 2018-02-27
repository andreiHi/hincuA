package ru.job4j.shop.controller;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.shop.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 26.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Authorization extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Authorization.class);
    private DBService service = DBService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        if ("yes".equals(remember)) {
            Cookie cookLogin = new Cookie("login", login);
            Cookie cookPass = new Cookie("password", password);
            cookLogin.setMaxAge(31 * 24 * 60 * 60);
            cookPass.setMaxAge(31 * 24 * 60 * 60);
            resp.addCookie(cookPass);
            resp.addCookie(cookLogin);
        }
        boolean present = service.checkUser(login, password);
        if (present) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("password", password);
        }
        String result = new Gson().toJson(present);
        System.out.println(result);
        pw.append(result);
        pw.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
