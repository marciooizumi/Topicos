package edu.utfpr;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Mensagem {
    @Id @GeneratedValue
    private long id;
    private String texto;
    public long getId(){ return this.id; }
    public void setId(long id){ this.id = id; }
    public String getTexto(){ return this.texto; }
    public void setTexto(String texto){ this.texto = texto; }
}
