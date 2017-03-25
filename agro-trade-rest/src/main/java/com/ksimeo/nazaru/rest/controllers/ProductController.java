package com.ksimeo.nazaru.rest.controllers;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.rest.services.IProductService;
import com.ksimeo.nazaru.rest.services.ProductService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ksimeo on 23.01.2015.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/addnewproduct", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void addNewProduct(@RequestBody String jsonData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(jsonData, Product.class);
        productService.addNewProduct(product);
    }

    @RequestMapping(value = "getproductbyid/{id}/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Product getProductById(@PathVariable int id) {

        return productService.getById(id);
    }

    @RequestMapping(value = "/delproduct/{id}/", method = RequestMethod.GET)
    @ResponseBody
    public void delProduct(@PathVariable int id) throws IOException {

        productService.delProduct(id);
    }

    @RequestMapping(value = "/getallproducts", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Product> getAllProducts() throws IOException {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/delallproducts", method = RequestMethod.DELETE, produces = "application/json")
    public String delAllProducts() throws IOException {

        productService.deleteAllProducts();
        return "all_products_deleted";
    }
}