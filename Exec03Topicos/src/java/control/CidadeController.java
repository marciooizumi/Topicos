package control;

import model.Cidade;
import model.CidadeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CidadeController", urlPatterns = {"/cadastroCid"})
public class CidadeController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CidadeDAO cDao = new CidadeDAO();
        Long id = -1L;
        Boolean result = false;
        
        List<Cidade> cid = cDao.getList();
        
        //recebendo id a ser excluido
        String num = request.getParameter("id");
        if (num != null) {
            id = Long.parseLong(num);
        }
        request.setAttribute("cidade", cid);
        String acao = request.getParameter("acao");
        cDao = new CidadeDAO();
        if (acao != null) {
            if (acao.equals("D")) {
                result = cDao.delete(id);
            }
            if (acao.equals("U")){
                Cidade c = cDao.getById(id);
                request.setAttribute("acao", "U");
                request.setAttribute("btn", "Alterar");
                request.setAttribute("nomeCidU", c.getNomeCidade());
                request.setAttribute("estadoU", c.getEstado());
            }
        }else{
            request.setAttribute("btn", "Cadastrar");
        }
        
        if (result) {
            response.sendRedirect("cadastroCid");
        } else {
            request.getRequestDispatcher("/WEB-INF/view/cadastroCidade.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = -1L;
        Boolean result = false;
        String nomeCid = request.getParameter("nomeCidade");
        String estado = request.getParameter("estado");
        String num = request.getParameter("id");
        if (num != null) {
            id = Long.parseLong(num);
        }
        String acao = request.getParameter("acao");

        CidadeDAO cDao = new CidadeDAO();
        if (acao==null) {
            result = cDao.insert(nomeCid, estado);
        } else if (acao.equals("U")) {
            result = cDao.update(id, nomeCid, estado);
        } else if (acao.equals("D")) {
            result = cDao.delete(id);
        } else {
            result = false;
        }

        if (result) {
//            HttpSession session = request.getSession();
//            session.setAttribute("cidade", c);
//            request.setAttribute("cidade", c);
//            session.setAttribute("msg1", nomeCid + " Cadastrado com sucesso");
            response.sendRedirect("cadastroCid");
        } else {
            request.setAttribute("error", "Erro ao cadastrar"); //dao.getLastMessage());
            RequestDispatcher resp = request.getRequestDispatcher("erro.jsp");
            resp.forward(request, response);
        }
    }

}
