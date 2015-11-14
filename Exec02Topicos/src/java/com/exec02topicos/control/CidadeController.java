package com.exec02topicos.control;

import com.exec02topicos.model.Cidade;
import com.exec02topicos.model.CidadeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CidadeController", urlPatterns = {"/CidadeController"})
public class CidadeController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/cadastroCidade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeCid = request.getParameter("nomeCidade");
        String estado = request.getParameter("estado");

        CidadeDAO cDao = new CidadeDAO();
        Cidade c = cDao.cadastrarCid(nomeCid, estado);

        if (c != null) {
            HttpSession session = request.getSession();
            session.setAttribute("cidade", c);
            response.sendRedirect("welcome.jsp");
        } else {
            request.setAttribute("error", "Erro ao cadastrar"); //dao.getLastMessage());
            RequestDispatcher resp = request.getRequestDispatcher("erro.jsp");
            resp.forward(request, response);
        }
    }

}
