/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
/**
 * Simple navigation bean
 * @author itcuties
 *
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {
 
    private static final long serialVersionUID = 1520318172495977648L;
    
    public String toCadastroMensagem(){
        return "/view/cadastroMensagem.xhtml";
    }
    
    public String toLista(){
        return "/view/lista.xhtml";
    }
 
    public String redirectToLista() {
        return "/view/lista.xhtml?faces-redirect=true";
    }
    
    public String toEditarMsg(){
        return "/view/editarMensagens.xhtml";
    }
    
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/view/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/view/login.xhtml";
    }
     
    /**
     * Redirect to info page.
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/view/info.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to info page.
     * @return Info page name.
     */
    public String toInfo() {
        return "/view/info.xhtml";
    }
     
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/view/welcome.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/view/welcome.xhtml";
    }
    
    //redirecionar para cadastro de usuarios
    public String toCadastroUser(){
        return "/view/cadastroUser.xhtml";
    }
     
}
