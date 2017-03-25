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
public class PasswordBean {
    @Autowired
    private IRestSys restSys;

    private String login;

    private String password;

    private String newPassword;

    private String confirmPassw;

    private String error;


    public String getConfirmPassw() {
        return confirmPassw;
    }

    public void setConfirmPassw(String confirmPassw) {
        this.confirmPassw = confirmPassw;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public void change() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) fc.getApplication().evaluateExpressionGet(fc, "#{loginBean}", LoginBean.class);
        String login = loginBean.getLogin();
        User user = restSys.getRequeredUser(login);
        if (password.equals(user.getPassword())) {
            if (newPassword.equals(confirmPassw)) {
                user.setPassword(newPassword);
                FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/orderslistadmin.xhtml");
            } else {
                error = "Вы неверно подтвердили пароль!";
                FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/changepass.xhtml");
            }
        } else {
            error = "Вы ввели неверный пароль!";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/changepass.xhtml");
        }
    }

    public void cancel() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("../serventrance/orderslistadmin.xhtml");
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
