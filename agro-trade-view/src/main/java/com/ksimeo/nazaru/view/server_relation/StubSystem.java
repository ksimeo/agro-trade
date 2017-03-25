//package com.ksimeo.nazaru.view.server_relation;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.core.models.Region;
import com.ksimeo.nazaru.core.models.User;

import java.util.*;

/**
 * Created by @Ksimeo on 09.02.2015.
 */
//public class StubSystem implements IRestSys {
//
//    private Map<String, User> users = new HashMap<String, User>();
//    private List<Order> orders = new ArrayList<>();
//
//    public StubSystem() {
//        User user1 = new User("Nazar", "Samarchuk", true);
//        User user2 = new User("Maksym", "Ksimeo", true);
//        User user3 = new User("Vasya", "#12345", false);
//        users.put("Nazar", user1);
//        users.put("Maksym", user2);
//        users.put("Vasya", user3);
//    }
//
//    @Override
//    public boolean isExistUser(String login) {
//        boolean isExist = users.containsKey(login);
//        if(isExist) return true;
//        else return false;
//    }
//
//    @Override
//    public User getRequeredUser(String userLogin) {
//       return users.get(userLogin);
//    }
//
//    @Override
//    public List getAllOrders() {
//        List<Order> orders = new LinkedList<>();
//        Order order1 = new Order(1, "Вася", "jwg@mail.com", "+380973234567", "Днепропетровская обл.", 2, 43);
//        Order order2 = new Order(2, "Коля", "if1@inbox.com", "+380637657889", "Запорожская обл.", 1, 54);
//        Order order3 = new Order(3, "Петя", "tyuir@hotmail.com", "+380952238990", "Одесская обл.", 3, 13);
//        Order order4 = new Order(4, "Серега", "kkrty@gmail.com", "+380997750544", "Черкасская обл.", 1, 7);
//        Order order5 = new Order(5, "Михаил", "erwer@yahoo.com", "+380500888990", "Харьковская обл.", 2, 17);
//        orders.add(order1);
//        orders.add(order2);
//        orders.add(order3);
//        orders.add(order4);
//        orders.add(order5);
//        return orders;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        List<User> usrs = new ArrayList<>();
//        User user1 = new User(1, "Nazar", "Samarchuk", true);
//        User user2 = new User(2, "Maksym", "Ksimeo", true);
//        User user3 = new User(3, "Vasya", "#12345", false);
//        usrs.add(user1);
//        usrs.add(user2);
//        usrs.add(user3);
//        return usrs;
//    }
//
//    @Override
//    public List<Product> getAllProducts() {
//        List<Product> products = new ArrayList<>();
//        Product product1 = new Product(1, "Тип 1", 2.95F);
//        Product product2 = new Product(2, "Тип 2", 3.19F);
//        Product product3 = new Product(3, "Тип 3", 2.14F);
//        products.add(product1);
//        products.add(product2);
//        products.add(product3);
//        return products;
//    }
//
//    @Override
//    public List getProducts() {
//
//        List<Product> products = new ArrayList<>();
//        products.add(new Product(1, "Биогумат тип 1", 20.5f));
//        products.add(new Product(2, "Биогумат тип 2", 19.99f));
//        products.add(new Product(3, "Биогумат тип 3", 14.12f));
//        return products;
//    }
//
//    @Override
//    public void sendOrder(Order order) {
//        orders.add(order);
//        System.out.println(order);
//    }
//}