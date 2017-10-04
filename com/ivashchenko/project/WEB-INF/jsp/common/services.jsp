<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/includes/links.jsp"%>
    <%@include file="/includes/i18n.jsp"%>
    <title>
        <fmt:message key="servicesPage.title" bundle="${labels}"/>
    </title>

    <jsp:useBean id="currentUser" type="project.model.users.AbstractUser" scope="session"/>
    <jsp:useBean id="services" type="java.util.List<project.model.services.Service>" scope="request"/>
</head>
<body>
<jsp:include page="/includes/header.jsp"/>
<c:if test="${currentUser.administrator}">
    <a href="/admin/createService.jsp">
        <fmt:message key="servicesPage.createService" bundle="${labels}"/>
    </a>
</c:if>

<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <table class="table table-responsive">
            <tr>
                <th>ID</th>
                <th><fmt:message key="servicesPage.serviceName" bundle="${labels}"/></th>
                <th><fmt:message key="servicesPage.servicePrice" bundle="${labels}"/></th>
                <th><fmt:message key="servicesPage.paymentType" bundle="${labels}"/></th>
                <th></th>
                <c:if test="${not currentUser.administrator}">
                    <th></th>
                </c:if>
            </tr>

            <c:if test="${empty services}">
                <tr>
                    <td colspan="5" align="center">
                        <b><fmt:message key="servicesPage.noServices" bundle="${labels}"/></b>
                    </td>
                </tr>
            </c:if>

            <c:forEach var="service" items="${services}">
                <tr>
                    <td>${service.id}</td>
                    <td>${service.serviceName}</td>
                    <td>${service.servicePrice}</td>
                    <td>${service.paymentType}</td>
                    <td>
                        <a href="/service?serviceId=${service.id}">
                            <button class="btn btn-info">
                                <fmt:message key="servicesPage.details" bundle="${labels}"/>
                            </button>
                        </a>
                    </td>
                    <c:if test="${not currentUser.administrator}">
                        <td>
                            <form action="/orderService" method="post">
                                    <%--<input type="hidden" name="action" value="order_service">--%>
                                <input type="hidden" name="serviceId" value="${service.id}">
                                <button type="submit">
                                    <fmt:message key="servicesPage.makeOrder" bundle="${labels}"/>
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
