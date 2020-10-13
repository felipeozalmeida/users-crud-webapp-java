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
import userscrudapi.controller.PessoaController;
import userscrudapi.model.bean.FornecedorBean;
import userscrudapi.model.bean.PessoaBean;

/**
 *
 * @author felipeozalmeida
 */
@WebServlet(name = "SupplierServlet", urlPatterns = {"/SupplierServlet"})
public class SupplierServlet extends HttpServlet {

    private FornecedorController fController = null;
    private PessoaController pController = null;

    private void index(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        final ArrayList<FornecedorBean> suppliers = fController.lista();
        request.setAttribute("suppliers", suppliers);
        request.getRequestDispatcher("supplier/index.jsp").forward(request, response);
    }

    private void create(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        final ArrayList<PessoaBean> people = pController.lista();
        request.setAttribute("people", people);
        request.getRequestDispatcher("supplier/edit.jsp").forward(request, response);
    }

    private void store(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorBean supplier) throws ServletException, IOException, SQLException, ClassNotFoundException {
        fController.inseri(supplier);
        final ArrayList<FornecedorBean> suppliers = fController.lista();
        request.setAttribute("suppliers", suppliers);
        request.setAttribute("success", "Supplier created successfully.");
        request.getRequestDispatcher("supplier/index.jsp").forward(request, response);
    }

    private void edit(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorBean supplier) throws ServletException, IOException, SQLException, ClassNotFoundException {
        final FornecedorBean supplierBusca = fController.busca(supplier);
        final ArrayList<PessoaBean> people = pController.lista();
        request.setAttribute("people", people);
        request.setAttribute("supplier", supplierBusca);
        request.getRequestDispatcher("supplier/edit.jsp").forward(request, response);
    }

    private void update(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorBean supplier) throws ServletException, IOException, SQLException, ClassNotFoundException {
        fController.altera(supplier);
        final ArrayList<FornecedorBean> suppliers = fController.lista();
        request.setAttribute("suppliers", suppliers);
        request.setAttribute("success", "Supplier updated successfully.");
        request.getRequestDispatcher("supplier/index.jsp").forward(request, response);
    }

    private void delete(final HttpServletRequest request, final HttpServletResponse response,
            final FornecedorBean supplier) throws ServletException, IOException, SQLException, ClassNotFoundException {
        fController.exclui(supplier);
        final ArrayList<FornecedorBean> suppliers = fController.lista();
        request.setAttribute("suppliers", suppliers);
        request.setAttribute("success", "Supplier deleted successfully.");
        request.getRequestDispatcher("supplier/index.jsp").forward(request, response);
    }

    /**
     * Processes requests for both HTTP <personId>GET</personId> and <personId>POST</personId> methods.
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
            fController = new FornecedorController();
            pController = new PessoaController();

            final String actionParam = request.getParameter("action");
            final String idParam = request.getParameter("id");
            final String personIdParam = request.getParameter("personId");
            final String cnpjParam = request.getParameter("cnpj");
            final String addressParam = request.getParameter("address");
            final String nameParam = request.getParameter("name");

            if (request.getMethod().equals("POST") && actionParam != null) {
                if (actionParam.equals("delete") && idParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final FornecedorBean supplier = new FornecedorBean(id);
                    delete(request, response, supplier);
                }
                if (actionParam.equals("update") && idParam != null && personIdParam != null && cnpjParam != null
                        && addressParam != null && nameParam != null) {
                    final int id = Integer.parseInt(idParam);
                    final int personId = Integer.parseInt(personIdParam);
                    final FornecedorBean supplier = new FornecedorBean(
                            id, personId, cnpjParam, addressParam, nameParam);
                    update(request, response, supplier);
                }
                if (actionParam.equals("store") && personIdParam != null && cnpjParam != null && addressParam != null
                        && nameParam != null) {
                    final int personId = Integer.parseInt(personIdParam);
                    final FornecedorBean supplier = new FornecedorBean(0, personId, cnpjParam, addressParam, nameParam);
                    store(request, response, supplier);
                }
            }

            if (request.getMethod().equals("GET")) {
                if (actionParam != null) {
                    if (actionParam.equals("edit") && idParam != null) {
                        final int id = Integer.parseInt(idParam);
                        final FornecedorBean supplier = new FornecedorBean(id);
                        edit(request, response, supplier);
                    }
                    if (actionParam.equals("create")) {
                        create(request, response);
                    }
                } else {
                    index(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SupplierServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet SupplierServlet at " + request.getContextPath() + "</h1>");
                out.println("<p>" + ex + "</p>");
                out.println("</body>");
                out.println("</html>");
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the personId.">
    /**
     * Handles the HTTP <personId>GET</personId> method.
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
     * Handles the HTTP <personId>POST</personId> method.
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
