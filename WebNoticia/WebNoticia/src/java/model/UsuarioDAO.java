/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.EntityManager;

/**
 *
 * @author Adriano_2
 */
public class UsuarioDAO extends DAO {
    
    public UsuarioDAO(EntityManager manager) {
        super(manager);
    }
    
    public Usuario login(String usuario, String senha) {
        Usuario login;
        try {
            login = (Usuario) manager.createNamedQuery("Usuario.findByUsuario")
                .setParameter("usuario", usuario)
                .setParameter("senha", senha)
                .getSingleResult();
        } catch (Exception e) {
            message = e.getMessage();
            return null;
        }
 
        return login;
    }
    
    protected boolean isValid(Usuario user) {
        if ("".equals(user.getNome()) || "".equals(user.getUsuario()) || "".equals(user.getSenha())) {
            message = "Preencha todos os campos";
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean criarNovo(Usuario user) {
        //Validação dos dados
        if (!isValid(user)) {
            return false;
        }
        
        try {
            manager.getTransaction().begin();    
            manager.persist(user);
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            message = e.getMessage();
            return false;
        }
        return true;
    }
    
    public boolean alterar(Usuario user) {
        //Validação dos dados
        if (!isValid(user)) {
            return false;
        }
        
       try {
            manager.getTransaction().begin();    
            manager.merge(user);
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            message = e.getMessage();
            return false;
        }
        return true;
    }
}
