package edu.utfpr;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/hello"})
public class A extends HttpServlet {

    public void doGet(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("logado") == null) {
            res.sendRedirect("login");
            return;
        }
        PrintWriter writer = res.getWriter();
        EntityManager em = Persistence.createEntityManagerFactory("revisao3PU")
                .createEntityManager();
        List<Mensagem> lista = em.createQuery("SELECT c FROM Mensagem c")
                .getResultList();
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <body>");
        writer.println("        <h1>Agora com Servlets</h1>");
        writer.println("<form action=\"hello\" method=\"POST\">");
        writer.println("    <input type=\"text\" name=\"nova_mensagem\" value=\"\" autofocus/>");
        writer.println("    <input type=\"submit\" value=\"Adicionar... &rarr;\" />");
        writer.println("</form>");
        writer.println("<ul>");
        for (Mensagem m : lista) {
            writer.println("<li>" + m.getTexto() + "</li>");
        }
        writer.println("</ul>");
        writer.println("<a href=\"Logout\">Sair</a>");
        writer.println("    </body>");
        writer.println("</html>");
        em.close();
    }

    public void doPost(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        EntityManager em = Persistence.createEntityManagerFactory("revisao3PU")
                .createEntityManager();
        Mensagem m = new Mensagem();
        m.setTexto(req.getParameter("nova_mensagem"));
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        em.close();
        res.sendRedirect("hello");
    }

}
