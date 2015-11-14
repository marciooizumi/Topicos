/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Márcio
 */
@ManagedBean
@SessionScoped
public class FrutaUI implements Serializable {

    private List<String> lista;
    private String novaFruta = "";

    /*
    public FrutaUI() {
        this.lista = new ArrayList<String>();
        this.lista.add("abobrinha");
        this.lista.add("abacate");
        this.lista.add("tomate");
    }
    */
    
    public FrutaUI(){
        this.lista = new ArrayList<String>();
    }
    
    public void add(){
        this.lista.add(this.novaFruta);
        this.novaFruta = "";
    }

    public String getNovaFruta() {
        return novaFruta;
    }

    public void setNovaFruta(String novaFruta) {
        this.novaFruta = novaFruta;
    }
    

    public List<String> getLista() {
        return this.lista;
    }

}
