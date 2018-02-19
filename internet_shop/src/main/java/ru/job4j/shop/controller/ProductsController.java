package ru.job4j.shop.controller;

import com.google.gson.Gson;
import ru.job4j.shop.model.Product;
import ru.job4j.shop.service.DBService;

import javax.servlet.ServletException;
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
        String prod = req.getParameter("product");
        String sort = req.getParameter("sort");
        System.out.println(sort);
        System.out.println(prod);
        if (prod != null) {
            List<Product> products = service.getAllProducts();
            String json = new Gson().toJson(products);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
            pw.append(json);
            pw.flush();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        service.close();
    }
}
