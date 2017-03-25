package com.ksimeo.nazaru.view.beans;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.view.server_relation.IRestSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by @Ksimeo on 09.03.2015.
 */
@ManagedBean
@ViewScoped
@Component
@Scope
public class NewUserBean {
    @Autowired
    IRestSys restSys;

    private String login;

    private String password;

    private String confPassword;

    private boolean isAdmin;

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void add() throws IOException {

        if(!restSys.isExistUser(login)) {
            if (login != null && password != null) {
                if (password.equals(confPassword)) {
                    if (password.length() > 4) {
                        restSys.addUser(new User(login, password, isAdmin));
                        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/userslist.xhtml");
                    } else {
                        error = "Вы ввели слишком короткий пароль!";
                        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/addnewuser.xhtml");
                    }
                } else {
                    error = "Вы не подтвердили пароль!";
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/addnewuser.xhtml");
                }
            } else {
                error = "Вы ввели не все данные!";
                FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/addnewuser.xhtml");
            }
        } else {
            error = "Пользователь с таким логином уже существует!";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/addnewuser.xhtml");
        }
    }

    public void cancel() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/userslist.xhtml");
    }
}