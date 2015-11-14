
package edu.utfpr;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Usuario {
    
    @Id @GeneratedValue
    private int id;
    private String nome;
    private String senha;
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String n){
        this.nome = n;
    }
    public String getSenha(){
        return this.senha;
    }
    public void setSenha(String s){
        this.senha = s;
    }
}
