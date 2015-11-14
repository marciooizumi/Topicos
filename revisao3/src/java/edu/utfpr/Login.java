package edu.utfpr;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <body>");
        writer.println("        <h1>Login Legal</h1>");
        writer.println("        <form action=\"login\" method=\"POST\">");
        writer.println("            <input type=\"text\" name=\"login\" value=\"\" />");
        writer.println("            <input type=\"text\" name=\"senha\" value=\"\" />");
        writer.println("            <input type=\"submit\" value=\"logar &rarr;\" />");
        writer.println("        </form>");
        writer.println("        <p>Ainda não tem cadastro? Clique <a href=\"Cadastro\">aqui!</a>");
        writer.println("    </body>");
        writer.println("</html>");
    }

    public void doPost(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        String login = req.getParameter("login"),
                senha = req.getParameter("senha");
        if (login.equals("marcio") && senha.equals("adalberto")) {
            HttpSession session = req.getSession();
            session.setAttribute("logado", true);
        }
        res.sendRedirect("hello");

    }
}
