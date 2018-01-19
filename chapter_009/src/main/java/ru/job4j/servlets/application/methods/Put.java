package ru.job4j.servlets.application.methods;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Обновление пользователя.
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Put extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Put.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        writer.append("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Update</title>" +
                " <h1 align=center>Обновление данных пользователя.</a></h1>" +
                "</head>" +
                "<body>" +
                "<h3><form action='' method='PUT' align = center>" +
                "Old  Login : <input type='text' name='oldlogin' value ='"+ req.getParameter("login")+"' disabled>"+
                "<br/>"+
                "New Login : <input type='text' name='login'>"+
                "<br/>"+
                "New Name  : <input type='text' name='name'>"+
                "<br/>"+
                "New Email : <input type='text' name='email'>"+
                "<br/>"+
                "<button type='submit'  formmethod=\"PUT\">Update</button>"+
                "</form></h3>" +

                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        writer.append("hello world");
        writer.flush();
    }
}
