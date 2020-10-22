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

import userscrudapi.controller.UsuarioController;
import userscrudapi.model.bean.UsuarioBean;

/**
 *
 * @author felipeozalmeida
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private static final String[] STATUSES = {"ATIVO", "DESATIVADO"};
    private static final String[] TYPES = {"ADM", "PADRÃO"};
    private UsuarioController controller = null;

    private void index(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        final ArrayList<UsuarioBean> usus = controller.lista();
        request.setAttribute("usus", usus);
        request.getRequestDispatcher("user/index.jsp").forward(request, response);
    }

    private void create(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("statuses", STATUSES);
        request.setAttribute("types", TYPES);
        request.getRequestDispatcher("user/edit.jsp").forward(request, response);
    }

    private void store(final HttpServletRequest request, final HttpServletResponse response, final UsuarioBean usu)
            throws ServletException, IOException, SQLException {
        controller.inseri(usu);
        final ArrayList<UsuarioBean> usus = controller.lista();
        request.setAttribute("usus", usus);
        request.setAttribute("success", "User created successfully.");
        request.getRequestDispatcher("user/index.jsp").forward(request, response);
    }

    private void edit(final HttpServletRequest request, final HttpServletResponse response, final UsuarioBean usu)
            throws ServletException, IOException, SQLException {
        final UsuarioBean usuBusca = controller.busca(usu);
        request.setAttribute("usu", usuBusca);
        request.setAttribute("statuses", STATUSES);
        request.setAttribute("types", TYPES);
        request.getRequestDispatcher("user/edit.jsp").forward(request, response);
    }

    private void update(final HttpServletRequest request, final HttpServletResponse response, final UsuarioBean usu)
            throws ServletException, IOException, SQLException {
        controller.altera(usu);
        final ArrayList<UsuarioBean> usus = controller.lista();
        request.setAttribute("usus", usus);
        request.setAttribute("success", "User updated successfully.");
        request.getRequestDispatcher("user/index.jsp").forward(request, response);
    }

    private void delete(final HttpServletRequest request, final HttpServletResponse response, final UsuarioBean usu)
            throws ServletException, IOException, SQLException {
        controller.exclui(usu);
        final ArrayList<UsuarioBean> usus = controller.lista();
        request.setAttribute("usus", usus);
        request.setAttribute("success", "User deleted successfully.");
        request.getRequestDispatcher("user/index.jsp").forward(request, response);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            controller = new UsuarioController();

            final String actionParam = request.getParameter("action");
            final String idParam = request.getParameter("id");
            final String loginParam = request.getParameter("login");
            final String passwordParam = request.getParameter("password");
            final String statusParam = request.getParameter("status");
            final String typeParam = request.getParameter("type");

            if (request.getMethod().equals("POST") && actionParam != null) {
                if (actionParam.equals("delete") && idParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final UsuarioBean usu = new UsuarioBean(id);
                    delete(request, response, usu);
                }
                if (actionParam.equals("update") && idParam != null && loginParam != null && passwordParam != null
                        && statusParam != null && typeParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final UsuarioBean usu = new UsuarioBean(id, loginParam, passwordParam, statusParam, typeParam);
                    update(request, response, usu);
                }
                if (actionParam.equals("store") && loginParam != null && passwordParam != null && statusParam != null
                        && typeParam != null) {
                    final UsuarioBean usu = new UsuarioBean(0, loginParam, passwordParam, statusParam, typeParam);
                    store(request, response, usu);
                }
            }

            if (request.getMethod().equals("GET")) {
                if (actionParam != null) {
                    if (actionParam.equals("edit") && idParam != null) {
                        final int id = Integer.parseInt(idParam);
                        final UsuarioBean usu = new UsuarioBean(id);
                        edit(request, response, usu);
                    }
                    if (actionParam.equals("create")) {
                        create(request, response);
                    }
                } else {
                    index(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet UserServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
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
