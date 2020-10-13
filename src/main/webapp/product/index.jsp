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
      <title>Product | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="admin.jsp">Back</a>
        <div class="row align-items-center">
            <div class="col">
                <h1>Products</h1>
            </div>
            <div class="col-auto">
                <a class="btn btn-outline-success" href="ProductServlet?action=create">Create</a>
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
                        <th scope="col">Code</th>
                        <th scope="col">Name</th>
                        <th scope="col">Value</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.getId()}</td>
                            <td>${product.getCod()}</td>
                            <td>${product.getNome()}</td>
                            <td>${product.getValor()}</td>
                            <td>${product.getQuant()}</td>
                            <td>
                                <a class="btn btn-outline-primary"
                                   href="ProductServlet?action=edit&id=${product.getId()}">Edit</a>
                                <form class="d-inline-block" action="ProductServlet?action=delete&id=${product.getId()}"
                                      method="POST">
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
