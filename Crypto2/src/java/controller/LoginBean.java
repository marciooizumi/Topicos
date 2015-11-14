/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.UsuarioDAO;

/**
 * Simple login bean.
 *
 * @author itcuties
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 7765876811740798583L;

    // Simple user database :)
    private String username;
    private String password;
    private boolean loggedIn;
    private UsuarioDAO udao = new UsuarioDAO();

    public UsuarioDAO getUdao() {
        return udao;
    }

    public void setUdao(UsuarioDAO udao) {
        this.udao = udao;
    }

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    /**
     * Login operation.
     *
     * @return
     */
    public String doLogin() {

        try {
            if (udao.verLogin(username, password) != null) {
                FacesMessage msg = new FacesMessage("Login efetuado com sucesso!");
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                loggedIn = true;
                return navigationBean.redirectToWelcome();
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set login ERROR
        // To to login page
        FacesMessage msg = new FacesMessage("Login ou senha errados!", "Digite alguma coisa nessa porra");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return navigationBean.toLogin();

    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigationBean.redirectToLogin();
    }

    // ------------------------------
    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

}
