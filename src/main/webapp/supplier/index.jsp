<%-- 
    Document   : users
    Created on : Oct 10, 2020, 8:43:31 PM
    Author     : felipeozalmeida
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page>
    <jsp:attribute name="header">
      <title>Supplier | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="admin.jsp">Back</a>
        <div class="row align-items-center">
            <div class="col">
                <h1>Suppliers</h1>
            </div>
            <div class="col-auto">
                <a class="btn btn-outline-success" href="SupplierServlet?action=create">Create</a>
            </div>
        </div>
        <c:if test="${! empty success}">
            <div class="alert alert-success" role="alert">
                ${success}
            </div>
        </c:if>
        <div class="table-responsive">
            <table class="table table-striped table-hoverable table-bordered">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">CNPJ</th>
                        <th scope="col">Address</th>
                        <th scope="col">Name</th>
                        <th scope="col">Contact ID</th>
                        <th scope="col">Contact Name</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="supplier" items="${suppliers}">
                        <tr>
                            <td>${supplier.getId()}</td>
                            <td>${supplier.getCnpj()}</td>
                            <td>${supplier.getEndereco()}</td>
                            <td>${supplier.getNome()}</td>
                            <td>${supplier.getIdPessoa()}</td>
                            <td>${supplier.getPessoa().getNome()}</td>
                            <td>
                                <a class="btn btn-outline-primary m-1"
                                   href="SupplierServlet?action=edit&id=${supplier.getId()}">Edit</a>
                                <form class="d-inline-block m-1"
                                      action="SupplierServlet?action=delete&id=${supplier.getId()}" method="POST">
                                    <button class="btn btn-outline-danger" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:page>
