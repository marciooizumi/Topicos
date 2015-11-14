package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "noticia")
public class Noticia  implements java.io.Serializable {
    @Id
    @GeneratedValue
    private int codigo;
    
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;
    
    @Column(length = 150, nullable = false)
    private String titulo;
    
    @Column(nullable = false)
    private String resumo;
    
    @Column(nullable = false, columnDefinition="TEXT")
    private String texto;
    
    @Column(nullable = false)
    private int hits = 0;
    
    @Column(nullable = false)
    private boolean ativo = true;

    public Noticia() {
    }
	
    public Noticia(int codigo, Usuario usuario, Categoria categoria, String titulo, String resumo, String texto, int hits, boolean ativo) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.categoria = categoria;
        this.titulo = titulo;
        this.resumo = resumo;
        this.texto = texto;
        this.hits = hits;
        this.ativo = ativo;
    }
   
    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getResumo() {
        return this.resumo;
    }
    
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public int getHits() {
        return this.hits;
    }
    
    public void setHits(int hits) {
        this.hits = hits;
    }
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}


