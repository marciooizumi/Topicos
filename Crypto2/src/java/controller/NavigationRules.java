/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Márcio
 */
@ManagedBean
@ApplicationScoped
public class NavigationRules implements Serializable {

    public String cadastroUser() {
        return "cadastroUser";
    }

    public String editarMensagens() {
        return "editarMensagens";
    }
    
    public String atualizarMsg(){
        return "welcome";
    }

}
