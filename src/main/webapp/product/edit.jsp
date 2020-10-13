<%-- 
    Document   : edit
    Created on : Oct 12, 2020, 11:24:05 AM
    Author     : felipeozalmeida
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page>
    <jsp:attribute name="header">
      <title>${! empty product ? product.getNome() : "New Product"} | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="ProductServlet">Back</a>
        <h1>${! empty product ? product.getNome() : "New Product"}</h1>
        <form class="needs-validation"
              action="${! empty product ? "ProductServlet?action=update" : "ProductServlet?action=store"}" method="POST"
              novalidate>
            <input name="id" type="hidden" value="${! empty product ? product.getId() : ""}">
            <div class="form-group">
                <label for="code">Code</label>
                <input class="form-control" id="code" name="code" type="text"
                       value="${! empty product ? product.getCod() : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input class="form-control" id="name" name="name" type="text"
                       value="${! empty product ? product.getNome() : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="value">Value</label>
                <input class="form-control" id="value" name="value" type="number"
                       value="${! empty product ? product.getValor() : ""}" min="0" step="0.01" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input class="form-control" id="quantity" name="quantity" type="number"
                       value="${! empty product ? product.getQuant() : ""}" min="0" step="1" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-row justify-content-end">
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </form>
        </div>
    </jsp:body>
</t:page>
