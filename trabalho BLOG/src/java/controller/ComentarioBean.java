/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Comentario;
import model.ComentarioDAO;
import model.Mensagem;
import model.Usuario;

/**
 *
 * @author Márcio
 */
@ManagedBean
@SessionScoped
public class ComentarioBean {

    Mensagem mensagem = new Mensagem();
    Usuario usuario = new Usuario();
    Comentario comentario = new Comentario();
    private String post;
    
    ComentarioDAO cdao = new ComentarioDAO();
    
    
    
    public void cadastrarComentario() {
        cdao.insert(this.mensagem, this.usuario, this.post);
//        cdao.insert(comentario);
        this.post = "";
    }

    public void atualizar() {
        cdao.alterar(this.comentario);
    }

    public void excluir() {
        cdao.delete(this.comentario);
    }

    public List<Comentario> getlista() {
        return cdao.listar();
    }
    
    
    
    
    
    

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public ComentarioDAO getCdao() {
        return cdao;
    }

    public void setCdao(ComentarioDAO cdao) {
        this.cdao = cdao;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
            
    public ComentarioBean() {
    }
    
}
