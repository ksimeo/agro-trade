package com.ksimeo.nazaru.view.server_relation;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.core.models.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @Ksimeo on 15.02.2015.
 */
@Service
public class RestSystem implements IRestSys {

    private String urlBase = "http://localhost:6060";

    private Map<String, User> users = new HashMap<String, User>();
    private List<Order> orders = new ArrayList<>();

    public RestSystem() {
        User user1 = new User("Nazar", "Samarchuk", true);
        User user2 = new User("Maksym", "Ksimeo", true);
        User user3 = new User("Vasya", "#12345", false);
        users.put("Nazar", user1);
        users.put("Maksym", user2);
        users.put("Vasya", user3);
    }

    @Override
    public boolean isExistUser(String login) {
        boolean isExist = users.containsKey(login);
        if(isExist) return true;
        else return false;
    }

    @Override
    public User getRequeredUser(String userLogin) {
        return users.get(userLogin);
    }

    @Override
    public List getAllOrders() {
        try {
            String getAllOrders = sendGet(urlBase + "/getallorders");
            ObjectMapper mapper = new ObjectMapper();
            List orders = mapper.readValue(getAllOrders, new TypeReference<List<Order>>() {});
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getAllUsers() {
        try {
            String fullPath = urlBase + "/getallusers";
            String userStream = sendGet(fullPath);
            ObjectMapper mapper = new ObjectMapper();
            List<Order> users = mapper.readValue(userStream, new TypeReference<List<User>>() {});
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getAllProducts() {
        try {
            String fullPath = urlBase + "/getallproducts";
            String productStream = sendGet(fullPath);
            ObjectMapper mapper = new ObjectMapper();
            List products = mapper.readValue(productStream, new TypeReference<List<Product>>() {});
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getProductsName(int id) {
        List<Product> productsList = getAllProducts();
        Map<Integer, String> toSend = new HashMap<>();
        for(Product item : productsList) {
            toSend.put(item.getId(), item.getName());
        }

        return toSend.get(id);
//        List<Product> products = new ArrayList<>();
//        products.add(new Product(1, "Биогумат тип 1", 20.5f));
//        products.add(new Product(2, "Биогумат тип 2", 19.99f));
//        products.add(new Product(3, "Биогумат тип 3", 14.12f));
//        return toSend;
    }

    @Override
    public Product getProductById(int id) {
        try {
            String fullPath = urlBase + "/getbyid/" + id + "/";
            String productData = sendGet(fullPath);
            ObjectMapper om = new ObjectMapper();
            Product product = om.readValue(productData, Product.class);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendOrder(Order order) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(order);
            String fullURLPath = urlBase + "/addneworder";
            sendPost(fullURLPath, data);
//            System.out.println("Send Order");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delOrder(int id) {

        String fullPath = urlBase + "/delorderbyid/" + id + "/";
        try {
            sendGet(fullPath);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delProduct(int id) {

        String fullPath = urlBase + "/delproduct/" + id + "/";
        try {
            sendGet(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(Product product) {
        try {
            String fullPath = urlBase + "/addnewproduct/";
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(product);
            sendPost(fullPath, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delUser(int id) {
        try {
            String fullPath = urlBase + "/deluserbyid/" + id + "/";
            sendGet(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {

        try {
            String fullPath = urlBase + "/addnewuser";
            ObjectMapper om = new ObjectMapper();
            String data = om.writeValueAsString(user);
            sendPost(fullPath, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private String getRegionName(int id) throws Exception {
//
//        Map<Integer, String> regions = new HashMap<>();
//        regions.put(1, "");
//
//
//    }

    private String sendPost(String url, String data) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json" + ";charset=UTF-8");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

   private String sendGet(String url) throws Exception {

       URL obj = new URL(url);
       HttpURLConnection con = (HttpURLConnection) obj.openConnection();
       // optional default is GET
       con.setRequestMethod("GET");
       int responseCode = con.getResponseCode();
       System.out.println("\nSending 'GET' request to URL : " + url);
       System.out.println("Response Code : " + responseCode);
       BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
       String inputLine;
       StringBuffer response = new StringBuffer();
       while ((inputLine = in.readLine()) != null) {
           response.append(inputLine);
       }
       in.close();
       return response.toString();
   }

}