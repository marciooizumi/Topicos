/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import model.UsuarioDAO;
import util.Format;

/**
 *
 * @author Adriano_2
 */
public class UsuarioControler extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cod = Format.getString(request.getParameter("codigo"));
        String nome = Format.getString(request.getParameter("nome"));
        String usuario = Format.getString(request.getParameter("usuario"));
        String senha = Format.getString(request.getParameter("senha"));
        
        Usuario user;
        if (Format.getBoolean(request.getSession().getAttribute("logado"))) {
            user = (Usuario)request.getSession().getAttribute("user");
            user.setNome(nome);
            user.setUsuario(usuario);
            user.setSenha(senha);
        }
        else {
            user = new Usuario(nome, usuario, senha);
        }
        
        UsuarioDAO dao;
        dao = new UsuarioDAO((EntityManager)request.getAttribute("manager"));
        boolean result;
        if (cod.isEmpty()) {
            //Create new user
            result = dao.criarNovo(user);
        }
        else {
            //Update user
            result = dao.alterar(user);
        }
        
        if (result) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("logado", true);
            response.sendRedirect("index.jsp");
        }
        else {
            request.setAttribute("error", dao.getLastMessage());
            RequestDispatcher resp = request.getRequestDispatcher("cadastro.jsp");
            resp.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
