/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package callback;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.DAO;

/**
 * Web application lifecycle listener.
 *
 * @author Adriano_2
 */
public class CloseConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DAO.closeFactory();
    }
}
