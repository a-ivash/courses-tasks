<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/i18n.jsp"%>
    <%@include file="../includes/links.jsp"%>
    <title>
        <fmt:message key="adminDashboard.title" bundle="${labels}"/>
    </title>
</head>
<body>
<div class="container">
    <%@include file="/includes/header.jsp"%>
    <div class="col-md-6 col-md-offset-3">
        <div class="list-group">
            <a href="/services" class="list-group-item">
                <h4 class="list-group-item-heading">
                    <fmt:message key="adminDashboard.showServices" bundle="${labels}"/>
                </h4>
            </a>
            <a href="/subscribers" class="list-group-item">
                <h4 class="list-group-item-heading">
                    <fmt:message key="adminDashboard.showSubscribers" bundle="${labels}"/>
                </h4>
            </a>
            <a href="/payments" class="list-group-item">
                <h4 class="list-group-item-heading">
                    <fmt:message key="adminDashboard.showPayments" bundle="${labels}"/>
                </h4>
            </a>
        </div>
    </div>
</div>
</body>
</html>
