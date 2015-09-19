package com.exec02topicos.model;

import javax.persistence.EntityManager;

public class CidadeDAO {
    EntityManager em = Factory.getInstance().createEntityManager();
    
    public boolean cadastrarCid(String cidade){
        if((cidade == null) || (cidade.equals("")))
            return false;
        
        // atribuindo o nome da cidade
        Cidade c = new Cidade();
        c.setNomeCidade(cidade);
        
        // fazendo conexao c/ banco e salvando
        em.getTransaction();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        
        // retornando verdadeiro se foi salvo no banco
        return true;        
    }
}
