package com.ksimeo.nazaru.rest.controllers;

import com.ksimeo.nazaru.rest.services.OrderService;
import com.ksimeo.nazaru.core.models.Order;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 26.01.2015.
 */
@Controller
public class OrderController  {

    @Autowired
    private OrderService orderService;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value="addneworder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public void addNewOrder(@RequestBody Order order) throws IOException {

//        Order order = mapper.readValue(jsonData, Order.class);
        orderService.addNewOrder(order);
//        System.out.println("OrderController:" + order);
//        return "/ordered";
    }

    @RequestMapping(value = "/getallorders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8" )
    @ResponseBody
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        System.out.println(orders);
        return orders;
    }

    @RequestMapping(value = "/delorderbyid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delOrderById(@PathVariable int id){

        orderService.deleteOrder(id);
    }


}
