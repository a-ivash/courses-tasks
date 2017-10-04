<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/i18n.jsp"%>
    <title><fmt:message key="registrationConfirmPage.title" bundle="${labels}"/></title>
    <h1>
        <fmt:message key="registrationConfirmPage.message" bundle="${labels}"/>
    </h1>
    <h2><fmt:message key="registrationConfirmPage.yourEmail" bundle="${labels}"/>: ${currentUser.email}</h2>
    <h2><fmt:message key="registrationConfirmPage.yourPassword" bundle="${labels}"/>: ${generatedPassword}</h2>
    <h3>
        <a href="/subscriber/dashboard.jsp"><fmt:message key="registrationConfirmPage.goToDashboard" bundle="${labels}"/></a>
    </h3>
</head>
<body>

</body>
</html>
