/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebeans;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author Márcio
 */
@ManagedBean
@RequestScoped
public class relatorioDeCidades implements Serializable {

    private FacesContext facesContext;
    private HttpServletResponse response;
    private EntityManager manager;

    public relatorioDeCidades() {
    }

    public void emitir() {
       
         Map<String, Object> parametros = new HashMap<>();
        
         ExecutorRelatorio relatorio = new ExecutorRelatorio("/relatorios/relatorio_cidades.jasper", 
         this.response, parametros, "Relatorio de cidades.pdf");
        
         Session session = manager.unwrap(Session.class) ;
         session.doWork(relatorio);
        
         facesContext.responseComplete();
    }
    
}
