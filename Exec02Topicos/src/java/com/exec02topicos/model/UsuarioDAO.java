package com.exec02topicos.model;

import com.exec02topicos.control.UsuarioController;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class UsuarioDAO{
    EntityManager em = Factory.getInstance().createEntityManager();
    
    public Usuario criarNovo(String nome, String senha) {
        Usuario user = new Usuario();
        
        if (user == null) {
            return null;
        }
        
        try {
            senha = criptografar(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        user.setNome(nome);
        user.setSenha(senha);
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
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
}
