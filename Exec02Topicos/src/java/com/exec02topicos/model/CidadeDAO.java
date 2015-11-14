package com.exec02topicos.model;

import javax.persistence.EntityManager;

public class CidadeDAO {
    EntityManager em = Factory.getInstance().createEntityManager();
    
    public Cidade cadastrarCid(String cidade, String estado){
        if((cidade == null) || (cidade.equals("")) || (estado == null) || (estado.equals("")))
            return null;
        
        // atribuindo o nome da cidade
        Cidade c = new Cidade();
        c.setNomeCidade(cidade);
        c.setEstado(estado);
        
        // fazendo conexao c/ banco e salvando
        em.getTransaction();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        
        // retornando verdadeiro se foi salvo no banco
        return c;        
    }
}
