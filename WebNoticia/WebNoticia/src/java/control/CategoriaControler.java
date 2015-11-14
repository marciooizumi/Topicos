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
import model.CategoriaDAO;
import model.Categoria;
import model.Usuario;
import util.Format;

/**
 *
 * @author Adriano_2
 */
public class CategoriaControler extends HttpServlet {

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
        String erro = "";
        HttpSession session = request.getSession();
        Usuario user = (Usuario)session.getAttribute("user");
        CategoriaDAO dao = new CategoriaDAO((EntityManager)request.getAttribute("manager"));
        
        boolean result = false;
        String acao = Format.getString(request.getParameter("acao"));
        int codigo = Format.getInt(request.getParameter("codigo"));
        String nome = Format.getString(request.getParameter("nome"));
        if (acao.equals("cadastro")) {
                //Alterar ou Inserir
                if (codigo == 0) {
                    //Inserir
                    Categoria cat = new Categoria(nome, user);
                    result = dao.criarNovo(cat);
                    if (!result) {
                        erro = "Falha ao criar uma nova categoria";
                    }
                }
                else {
                    //Alterar
                    Categoria cat = dao.getById(codigo);
                    if (cat != null && cat.getUsuario() == user) {
                        cat.setNome(nome);
                        result = dao.alterar(cat);
                        if (!result) {
                            erro = "Falha ao alterar a categoria";
                        }
                    }
                    else {
                        erro = "A categoria não existe";
                    }
                }
        }
        else if (acao.equals("excluir")) {
                Categoria cat = dao.getById(codigo);
                if (cat != null && cat.getUsuario() == user) {
                    result = dao.excluir(cat);
                    if (!result) {
                        erro = "Falha ao exlcuir a categoria";
                    }
                }
                else {
                    erro = "A categoria não existe";
                }
        }
        else {
            response.sendRedirect("categoria.jsp");
        }
        
        if (!result) {
            request.setAttribute("error", erro);
            RequestDispatcher req = request.getRequestDispatcher("categoria.jsp");
            req.forward(request, response);
        }
        else {
            response.sendRedirect("categoria.jsp?editar=" + codigo);
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
