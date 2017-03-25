package com.ksimeo.nazaru.view.beans;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.view.server_relation.IRestSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Ksimeo on 09.03.2015.
 */
@ManagedBean
@ViewScoped
@Component
@Scope
public class ModifyProduct {

    @Autowired
    private IRestSys restSys;

    private String name;

    private float price;

    private String error;

    private Product modProduct;

//    {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ProductsBean productsBean = (ProductsBean)fc.getApplication().evaluateExpressionGet(fc, "#{productsBean}", ProductsBean.class);
//        modProduct = productsBean.getSelectedProduct();
//        this.name = modProduct.getName();
//        this.price = modProduct.getPrice();
//    }

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void modify() {

        if(name != null && price != 0f) {
            int productId = modProduct.getId();
            restSys.delProduct(productId);
            restSys.addProduct(new Product(modProduct.getId(), name, price));
        } else{

            error = "Вы ввели не все данные!";
        }
    }
}
