/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felipeozalmeida.userscrudwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userscrudapi.controller.PessoaController;
import userscrudapi.model.bean.PessoaBean;

/**
 *
 * @author felipeozalmeida
 */
@WebServlet(name = "PersonServlet", urlPatterns = {"/PersonServlet"})
public class PersonServlet extends HttpServlet {

    private PessoaController controller = null;

    private void index(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        final ArrayList<PessoaBean> people = controller.lista();
        request.setAttribute("people", people);
        request.getRequestDispatcher("person/index.jsp").forward(request, response);
    }

    private void create(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("person/edit.jsp").forward(request, response);
    }

    private void store(final HttpServletRequest request, final HttpServletResponse response, final PessoaBean person)
            throws ServletException, IOException, SQLException {
        controller.inseri(person);
        final ArrayList<PessoaBean> people = controller.lista();
        request.setAttribute("people", people);
        request.setAttribute("success", "Person created successfully.");
        request.getRequestDispatcher("person/index.jsp").forward(request, response);
    }

    private void edit(final HttpServletRequest request, final HttpServletResponse response, final PessoaBean person)
            throws ServletException, IOException, SQLException {
        final PessoaBean personBusca = controller.busca(person);
        request.setAttribute("person", personBusca);
        request.getRequestDispatcher("person/edit.jsp").forward(request, response);
    }

    private void update(final HttpServletRequest request, final HttpServletResponse response, final PessoaBean person)
            throws ServletException, IOException, SQLException {
        controller.altera(person);
        final ArrayList<PessoaBean> people = controller.lista();
        request.setAttribute("people", people);
        request.setAttribute("success", "Person updated successfully.");
        request.getRequestDispatcher("person/index.jsp").forward(request, response);
    }

    private void delete(final HttpServletRequest request, final HttpServletResponse response, final PessoaBean person)
            throws ServletException, IOException, SQLException {
        controller.exclui(person);
        final ArrayList<PessoaBean> people = controller.lista();
        request.setAttribute("people", people);
        request.setAttribute("success", "Person deleted successfully.");
        request.getRequestDispatcher("person/index.jsp").forward(request, response);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            controller = new PessoaController();

            final String actionParam = request.getParameter("action");
            final String idParam = request.getParameter("id");
            final String nameParam = request.getParameter("name");
            final String positionParam = request.getParameter("position");

            if (request.getMethod().equals("POST") && actionParam != null) {
                if (actionParam.equals("delete") && idParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final PessoaBean person = new PessoaBean(id);
                    delete(request, response, person);
                }
                if (actionParam.equals("update") && idParam != null && nameParam != null && positionParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final PessoaBean person = new PessoaBean(id, nameParam, positionParam);
                    update(request, response, person);
                }
                if (actionParam.equals("store") && nameParam != null && positionParam != null) {
                    final PessoaBean person = new PessoaBean(0, nameParam, positionParam);
                    store(request, response, person);
                }
            }

            if (request.getMethod().equals("GET")) {
                if (actionParam != null) {
                    if (actionParam.equals("edit") && idParam != null) {
                        final int id = Integer.parseInt(idParam);
                        final PessoaBean person = new PessoaBean(id);
                        edit(request, response, person);
                    }
                    if (actionParam.equals("create")) {
                        create(request, response);
                    }
                } else {
                    index(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PersonServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet PersonServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet PersonServlet at " + request.getContextPath() + "</h1>");
                out.println("<p>" + ex + "</p>");
                out.println("</body>");
                out.println("</html>");
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
