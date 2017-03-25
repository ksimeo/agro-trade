package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.rest.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksimeo on 26.01.2015.
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void addNewOrder(Order order) {
        orderDao.save(order);
//        System.out.println("OrderService:" + order1);
//        return order1;
    }

    @Override
    public List<Order> getAllOrders() {

        return (ArrayList<Order>) orderDao.findAll();
    }

    @Override
    public void deleteAllOrders() {

        orderDao.deleteAll();
    }

    @Override
    public void deleteOrder(int id) {

        orderDao.delete(id);
    }
}