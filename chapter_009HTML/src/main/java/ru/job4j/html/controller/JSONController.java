package ru.job4j.html.controller;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.html.model.Address;
import ru.job4j.html.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class JSONController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(JSONController.class);
    private static DBConnection dbConnection = DBConnection.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ansver = req.getParameter("select");
        List<Address> list = new ArrayList<>();
        if (ansver.equals("country")) {
            list.addAll(dbConnection.getAllCountries());
        }
        if (ansver.equals("towns")) {
            list.addAll(dbConnection.getAllTowns());
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        String json = new Gson().toJson(list);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        pw.append(json);
        pw.flush();
    }

    @Override
    public void destroy() {
        super.destroy();
        dbConnection.close();
    }
}
