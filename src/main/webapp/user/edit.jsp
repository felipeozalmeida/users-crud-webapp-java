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
      <title>${! empty usu ? usu.getLogin() : "New User"} | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="UserServlet">Back</a>
        <h1>${! empty usu ? usu.getLogin() : "New User"}</h1>
        <form class="needs-validation"
              action="${! empty usu ? "UserServlet?action=update" : "UserServlet?action=store"}" method="POST"
              novalidate>
            <input name="id" type="hidden" value="${! empty usu ? usu.getId() : ""}">
            <div class="form-group">
                <label for="login">Login</label>
                <input class="form-control" id="login" name="login" type="text"
                       value="${! empty usu ? usu.getLogin() : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input class="form-control" id="password" name="password" type="password"
                       value="${! empty usu ? usu.getSenha() : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <select class="custom-select" id="status" name="status" required>
                    <option disabled ${empty usu ? "selected" : ""} value>Select status...</option>
                    <c:forEach var="status" items="${statuses}">
                        <option ${! empty usu and usu.getStatus() eq status ? "selected" : ""}>${status}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="type">Type</label>
                <select class="custom-select" id="type" name="type" required>
                    <option disabled ${empty usu ? "selected" : ""} value>Select type...</option>
                    <c:forEach var="type" items="${types}">
                        <option ${! empty usu and usu.getTipo() eq type ? "selected" : ""}>${type}</option>
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
