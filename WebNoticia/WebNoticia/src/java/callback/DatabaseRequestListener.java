/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package callback;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.DAO;

/**
 * Web application lifecycle listener.
 *
 * @author Adriano_2
 */
public class DatabaseRequestListener implements ServletRequestListener, HttpSessionListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession();
        EntityManager manager = (EntityManager) session.getAttribute("manager");
        if (manager == null) {
            manager = DAO.createEntityManager();
            session.setAttribute("manager", manager);
        }
        request.setAttribute("manager", manager);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        EntityManager manager = (EntityManager) session.getAttribute("manager");
        if (manager != null) {
            manager.close();
        }
    }
}
