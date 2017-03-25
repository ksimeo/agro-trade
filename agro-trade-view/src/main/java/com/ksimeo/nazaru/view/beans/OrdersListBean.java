package com.ksimeo.nazaru.view.beans;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.view.server_relation.IRestSys;
import com.ksimeo.nazaru.view.server_relation.RestSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//import com.ksimeo.nazaru.view.server_relation.StubSystem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by @Ksimeo on 10.02.2015.
 */
@ManagedBean
@ViewScoped
@Component
@Scope
public class OrdersListBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IRestSys restSys;

    private int id;

    private Date date;

    private String tel;

    private String email;

    private String region;

    private String productName;

    private Integer number;

    private List<Order> orders;

    private Order selectedOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Order> getOrders() {
        return restSys.getAllOrders();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public void selectedOrderListener() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        selectedOrder = (Order)context.getApplication().evaluateExpressionGet(context, "#{order}", Order.class);
//        FacesContext.getCurrentInstance().getExternalContext().redirect("/serventrance/orderslistadmin.xhtml");
    }

    public void delete() throws IOException {

        if(selectedOrder != null) {
            restSys.delOrder(selectedOrder.getId());
            selectedOrder = null;
        }
    }

    public void changePassword() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/changepass.xhtml");


    }

    public void cancel() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/addnewuser.xhtml");
    }

//    public void selectedOrderListener() {
//
//        FacesContext context = FacesContext.getCurrentInstance();
//        selectedOrder = context.getApplication().evaluateExpressionGet(context, "#{order}", Order.class);
//        selectedOrders.add(selectedOrder);
//    }
//
//    public void delete() throws IOException {
//
//        if(selectedOrders != null && !selectedOrders.isEmpty()) {
//            for(Order item : selectedOrders) {
//                restSys.delOrder(item.getId());
//            }
//           selectedOrders.clear();
//
//        } else {
//            error="You should choose party!!";
////            FacesContext.getCurrentInstance().getExternalContext().redirect("/serventrance/orderslistadmin.xhtml");
//        }
//
//    }
}