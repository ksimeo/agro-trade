package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.Product;

import java.util.List;

/**
 * Created by Ksimeo on 26.01.2015.
 */
public interface IProductService {

    Product addNewProduct(Product product);
    List<Product> getAllProducts();
    void delProduct(int id);
    void deleteAllProducts();
    Product getById(int id);
}