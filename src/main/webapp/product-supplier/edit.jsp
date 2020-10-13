<%-- 
    Document   : edit
    Created on : Oct 12, 2020, 11:24:05 AM
    Author     : felipeozalmeida
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="productSupplierTitle"
       value="${productSupplier.getFornecedor().getNome()} x ${productSupplier.getProduto().getNome()}"/>
<t:page>
    <jsp:attribute name="header">
        <title>${! empty productSupplier ? productSupplierTitle : "New Product x Supplier"} | Products Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="ProductSupplierServlet">Back</a>
        <h1>${! empty productSupplier ? productSupplierTitle : "New Product x Supplier"}</h1>
        <form class="needs-validation"
              action="${! empty productSupplier
                        ? "ProductSupplierServlet?action=update"
                        : "ProductSupplierServlet?action=store"}"
              method="POST" novalidate>
            <input name="id" type="hidden" value="${! empty productSupplier ? productSupplier.getId() : ""}">
            <div class="form-group">
                <label for="supplierId">Supplier</label>
                <select class="custom-select" id="supplierId" name="supplierId" required>
                    <option disabled ${empty productSupplier ? "selected" : ""} value>Select Supplier...</option>
                    <c:forEach var="supplier" items="${suppliers}">
                        <option value="${supplier.getId()}"
                                ${! empty productSupplier and productSupplier.getIdFornecedor() eq supplier.getId()
                                  ? "selected"
                                  : ""}>
                            ${supplier.getNome()}
                        </option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="productId">Product</label>
                <select class="custom-select" id="productId" name="productId" required>
                    <option disabled ${empty productSupplier ? "selected" : ""} value>Select Product...</option>
                    <c:forEach var="product" items="${products}">
                        <option value="${product.getId()}"
                                ${! empty productSupplier and productSupplier.getIdProduto() eq product.getId()
                                  ? "selected"
                                  : ""}>
                            ${product.getNome()}
                        </option>
                    </c:forEach>
                </select>
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
