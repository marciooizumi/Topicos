/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mensagem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Márcio
 */
@WebServlet(name = "Mensagem", urlPatterns = {"/hello"})
public class Mensagem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = Persistence.createEntityManagerFactory("revisao4PU").createEntityManager();
        response.getWriter().println("<h1>Olá mundo</h1>");
        em.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
