package model;

import java.util.List;
import javax.persistence.EntityManager;

public class CidadeDAO {

    EntityManager em = Factory.getInstance().createEntityManager();

    public Boolean insert(String cidade, String estado) {
        if ((cidade == null) || (cidade.equals("")) || (estado == null) || (estado.equals(""))) {
            return false;
        }

        // atribuindo o nome da cidade
        Cidade c = new Cidade();
        c.setNomeCidade(cidade);
        c.setEstado(estado);

        // fazendo conexao c/ banco e salvando
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();

        // retornando verdadeiro se foi salvo no banco
        return true;
    }

    public List<Cidade> getList() {
        em.getTransaction().begin();
        List<Cidade> lista = em.createQuery("SELECT c from Cidade as c").getResultList();
        em.close();
        return lista;
    }

    public Boolean delete(Long id) {
        if (id < 0) {
            return false;
        }

        //Cidade c = this.getById(id);// está dando erro
        Cidade c = (Cidade) em.createQuery("SELECT c FROM Cidade c WHERE c.id=:id").setParameter("id", id).getSingleResult();
        if (c == null) {
            return false;
        }
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Boolean update(Long id, String nomeC, String estado) {
        if ((id < 0) || (nomeC == null) || (nomeC.equals("") || (estado == null) || (estado.equals("")))) {
            return false;
        }

        Cidade c = (Cidade) em.createQuery("SELECT c FROM Cidade c WHERE c.id=:id").setParameter("id", id).getSingleResult();
        c.setNomeCidade(nomeC);
        c.setEstado(estado);

        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        return true;

    }

    public Cidade getById(Long id) {
        em.getTransaction().begin();
        Cidade c = (Cidade) em.createQuery("SELECT c FROM Cidade c WHERE c.id=:id").setParameter("id", id).getSingleResult();
        if (c == null) {
            return null;
        }
        em.close();
        return c;
    }
}
