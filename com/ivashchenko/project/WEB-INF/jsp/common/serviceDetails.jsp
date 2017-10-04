<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/includes/links.jsp"%>
    <%@include file="/includes/i18n.jsp"%>
    <jsp:useBean id="service" class="project.model.services.Service" scope="request"/>
    <title>
        <fmt:message key="serviceDetailsPage.title" bundle="${labels}"/> ${service.serviceName}
    </title>
</head>
<body>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <h3> <fmt:message key="serviceDetailsPage.serviceName" bundle="${labels}"/>: ${service.serviceName}</h3>
        <h3><fmt:message key="serviceDetailsPage.serviceDescription" bundle="${labels}"/></h3>
        <p>${service.serviceDescription}</p>
        <h3><fmt:message key="serviceDetailsPage.servicePrice" bundle="${labels}"/>: ${service.servicePrice}</h3>
        <h3><fmt:message key="serviceDetailsPage.servicePaymentType" bundle="${labels}"/>: ${service.paymentType}</h3>

        <c:if test="${not currentUser.isAdministrator()}">
            <form action="/orderService" method="post">
                <input type="hidden" name="serviceId" value="${service.id}">
                <button type="submit">
                    <fmt:message key="serviceDetailsPage.makeOrder" bundle="${labels}"/>
                </button>
            </form>
        </c:if>
        <a href="/services"><fmt:message key="serviceDetailsPage.goBack" bundle="${labels}"/></a>
    </div>
</div>

    <%--<c:if test="${currentUser.isAdministrator()}">--%>
        <%--<a href="#">--%>
            <%--<input type="button" value="Update">--%>
        <%--</a>--%>
        <%--<a href="#">--%>
            <%--<input type="button" value="Delete">--%>
        <%--</a>--%>
    <%--</c:if>--%>


</body>
</html>
