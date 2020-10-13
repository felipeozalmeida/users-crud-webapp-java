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
      <title>Users | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="admin.jsp">Back</a>
        <div class="row align-items-center">
            <div class="col">
                <h1>Users</h1>
            </div>
            <div class="col-auto">
                <a class="btn btn-outline-success" href="UserServlet?action=create">Create</a>
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
                        <th scope="col">Login</th>
                        <th scope="col">Password</th>
                        <th scope="col">Status</th>
                        <th scope="col">Type</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usu" items="${usus}">
                        <tr>
                            <td>${usu.getId()}</td>
                            <td>${usu.getLogin()}</td>
                            <td>${usu.getSenha()}</td>
                            <td>${usu.getStatus()}</td>
                            <td>${usu.getTipo()}</td>
                            <td>
                                <a class="btn btn-outline-primary"
                                   href="UserServlet?action=edit&id=${usu.getId()}">Edit</a>
                                <form class="d-inline-block" action="UserServlet?action=delete&id=${usu.getId()}"
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
