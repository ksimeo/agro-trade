package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.rest.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ksimeo on 26.01.2015.
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product addNewProduct(Product product) {

        return productDao.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return (List<Product>)productDao.findAll();
    }

    @Override
    public void delProduct(int id) {

        productDao.delete(id);
    }

    @Override
    public void deleteAllProducts() {

        productDao.deleteAll();
    }

    @Override
    public Product getById(int id){

        return productDao.findOne(id);
    }
}
