/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import model.Factory;
import model.Mensagem;
import model.MensagemDAO;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Márcio
 */
@ManagedBean
@SessionScoped
public class MensagemBean {

    Mensagem mensagem = new Mensagem();
    private Long id;
    private String titulo = "";
    private String texto = "";
    private Date dataMsg;

    MensagemDAO mdao = new MensagemDAO();

    private List<Mensagem> lista;

    public Date getDataMsg() {
        return dataMsg;
    }

    public void setDataMsg(Date dataMsg) {
        this.dataMsg = dataMsg;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public void cadastrarM() {
        mdao.insert(this.titulo, this.texto);
        this.titulo = "";
        this.texto = "";
    }

    public void atualizar() {
        mdao.alterar(this.mensagem);
        this.mensagem.setTexto("");
        this.mensagem.setTitulo("");
    }

    public void excluir() {
        mdao.delete(mensagem);
    }

    public List<Mensagem> getlistarM() {
        return mdao.listar();
    }

    public List<Mensagem> getLista() {
        return lista;
    }

    public void setLista(List<Mensagem> lista) {
        this.lista = lista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public MensagemDAO getMdao() {
        return mdao;
    }

    public void setMdao(MensagemDAO mdao) {
        this.mdao = mdao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
