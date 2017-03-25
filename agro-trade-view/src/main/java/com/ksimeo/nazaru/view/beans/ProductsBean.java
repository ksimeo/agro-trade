package com.ksimeo.nazaru.view.beans;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.view.server_relation.IRestSys;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.view.server_relation.RestSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 10.02.2015.
 */
@ManagedBean
@ViewScoped
@Component
@Scope
public class ProductsBean {

    @Autowired
    private IRestSys restSys;

    private int id;

    private String name;

    private float price;

    private Product selectedProduct;

    private List products;

    public void setProducts(List products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void selectedProductListener() {

        FacesContext context = FacesContext.getCurrentInstance();
        selectedProduct = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
    }

    public void deleteProd() {

        restSys.delProduct(selectedProduct.getId());
    }

    public List getProducts() {
        return getAllProducts();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List getAllProducts() {

        return restSys.getAllProducts();
    }

    public void delete() {

        restSys.delProduct(selectedProduct.getId());
    }

    public void modify() throws IOException {

        if(selectedProduct != null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("/serventrance/modifyproduct.xhtml");
        }
    }

    public void added() throws IOException {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/serventrance/addnewproduct.xhtml");
    }

    public void cancel() throws IOException {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/serventrance/orderslistadmin.xhtml");
    }
}
