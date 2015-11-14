package relatorio;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import net.sf.jasperreports.export.ExporterInput;
import org.hibernate.jdbc.Work;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Márcio
 */
public class ExecutorRelatorio implements Work {

    private String caminhoRelatorio;
    private HttpServletResponse response;
    private Map<String, Object> parametros;
    private String nomeArquivoSaida;

    public ExecutorRelatorio(String caminhoRelatorio, HttpServletResponse response, Map<String, Object> parametros, String nomeArquivoSaida) {
        this.caminhoRelatorio = caminhoRelatorio;
        this.response = response;
        this.parametros = parametros;
        this.nomeArquivoSaida = nomeArquivoSaida;
    }
    

    @Override
    public void execute(Connection cnctn) throws SQLException {

        try {
            InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
            JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, cnctn);

//            JRExporter exportador = new JRPdfExporter();
//            exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
//            		exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
            Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> exportador = new JRPdfExporter();
            exportador.setExporterInput(new SimpleExporterInput(print));
            exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            
            response.setContentType("application/pdf");
            
            exportador.exportReport();
        } catch (Exception e) {
            throw new SQLException("Erro ao emitir relatorio" + this.caminhoRelatorio, e);
        }

    }

}
