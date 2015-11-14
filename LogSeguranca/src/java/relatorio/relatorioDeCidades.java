package relatorio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.service.spi.InjectService;

/**
 *
 * @author Márcio
 */
@Named
@RequestScoped
public class relatorioDeCidades implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Date dataInicio;
    private Date dataFim;
    
    @Inject
    private FacesContext facesContext;
    @Inject
    private HttpServletResponse response;
    @Inject
    private EntityManager manager;

    public relatorioDeCidades() {
    }

    public void emitir() {

        Map<String, Object> parametros = new HashMap<>();
//        parametros.put("data_inicio", this.getDataInicio());
//        parametros.put("data_fim", this.getDataFim());

        ExecutorRelatorio relatorio = new ExecutorRelatorio("/relatorios/relatorio_cidades.jasper",
                this.response, parametros, "Relatorio de cidades.pdf");

//        ExecutorRelatorio relatorio = new ExecutorRelatorio("/relatorios/relatorio_cidades.jasper",
//                null, null, "Relatorio de cidades.pdf");
        
        
        Session session = manager.unwrap(Session.class);
        session.doWork(relatorio);

        facesContext.responseComplete();
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

}
