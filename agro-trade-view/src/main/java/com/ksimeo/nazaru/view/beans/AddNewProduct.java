package com.ksimeo.nazaru.view.beans;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.view.server_relation.IRestSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ksimeo on 08.03.2015.
 */
@ManagedBean
@ViewScoped
@Component
@Scope
public class AddNewProduct {

    @Autowired
    IRestSys restSys;

    private String name;

    private float price;

    private Product selectedProduct;

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String error;

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

    public void add() throws IOException {

        if(name != null && price != 0f ) {

            restSys.addProduct(new Product(name, price));
            FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/productslist.xhtml");
        } else {
            error = "Вы ввели не все данные!";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/addnewproduct.xhtml");
        }
    }

    public void cancel() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/productslist.xhtml");
    }

}
