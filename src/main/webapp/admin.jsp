<%-- 
    Document   : admin
    Created on : Oct 10, 2020, 8:04:34 PM
    Author     : felipeozalmeida
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page>
    <jsp:attribute name="header">
      <title>Admin | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <h1 class="text-center">Admin</h1>
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="list-group">
                    <a class="list-group-item list-group-item-action" href="UserServlet">Users</a>
                    <a class="list-group-item list-group-item-action" href="PersonServlet">People</a>
                    <a class="list-group-item list-group-item-action" href="ProductServlet">Products</a>
                    <a class="list-group-item list-group-item-action" href="SupplierServlet">Suppliers</a>
                    <a class="list-group-item list-group-item-action" href="PersonUserServlet">People x Users</a>
                    <a class="list-group-item list-group-item-action" href="ProductSupplierServlet">
                        Products X Suppliers
                    </a>
                </div>
            </div>
        </div>
    </jsp:body>
</t:page>
