package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.actions.Process;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Controller extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(String.format("%s/src/html/add.html", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Process process = new Process();
        process.findAction(req);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        pw.append(process.getResponse(req));
        pw.flush();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
