/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Márcio
 */
@ManagedBean
@RequestScoped
public class DateConverter {

    private Date horaAgora;

    public Date getHoraAgora() {
        return horaAgora;
    }

    public void setHoraAgora(Date horaAgora) {
        this.horaAgora = horaAgora;
    }
    
}
