package ru.job4j.shop.controller;

import com.google.gson.Gson;
import ru.job4j.shop.model.Product;
import ru.job4j.shop.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
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
        List<Product> products = service.getAllProducts(sort);
        String json = new Gson().toJson(products);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("admin") != null) {
            String action = req.getParameter("action");
            String id = req.getParameter("id");
            if ("delete".equals(action)) {
                service.deleteProduct(id);
            } else {
                String form = req.getParameter("form");
                String[]f = form.split("&");
                String name = f[0].split("=")[1];
                String price = f[1].split("=")[1];
                String amount = f[2].split("=")[1];
                String minidescription = f[3].split("=")[1].replaceAll("\\+", " ");
                String description = f[4].split("=")[1].replaceAll("\\+", " ");
                String result = "";
                if (name.isEmpty() || price.isEmpty() || amount.isEmpty() || minidescription.isEmpty() || description.isEmpty()) {
                    result = "Заполните все поля";
                } else {
                    Product product;
                    try {
                        int priceInt = Integer.parseInt(price);
                        int amountInt = Integer.parseInt(amount);
                        product = new Product(name, description, minidescription, priceInt, amountInt);
                        if ("add".equals(action)) {
                            service.addNewProduct(product);
                            result = "ok";
                        }
                        if ("update".equals(action)) {
                            service.updateProduct(product, id);
                            result = "ok";
                        }
                    } catch (NumberFormatException e) {
                        result = "Введите коректные данные";
                    }
                }
                String json = new Gson().toJson(result);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
                pw.append(json);
                pw.flush();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        service.close();
    }
}
