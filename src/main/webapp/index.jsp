<%-- 
    Document   : index
    Created on : Oct 10, 2020, 5:01:34 PM
    Author     : felipeozalmeida
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:page>
    <jsp:attribute name="header">
        <title>Users Crud Webapp</title>
    </jsp:attribute>
    <jsp:body>
        <h1>Users Crud Webapp</h1>
        <form class="needs-validation" action="LoginServlet" method="POST" novalidate>
            <div class="form-group">
                <label for="login">Login</label>
                <input class="form-control" id="login" name="login" type="text"
                       value="${! empty login ? login : ""}" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input class="form-control" id="password" name="password" type="password"
                       value="${! empty password ? password : "" }" required>
                <div class="invalid-feedback">Required.</div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <c:if test="${! empty alert}">
            <div class="alert alert-danger my-3" role="alert">
                ${alert}
            </div> 
        </c:if>
        <c:if test="${! empty success}">
            <div class="alert alert-success my-3" role="alert">
                ${success}
            </div> 
        </c:if>
    </jsp:body>
</t:page>
