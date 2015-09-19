package com.exec02topicos.control;

import com.exec02topicos.model.Usuario;
import com.exec02topicos.model.UsuarioDAO;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.swing.UIManager.getString;

@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");

        UsuarioDAO udao = new UsuarioDAO();
        Usuario user = udao.criarNovo(nome, senha);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("view/welcome.jsp");
        } else {
            request.setAttribute("error", "Erro ao cadastrar"); //dao.getLastMessage());
            RequestDispatcher resp = request.getRequestDispatcher("erro.jsp");
            resp.forward(request, response);
        }
    }

}
