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

import userscrudapi.controller.FornecedorController;
import userscrudapi.controller.FornecedorProdutoController;
import userscrudapi.controller.ProdutoController;
import userscrudapi.model.bean.FornecedorBean;
import userscrudapi.model.bean.FornecedorProdutoBean;
import userscrudapi.model.bean.ProdutoBean;

/**
 *
 * @author felipeozalmeida
 */
@WebServlet(name = "ProductSupplierServlet", urlPatterns = {"/ProductSupplierServlet"})
public class ProductSupplierServlet extends HttpServlet {

    private FornecedorProdutoController controller = null;
    private ProdutoController pController = null;
    private FornecedorController sController = null;

    private void index(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        final ArrayList<FornecedorProdutoBean> productsSuppliers = controller.lista();
        request.setAttribute("productsSuppliers", productsSuppliers);
        request.getRequestDispatcher("product-supplier/index.jsp").forward(request, response);
    }

    private void create(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        final ArrayList<ProdutoBean> products = pController.lista();
        final ArrayList<FornecedorBean> suppliers = sController.lista();
        request.setAttribute("products", products);
        request.setAttribute("suppliers", suppliers);
        request.getRequestDispatcher("product-supplier/edit.jsp").forward(request, response);
    }

    private void store(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorProdutoBean productSupplier) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        controller.inseri(productSupplier);
        final ArrayList<FornecedorProdutoBean> productsSuppliers = controller.lista();
        request.setAttribute("productsSuppliers", productsSuppliers);
        request.setAttribute("success", "Product x Supplier created successfully.");
        request.getRequestDispatcher("product-supplier/index.jsp").forward(request, response);
    }

    private void edit(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorProdutoBean productSupplier) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        final FornecedorProdutoBean productSupplierBusca = controller.busca(productSupplier);
        final ArrayList<ProdutoBean> products = pController.lista();
        final ArrayList<FornecedorBean> suppliers = sController.lista();
        request.setAttribute("products", products);
        request.setAttribute("suppliers", suppliers);
        request.setAttribute("productSupplier", productSupplierBusca);
        request.getRequestDispatcher("product-supplier/edit.jsp").forward(request, response);
    }

    private void update(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorProdutoBean productSupplier) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        controller.altera(productSupplier);
        final ArrayList<FornecedorProdutoBean> productsSuppliers = controller.lista();
        request.setAttribute("productsSuppliers", productsSuppliers);
        request.setAttribute("success", "Product x Supplier updated successfully.");
        request.getRequestDispatcher("product-supplier/index.jsp").forward(request, response);
    }

    private void delete(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorProdutoBean productSupplier) throws ServletException, IOException, SQLException,
            ClassNotFoundException {
        controller.exclui(productSupplier);
        final ArrayList<FornecedorProdutoBean> productsSuppliers = controller.lista();
        request.setAttribute("productsSuppliers", productsSuppliers);
        request.setAttribute("success", "Product x Supplier deleted successfully.");
        request.getRequestDispatcher("product-supplier/index.jsp").forward(request, response);
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
            controller = new FornecedorProdutoController();
            pController = new ProdutoController();
            sController = new FornecedorController();

            final String actionParam = request.getParameter("action");
            final String idParam = request.getParameter("id");
            final String productIdParam = request.getParameter("productId");
            final String supplierIdParam = request.getParameter("supplierId");

            if (request.getMethod().equals("POST") && actionParam != null) {
                if (actionParam.equals("delete") && idParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final FornecedorProdutoBean productSupplier = new FornecedorProdutoBean(id);
                    delete(request, response, productSupplier);
                }
                if (actionParam.equals("update") && idParam != null && productIdParam != null
                        && supplierIdParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final int productId = Integer.parseInt(productIdParam);
                    final int supplierId = Integer.parseInt(supplierIdParam);
                    final FornecedorProdutoBean productSupplier = new FornecedorProdutoBean(id, supplierId, productId);
                    update(request, response, productSupplier);
                }
                if (actionParam.equals("store") && productIdParam != null && supplierIdParam != null) {
                    final int productId = Integer.parseInt(productIdParam);
                    final int supplierId = Integer.parseInt(supplierIdParam);
                    final FornecedorProdutoBean productSupplier = new FornecedorProdutoBean(0, supplierId, productId);
                    store(request, response, productSupplier);
                }
            }

            if (request.getMethod().equals("GET")) {
                if (actionParam != null) {
                    if (actionParam.equals("edit") && idParam != null) {
                        final int id = Integer.parseInt(idParam);
                        final FornecedorProdutoBean productSupplier = new FornecedorProdutoBean(id);
                        edit(request, response, productSupplier);
                    }
                    if (actionParam.equals("create")) {
                        create(request, response);
                    }
                } else {
                    index(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductSupplierServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet ProductSupplierServlet at " + request.getContextPath() + "</h1>");
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
