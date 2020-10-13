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

import userscrudapi.controller.ProdutoController;
import userscrudapi.model.bean.ProdutoBean;

/**
 *
 * @author felipeozalmeida
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    private ProdutoController controller = null;

    private void index(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        final ArrayList<ProdutoBean> products = controller.lista();
        request.setAttribute("products", products);
        request.getRequestDispatcher("product/index.jsp").forward(request, response);
    }

    private void create(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("product/edit.jsp").forward(request, response);
    }

    private void store(final HttpServletRequest request, final HttpServletResponse response, final ProdutoBean product)
            throws ServletException, IOException, SQLException {
        controller.inseri(product);
        final ArrayList<ProdutoBean> products = controller.lista();
        request.setAttribute("products", products);
        request.setAttribute("success", "Product created successfully.");
        request.getRequestDispatcher("product/index.jsp").forward(request, response);
    }

    private void edit(final HttpServletRequest request, final HttpServletResponse response, final ProdutoBean product)
            throws ServletException, IOException, SQLException {
        final ProdutoBean productBusca = controller.busca(product);
        request.setAttribute("product", productBusca);
        request.getRequestDispatcher("product/edit.jsp").forward(request, response);
    }

    private void update(final HttpServletRequest request, final HttpServletResponse response, final ProdutoBean product)
            throws ServletException, IOException, SQLException {
        controller.altera(product);
        final ArrayList<ProdutoBean> products = controller.lista();
        request.setAttribute("products", products);
        request.setAttribute("success", "Product updated successfully.");
        request.getRequestDispatcher("product/index.jsp").forward(request, response);
    }

    private void delete(final HttpServletRequest request, final HttpServletResponse response, final ProdutoBean product)
            throws ServletException, IOException, SQLException {
        controller.exclui(product);
        final ArrayList<ProdutoBean> products = controller.lista();
        request.setAttribute("products", products);
        request.setAttribute("success", "Product deleted successfully.");
        request.getRequestDispatcher("product/index.jsp").forward(request, response);
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
            controller = new ProdutoController();

            final String actionParam = request.getParameter("action");
            final String idParam = request.getParameter("id");
            final String codeParam = request.getParameter("code");
            final String nameParam = request.getParameter("name");
            final String valueParam = request.getParameter("value");
            final String quantityParam = request.getParameter("quantity");

            if (request.getMethod().equals("POST") && actionParam != null) {
                if (actionParam.equals("delete") && idParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final ProdutoBean product = new ProdutoBean(id);
                    delete(request, response, product);
                }
                if (actionParam.equals("update") && idParam != null && codeParam != null && nameParam != null
                        && valueParam != null && quantityParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final int value = Integer.parseInt(valueParam);
                    final int quantity = Integer.parseInt(quantityParam);
                    final ProdutoBean product = new ProdutoBean(id, codeParam, nameParam, value, quantity);
                    update(request, response, product);
                }
                if (actionParam.equals("store") && codeParam != null && nameParam != null && valueParam != null) {
                    final int value = Integer.parseInt(valueParam);
                    final int quantity = Integer.parseInt(quantityParam);
                    final ProdutoBean product = new ProdutoBean(0, codeParam, nameParam, value, quantity);
                    store(request, response, product);
                }
            }

            if (request.getMethod().equals("GET")) {
                if (actionParam != null) {
                    if (actionParam.equals("edit") && idParam != null) {
                        final int id = Integer.parseInt(idParam);
                        final ProdutoBean product = new ProdutoBean(id);
                        edit(request, response, product);
                    }
                    if (actionParam.equals("create")) {
                        create(request, response);
                    }
                } else {
                    index(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
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
