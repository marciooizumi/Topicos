/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Márcio
 */
public class ComentarioDAO {

    //EntityManager em = Factory.getInstance().createEntityManager();
    private static EntityManagerFactory factory = null;

    Comentario comentario = new Comentario();

    public void insert(Long mensagemId, String post, String nomeUsu) {
        EntityManager em = getEM();
        
        comentario.setPost(post);
        comentario.setNomeUsu(nomeUsu);
        comentario.setIdMensagem(mensagemId);

        em.getTransaction().begin();
        em.persist(this.comentario);
        em.getTransaction().commit();
        em.close();
    }
    
    public void delete(Comentario c) {
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Comentario c WHERE c.id = :id").setParameter("id", c.getId()).executeUpdate();
        em.getTransaction().commit();
        em.close();        
    }
    
    public boolean alterar(Comentario c) {
        EntityManager em = getEM();
       try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public List<Comentario> listar() {
        EntityManager em = getEM();
        List<Comentario> lista = (List<Comentario>) em.createQuery("SELECT c FROM Comentario c").getResultList();
        em.close();
        return lista;
    }

    private static EntityManager getEM() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("BlogPU2");
        }
        return factory.createEntityManager();
    }
}
