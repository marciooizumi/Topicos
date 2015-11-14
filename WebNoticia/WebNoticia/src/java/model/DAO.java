/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Adriano_2
 */
public abstract class DAO {
    protected String message;
   
    EntityManager manager;
    public DAO(EntityManager manager) {
        this.manager = manager;
    }
    
    public EntityManager getManager() {
        return manager;
    }
    
    public void closeManager() {
        manager.close();
    }
    
    public String getLastMessage() {
        return message;
    }
    
    static private EntityManagerFactory factory;
    public static EntityManager createEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("sistemanoticia");
        }
        return factory.createEntityManager();
    }
    
    public static void closeFactory() {
        createEntityManager().close();
        factory = null;
    }
}
