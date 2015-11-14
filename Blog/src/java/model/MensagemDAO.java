/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class MensagemDAO {

    private static EntityManagerFactory factory = null;

    //EntityManager em = Factory.getInstance().createEntityManager();
    public Boolean insert(String titulo, String texto) {
        EntityManager em = MensagemDAO.getEM();
        Mensagem msg = new Mensagem();

        if (titulo.equals("") || titulo == null || texto.equals("") || texto == null) {
            return false;
        }

//        Calendar data = Calendar.getInstance();
//        Date d = data.getTime();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        dateFormat.format(d);

        msg.setTitulo(titulo);
        msg.setTexto(texto);
//        msg.setDataMensagem(d);
        msg.setDataMensagem(Calendar.getInstance());


        em.getTransaction().begin();
        em.persist(msg);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public void delete(Mensagem m) {
//        EntityManager em = MensagemDAO.getEM();
//        em.getTransaction().begin();
//        em.remove(m);
//        em.getTransaction().commit();
//        em.close();

        EntityManager em = MensagemDAO.getEM();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Mensagem c WHERE c.id = :id").setParameter("id", m.getId()).executeUpdate();
        em.getTransaction().commit();
        em.close();

    }

    public Usuario verLogin(String usu, String senha) throws NoSuchAlgorithmException {
        EntityManager em = MensagemDAO.getEM();
        try {
            Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.login ="
                    + ":usu and u.senha =:senha")
                    .setParameter("usu", usu).setParameter("senha", senha).getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Mensagem> listar() {
//        em.getTransaction().begin();
//        List<Mensagem> log = em.createQuery("SELECT m FROM Mensagem m").getResultList();
//        em.getTransaction().commit();
//        em.close();
//        return log;

        EntityManager em = MensagemDAO.getEM();
        List<Mensagem> lista = (List<Mensagem>) em.createQuery("SELECT m FROM Mensagem m").getResultList();
        em.close();
        return lista;
    }

    public boolean alterar(Mensagem m) {
        EntityManager em = MensagemDAO.getEM();
        //Mensagem m = getById(id);
        //m.setTitulo(titulo);
        //m.setTexto(newText);
        try {
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Mensagem getById(Long id) {
        EntityManager em = MensagemDAO.getEM();
        return em.find(Mensagem.class, id);
    }

    private static EntityManager getEM() {
        if (MensagemDAO.factory == null) {
            MensagemDAO.factory = Persistence.createEntityManagerFactory("BlogPU");
        }
        return MensagemDAO.factory.createEntityManager();
    }
}
