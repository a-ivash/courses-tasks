<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/i18n.jsp" %>
    <%@include file="/includes/links.jsp"%>
    <title>
        <fmt:message key="loginPage.title" bundle="${labels}"/>
    </title>

</head>
<body>
<%@include file="../includes/header.jsp"%>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <form action="/login" method="post">
            <fmt:message bundle="${labels}" key="loginPage.email"/>:
            <input type="email" name="userEmail" class="form-control" required/> <br/>

            <fmt:message bundle="${labels}" key="loginPage.password"/>:
            <input type="password" name="userPassword" class="form-control" required/> <br/>

            ${wrongEmailPasswordMessage}

            <button type="submit" class="btn btn-info btn-block">
                <fmt:message bundle="${labels}" key="loginPage.loginButton"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>
