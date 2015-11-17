package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioModel {

    private static EntityManagerFactory factory = null;

    public void inserir(String nome, String senha, String email) {
        Usuario usu = new Usuario();

        if (nome.equals("") || senha.equals("") || email.equals("")) {
            return;
        }

        usu.setNome(nome);
        usu.setSenha(senha);
        usu.setEmail(email);

//        EntityManager em = getEM();
        EntityManager em = this.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(usu);
        em.getTransaction().commit();
        em.close();
    }

   public EntityManagerFactory getFactory () {
        if (UsuarioModel.factory == null)
            UsuarioModel.factory = Persistence.createEntityManagerFactory("jpa01");
        return UsuarioModel.factory;
    }
}
