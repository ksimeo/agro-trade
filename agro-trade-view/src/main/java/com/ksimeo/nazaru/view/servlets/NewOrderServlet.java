package com.ksimeo.nazaru.view.servlets;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.view.server_relation.IRestSys;
import com.ksimeo.nazaru.view.server_relation.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by @Ksimeo on 11.03.2015.
 */
@WebServlet(urlPatterns = "doorder")
public class NewOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String telefon = req.getParameter("tel");
            String name = req.getParameter("name");
            String email = req.getParameter("address");
            String region = req.getParameter("region");
            String product = req.getParameter("product");
            String number = req.getParameter("number");

            if (telefon != null && name != null && number != null) {
                IRestSys restSys = new RestSystem();
                int numb = Integer.parseInt(number);
                Order order = new Order(telefon, name, email, region, product, numb);
                restSys.sendOrder(order);
                resp.sendRedirect("/orderpages/ordered.html");
            } else {
                req.setAttribute("ErrorForm", "Вы не заполнили все обязательные поля!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}