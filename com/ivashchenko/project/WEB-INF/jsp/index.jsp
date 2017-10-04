<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="includes/links.jsp"%>
    <%@include file="includes/i18n.jsp" %>
    <title>
        <fmt:message key="indexPage.title" bundle="${labels}"/>
    </title>

</head>
<body>
<%@include file="/includes/header.jsp"%>
<div class="container">
    <div class="col-md-4 col-md-offset-4">

        <table style="width: 100%">
            <tr>
                <td>
                    <a href="public/login.jsp">
                        <button class="btn btn-info btn-block">
                            <fmt:message bundle="${labels}" key="indexPage.loginLink"/>
                        </button>
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="public/createAccount.jsp">
                        <button class="btn btn-light btn-block">
                            <fmt:message bundle="${labels}" key="indexPage.createAccountLink"/>
                        </button>
                    </a>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
