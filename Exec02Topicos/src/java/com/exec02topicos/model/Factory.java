/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exec02topicos.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Márcio
 */
public class Factory {

    // Variável estática que conterá a instância do método
    private static EntityManagerFactory instance;

    static {
        // Operações de inicialização da classe
    }

    // Construtor privado. Suprime o construtor público padrão
    public Factory() {
    }

    // Método público estático de acesso único ao objeto
    public static EntityManagerFactory getInstance() {

        if (instance == null) {
            inicializaInstancia();
            // O valor é retornado para quem está pedindo

        }
        return instance;
        // Retorna o a instância do objeto

    }

    /*
     * Este metodo é sincronizado para evitar que devido a concorrencia sejam criados mais de
     * uma instancia.
     */
    private static synchronized void inicializaInstancia() {
        if (instance == null) {
//            instance = new Factory();
            instance = Persistence.createEntityManagerFactory("Top03");
        }
    }

}
