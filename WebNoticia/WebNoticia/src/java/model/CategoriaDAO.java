/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import static model.DAO.createEntityManager;

/**
 *
 * @author Adriano_2
 */
public class CategoriaDAO extends DAO {
    
    public CategoriaDAO(EntityManager manager) {
        super(manager);
    }
    
    protected boolean isValid(Categoria cat) {
        try {
            if (cat.getNome().isEmpty() || cat.getUsuario() == null) {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean criarNovo(Categoria cat) {
        //Validação dos dados
        if (!isValid(cat)) {
            return false;
        }
        
        try {
            manager.getTransaction().begin(); 
            manager.refresh(cat.getUsuario());
            manager.persist(cat);
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
    
    public boolean alterar(Categoria cat) {
        //Validação dos dados
        if (!isValid(cat)) {
            return false;
        }
        
        try {
            manager.getTransaction().begin();    
            manager.merge(cat);
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
    
    public boolean excluirById(int id) {
        return excluir(getById(id));
    }
    
    public boolean excluir(Categoria cat) {
        cat.setAtivo(false);
        return alterar(cat);
    }
   
    public Categoria getById(int id) {
        return manager.find(Categoria.class, id);
    }
    
    public List<Categoria> getAllByUser(Usuario user) {
        return (List<Categoria>) manager.createNamedQuery("Categoria.listByUsuario")
               .setParameter("usuario", user)
               .getResultList();
    }
}
