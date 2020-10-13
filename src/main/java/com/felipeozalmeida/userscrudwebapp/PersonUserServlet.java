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
import userscrudapi.controller.PessoaUsuarioController;
import userscrudapi.controller.UsuarioController;
import userscrudapi.model.bean.PessoaBean;
import userscrudapi.model.bean.PessoaUsuarioBean;
import userscrudapi.model.bean.UsuarioBean;

/**
 *
 * @author felipeozalmeida
 */
@WebServlet(name = "PersonUserServlet", urlPatterns = {"/PersonUserServlet"})
public class PersonUserServlet extends HttpServlet {

    private PessoaUsuarioController controller = null;
    private PessoaController pController = null;
    private UsuarioController uController = null;

    private void index(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        final ArrayList<PessoaUsuarioBean> peopleUsers = controller.lista();
        request.setAttribute("peopleUsers", peopleUsers);
        request.getRequestDispatcher("person-user/index.jsp").forward(request, response);
    }

    private void create(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        final ArrayList<PessoaBean> people = pController.lista();
        final ArrayList<UsuarioBean> users = uController.lista();
        request.setAttribute("people", people);
        request.setAttribute("users", users);
        request.getRequestDispatcher("person-user/edit.jsp").forward(request, response);
    }

    private void store(final HttpServletRequest request, final HttpServletResponse response,
            final PessoaUsuarioBean personUser) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        controller.inseri(personUser);
        final ArrayList<PessoaUsuarioBean> peopleUsers = controller.lista();
        request.setAttribute("peopleUsers", peopleUsers);
        request.setAttribute("success", "Person x User created successfully.");
        request.getRequestDispatcher("person-user/index.jsp").forward(request, response);
    }

    private void edit(final HttpServletRequest request, final HttpServletResponse response,
            final PessoaUsuarioBean personUser) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        final PessoaUsuarioBean personUserBusca = controller.busca(personUser);
        final ArrayList<PessoaBean> people = pController.lista();
        final ArrayList<UsuarioBean> users = uController.lista();
        request.setAttribute("people", people);
        request.setAttribute("users", users);
        request.setAttribute("personUser", personUserBusca);
        request.getRequestDispatcher("person-user/edit.jsp").forward(request, response);
    }

    private void update(final HttpServletRequest request, final HttpServletResponse response,
            final PessoaUsuarioBean personUser) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        controller.altera(personUser);
        final ArrayList<PessoaUsuarioBean> peopleUsers = controller.lista();
        request.setAttribute("peopleUsers", peopleUsers);
        request.setAttribute("success", "Person x User updated successfully.");
        request.getRequestDispatcher("person-user/index.jsp").forward(request, response);
    }

    private void delete(final HttpServletRequest request, final HttpServletResponse response,
            final PessoaUsuarioBean personUser) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        controller.exclui(personUser);
        final ArrayList<PessoaUsuarioBean> peopleUsers = controller.lista();
        request.setAttribute("peopleUsers", peopleUsers);
        request.setAttribute("success", "Person x User deleted successfully.");
        request.getRequestDispatcher("person-user/index.jsp").forward(request, response);
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
            controller = new PessoaUsuarioController();
            pController = new PessoaController();
            uController = new UsuarioController();

            final String actionParam = request.getParameter("action");
            final String idParam = request.getParameter("id");
            final String personIdParam = request.getParameter("personId");
            final String userIdParam = request.getParameter("userId");

            if (request.getMethod().equals("POST") && actionParam != null) {
                if (actionParam.equals("delete") && idParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final PessoaUsuarioBean personUser = new PessoaUsuarioBean(id);
                    delete(request, response, personUser);
                }
                if (actionParam.equals("update") && idParam != null && personIdParam != null
                        && userIdParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final int personId = Integer.parseInt(personIdParam);
                    final int userId = Integer.parseInt(userIdParam);
                    final PessoaUsuarioBean personUser = new PessoaUsuarioBean(id, personId, userId);
                    update(request, response, personUser);
                }
                if (actionParam.equals("store") && personIdParam != null && userIdParam != null) {
                    final int personId = Integer.parseInt(personIdParam);
                    final int userId = Integer.parseInt(userIdParam);
                    final PessoaUsuarioBean personUser = new PessoaUsuarioBean(0, personId, userId);
                    store(request, response, personUser);
                }
            }

            if (request.getMethod().equals("GET")) {
                if (actionParam != null) {
                    if (actionParam.equals("edit") && idParam != null) {
                        final int id = Integer.parseInt(idParam);
                        final PessoaUsuarioBean personUser = new PessoaUsuarioBean(id);
                        edit(request, response, personUser);
                    }
                    if (actionParam.equals("create")) {
                        create(request, response);
                    }
                } else {
                    index(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PersonUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet PersonUserServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet PersonUserServlet at " + request.getContextPath() + "</h1>");
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
