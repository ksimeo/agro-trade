package com.ksimeo.nazaru.view.beans;

import com.ksimeo.nazaru.view.server_relation.IRestSys;
import com.ksimeo.nazaru.view.server_relation.RestSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
* Created by @Ksimeo on 04.02.2015.
*/
@ManagedBean
@ViewScoped
@Component
@Scope
public class LoginBean {

    @Autowired
    private IRestSys restSystem;

    private String error;

    private String login;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void doEnter() throws IOException {

        try {
            if (!login.isEmpty() && !password.isEmpty()) {
                boolean isUserExist = restSystem.isExistUser(login);
                if (isUserExist) {
                    String realPass = restSystem.getRequeredUser(login).getPassword();
                    if (password.equals(realPass)) {
                        boolean adminStatus = restSystem.getRequeredUser(login).isAdmin();
                        if (adminStatus) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/secure/orderslistadmin.xhtml");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/secure/orderslist.xhtml");
                        }
                    } else {
                        error = "Вы не верно ввели логин или пароль!";
                        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/login.xhtml");
                    }
                } else {
                    error = "Вы не верно ввели логин или пароль!";
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/login.xhtml");
                }
            } else {
                error = "Вы не заполнили все поля!";
                FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/login.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = "Что-то пошло не так!";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/login.xhtml");
        }
    }
}
