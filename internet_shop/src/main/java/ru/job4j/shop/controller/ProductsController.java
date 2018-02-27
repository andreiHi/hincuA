package ru.job4j.shop.controller;

import com.google.gson.Gson;
import ru.job4j.shop.model.Product;
import ru.job4j.shop.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * .
 * @author Hincu Andrei (andreih1981@gmail.com) by 17.02.18;
 * @version $Id$
 * @since 0.1
 */
public class ProductsController extends HttpServlet {
    private DBService service = DBService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String sort = req.getParameter("sort");
        Cookie[] cookies = req.getCookies();
        String login = "";
        String pass = "";
        for (int i = 0; i < cookies.length; i++) {
            if ("login".equals(cookies[i].getName())) {
                login = cookies[i].getValue();
            }
            if ("password".equals(cookies[i].getName())) {
                pass = cookies[i].getValue();
            }
        }
        System.out.println(login + " " + pass);
        List<Product> products = service.getAllProducts(sort);
        String json = new Gson().toJson(products);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        pw.append(json);
        pw.flush();
    }

    @Override
    public void destroy() {
        super.destroy();
        service.close();
    }
}
