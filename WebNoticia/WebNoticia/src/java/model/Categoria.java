package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity (name = "categoria")
@NamedQuery(name="Categoria.listByUsuario", query="SELECT c FROM categoria AS c "+
        "WHERE c.usuario = :usuario AND c.ativo = true ORDER BY c.nome") 
public class Categoria  implements java.io.Serializable {
    @Id
    @GeneratedValue
    private int codigo;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;
    
    @Column(nullable = false)
    private boolean ativo = true;
   
    public Categoria() {
    }
	
    public Categoria(int codigo, String nome, Usuario usuario) {
        this.codigo = codigo;
        this.nome = nome;
        this.usuario = usuario;
    }
    
    public Categoria(String nome, Usuario usuario) {
       this.nome = nome;
       this.usuario = usuario;
    }
   
    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}