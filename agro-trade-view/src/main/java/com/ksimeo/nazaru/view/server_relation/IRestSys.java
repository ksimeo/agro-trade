package com.ksimeo.nazaru.view.server_relation;


import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.core.models.User;

import java.util.List;
import java.util.Map;

/**
 * Created by @Ksimeo on 04.02.2015.
 */
public interface IRestSys {

    boolean isExistUser(String login);
    User getRequeredUser(String userLogin);
    List getAllOrders();
    List getAllUsers();
    List getAllProducts();
    void delProduct(int id);
    String getProductsName(int id);
    Product getProductById(int id);
    void sendOrder(Order order);
    void delOrder(int id);
    void addProduct(Product product);
    void delUser(int id);
    void addUser(User user);
}