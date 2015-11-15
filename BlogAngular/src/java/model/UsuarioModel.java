package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioModel {

    private static EntityManagerFactory factory = null;
    
    public void inserir (String nome, String senha, String email){
        EntityManager em = UsuarioModel.getEM();
        Usuario usu = new Usuario();

        if (nome.equals("") || senha.equals("") || email.equals("")) {
            return;
        }
        
     

        usu.setNome(nome);
        usu.setSenha(senha);
        usu.setEmail(email);


        em.getTransaction().begin();
        em.persist(usu);
        em.getTransaction().commit();
        em.close();
    }

    private static EntityManager getEM() {
        if (UsuarioModel.factory == null) {
            UsuarioModel.factory = Persistence.createEntityManagerFactory("BlogAngularPU");
        }
        return UsuarioModel.factory.createEntityManager();
    }
}
