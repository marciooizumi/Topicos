/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Márcio
 */
@ManagedBean
@RequestScoped
public class FrutaUI implements Serializable {

    private List<String> lista;

    public FrutaUI() {
        this.lista = new ArrayList<String>();
        this.lista.add("abobrinha");
        this.lista.add("pepino");
        this.lista.add("abacate");       
    }
    
    public List <String> getLista(){
        return this.lista;
    }

}
