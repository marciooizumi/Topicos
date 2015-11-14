/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cadastrar"})
public class Cadastro extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter w = response.getWriter();
        w.println("<!DOCTYPE HTML>");
        w.println("<html>");
        w.println("<head>");
        w.println("     <title>Cadastro de usuários</title>");
        w.println("</head>");
        w.println("<body>");
        w.println("     <form action=\"cadastrar\" method=\"POST\">");
        w.println("         <legend>Cadastro de Usuários</legend>");
        w.println("         Nome: <input type=\"text\" name=\"fnome\" value=\"\">");
        w.println("         Senha: <input type=\"password\" name=\"fsenha\" value=\"\">");
        w.println("         <input type=\"submit\" value=\"Cadastrar\">");
        w.println("     </form>");
        w.println("</body>");
        w.println("</html>");

    }

    protected void doPost(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        EntityManager em = Persistence.createEntityManagerFactory("revisao3PU")
                .createEntityManager();
        
        Usuario u = new Usuario();
        
        u.setNome(req.getParameter("fnome"));
        u.setSenha(req.getParameter("fsenha"));
        
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        
        res.sendRedirect("hello");
    }

}
