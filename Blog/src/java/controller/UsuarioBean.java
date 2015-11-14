/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.UsuarioDAO;

/**
 *
 * @author Márcio
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    private String usuario = "";
    private String senha = "";

    public void cadastrar() {
        UsuarioDAO u = new UsuarioDAO();
        u.insert(usuario, senha);
        this.usuario = "";
        this.senha = "";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
