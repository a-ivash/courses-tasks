<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:useBean id="currentUser" type="project.model.users.Subscriber" scope="session"/>
    <%@include file="/includes/links.jsp"%>
</head>
<body>
<div class="container">
    <%@include file="/includes/header.jsp"%>
    <div class="col-md-6 col-md-offset-3">
        <h1><fmt:message key="subscriberDashboard.welcome" bundle="${labels}"/>, ${currentUser.firstName} ${currentUser.lastName}</h1>

        <h1>
            ${BLOCKED_USER_MESSAGE}
        </h1>
        <h1>
            ${INACTIVE_USER_MESSAGE}
        </h1>
        <div class="list-group">
            <a href="/services" class="list-group-item">
                <h4 class="list-group-item-heading">
                    <fmt:message key="subscriberDashboard.services" bundle="${labels}"/>
                </h4>
            </a>
            <a href="/orders" class="list-group-item">
                <h4 class="list-group-item-heading">
                    <fmt:message key="subscriberDashboard.myorders" bundle="${labels}"/>
                </h4>
            </a>
            <a href="/payments" class="list-group-item">
                <h4 class="list-group-item-heading">
                    <fmt:message key="subscriberDashboard.mypayments" bundle="${labels}"/>
                </h4>
            </a>
        </div>
    </div>
</div>
</body>
</html>
