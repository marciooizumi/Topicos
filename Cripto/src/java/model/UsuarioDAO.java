/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Márcio
 */
public class UsuarioDAO {

    private static EntityManagerFactory factory = null;


    public Usuario insert(String login, String senha) {
        
        EntityManager em = UsuarioDAO.getEM();
        
        if (login.equals("") || senha.equals("") ) {
            return null;
        }
        
        Usuario user = new Usuario();

        try {
            senha = criptografar(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        user.setLogin(login);
        user.setSenha(senha);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public void delete(Usuario usu){
        EntityManager em = UsuarioDAO.getEM();
        em.getTransaction().begin();
        em.remove(usu);
        em.getTransaction().commit();
        em.close();
    }
    
    public String criptografar(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String s = hash.toString(16);
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        return s;
    }
    
    public Usuario verLogin(String usu, String senha) throws NoSuchAlgorithmException {
        EntityManager em = UsuarioDAO.getEM();
        try {
            senha = criptografar(senha);
            Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.login ="
                    + ":usu and u.senha =:senha")
                    .setParameter("usu", usu).setParameter("senha", senha).getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    private static EntityManager getEM() {
        if (UsuarioDAO.factory == null) {
            UsuarioDAO.factory = Persistence.createEntityManagerFactory("CriptoPU");
        }
        return UsuarioDAO.factory.createEntityManager();
    }
}
