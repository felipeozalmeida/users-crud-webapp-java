<%-- 
    Document   : edit
    Created on : Oct 12, 2020, 11:24:05 AM
    Author     : felipeozalmeida
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="personUserTitle"
       value="${personUser.getPessoa().getNome()} x ${personUser.getUsuario().getLogin()}"/>
<t:page>
    <jsp:attribute name="header">
        <title>${! empty personUser ? personUserTitle : "New Person x User"} | Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <a href="PersonUserServlet">Back</a>
        <h1>${! empty personUser ? personUserTitle : "New Person x User"}</h1>
        <form class="needs-validation"
              action="${! empty personUser ? "PersonUserServlet?action=update" : "PersonUserServlet?action=store"}"
              method="POST" novalidate>
            <input name="id" type="hidden" value="${! empty personUser ? personUser.getId() : ""}">
            <div class="form-group">
                <label for="personId">Person</label>
                <select class="custom-select" id="personId" name="personId" required>
                    <option disabled ${empty personUser ? "selected" : ""} value>Select Person...</option>
                    <c:forEach var="person" items="${people}">
                        <option value="${person.getId()}"
                                ${! empty personUser and personUser.getIdPessoa() eq person.getId() ? "selected" : ""}>
                            ${person.getNome()}
                        </option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="userId">User</label>
                <select class="custom-select" id="userId" name="userId" required>
                    <option disabled ${empty personUser ? "selected" : ""} value>Select User...</option>
                    <c:forEach var="user" items="${users}">
                        <option value="${user.getId()}"
                                ${! empty personUser and personUser.getIdUsuario() eq user.getId() ? "selected" : ""}>
                            ${user.getLogin()}
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
