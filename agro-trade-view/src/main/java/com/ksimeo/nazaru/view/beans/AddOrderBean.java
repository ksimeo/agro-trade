package com.ksimeo.nazaru.view.beans;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Region;
import com.ksimeo.nazaru.view.server_relation.IRestSys;
import com.ksimeo.nazaru.view.server_relation.RestSystem;
import com.ksimeo.nazaru.view.services.RegionService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by @Ksimeo on 13.02.2015.
 */
@ManagedBean
@ViewScoped
@Component
@Scope
public class AddOrderBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private IRestSys restSystem = new RestSystem();

    private Date date;

    private String name;

    private String tel;

    private String email;

    private int region;

    private int number;

    private int product;

    List products;

    RegionService rs = new RegionService();

    List<Region> regions;

    public List getProducts() {

        return restSystem.getAllProducts();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    public List getRegions() {
        return rs.getAllRegions();
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String doOrder(){

//        int m = 0;
////        Region reg = null;
//        List<Region> regs = getRegions();
//        String regionName = "";
//        for(int i = 0; i < regs.size(); i++) {
//            m = regs.get(i).getId();
//            if(m == region) {
//                regionName = regs.get(i).getName();
//            }
//        }



        restSystem.sendOrder(new Order(name, tel, email, rs.getNamebyId(region), restSystem.getProductsName(product), number));
        return "ordered";
    }

//    public void doPost() {
//
//        restSystem.doPost();
//    }
}