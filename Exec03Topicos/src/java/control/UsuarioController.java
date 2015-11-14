package control;

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
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(name = "UsuarioController", urlPatterns = {"/cadastrar"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        RequestDispatcher resp = req.getRequestDispatcher("/WEB-INF/view/cadastro.jsp");
        resp.forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");

        UsuarioDAO udao = new UsuarioDAO();
        Usuario user = udao.insert(nome, senha);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("CidadeController");
        } else {
            request.setAttribute("error", "Erro ao cadastrar"); //dao.getLastMessage());
            RequestDispatcher resp = request.getRequestDispatcher("/WEB-INF/view/cadastro.jsp");
            resp.forward(request, response);
        }
    }

}
