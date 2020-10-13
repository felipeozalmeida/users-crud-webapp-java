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
      <title>${! empty person ? person.getNome() : "New Person"} | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="PersonServlet">Back</a>
        <h1>${! empty person ? person.getNome() : "New Person"}</h1>
        <form class="needs-validation"
              action="${! empty person ? "PersonServlet?action=update" : "PersonServlet?action=store"}" method="POST"
              novalidate>
            <input name="id" type="hidden" value="${! empty person ? person.getId() : ""}">
            <div class="form-group">
                <label for="name">Name</label>
                <input class="form-control" id="name" name="name" type="text"
                       value="${! empty person ? person.getNome() : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="position">Position</label>
                <input class="form-control" id="position" name="position" type="text"
                       value="${! empty person ? person.getCargo() : ""}" required>
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
