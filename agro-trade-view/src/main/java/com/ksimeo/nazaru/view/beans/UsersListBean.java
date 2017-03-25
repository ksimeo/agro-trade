package com.ksimeo.nazaru.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ksimeo.nazaru.view.server_relation.IRestSys;
//import com.ksimeo.nazaru.view.server_relation.StubSystem;
import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.view.server_relation.RestSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 12.10.2014.
 */

@ManagedBean
@ViewScoped
@Component
@Scope
public class UsersListBean {

    private String login;
    private String password;
    private boolean isAdmin;
    @Autowired
    private IRestSys restSystem;
    private List<User> users;
    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getUsers() {
        return getAllUsers();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<User> getAllUsers() {
        return users = restSystem.getAllUsers();
    }

    public void selectedUserListener() {
        FacesContext context = FacesContext.getCurrentInstance();
        selectedUser = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
    }

    public void delete() {

        if(selectedUser != null) {

            restSystem.delUser(selectedUser.getId());
        }

    }

    public void add() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/addnewuser.xhtml");
    }

    public void cancel() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/orderslistadmin.xhtml");
    }

}