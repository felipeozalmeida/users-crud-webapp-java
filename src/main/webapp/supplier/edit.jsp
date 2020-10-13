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
      <title>${! empty supplier ? supplier.getNome() : "New Supplier"} | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="SupplierServlet">Back</a>
        <h1>${! empty supplier ? supplier.getNome() : "New Supplier"}</h1>
        <form class="needs-validation"
              action="${! empty supplier ? "SupplierServlet?action=update" : "SupplierServlet?action=store"}"
              method="POST" novalidate>
            <input name="id" type="hidden" value="${! empty supplier ? supplier.getId() : ""}">
            <div class="form-group">
                <label for="personId">Person</label>
                <select class="custom-select" id="personId" name="personId" required>
                    <option disabled ${empty supplier ? "selected" : ""} value>Select Person...</option>
                    <c:forEach var="person" items="${people}">
                        <option value="${person.getId()}" ${! empty supplier and supplier.getIdPessoa() eq person.getId() ? "selected" : ""}>
                            ${person.getNome()}
                        </option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="cnpj">CNPJ</label>
                <input class="form-control" id="cnpj" name="cnpj" type="text"
                       value="${! empty supplier ? supplier.getCnpj() : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input class="form-control" id="address" name="address" type="text"
                       value="${! empty supplier ? supplier.getEndereco() : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input class="form-control" id="name" name="name" type="text"
                       value="${! empty supplier ? supplier.getNome() : ""}" required>
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
