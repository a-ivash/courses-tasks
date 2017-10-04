<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../includes/i18n.jsp"%>
    <title>
        <fmt:message key="subscriberOrders.title" bundle="${labels}"/>
    </title>
    <jsp:include page="/includes/links.jsp"/>
    <jsp:useBean id="currentUser" scope="session" type="project.model.users.Subscriber"/>

    <jsp:useBean id="orders" type="java.util.List<project.model.orders.Order>" scope="request"/>
</head>
<body>
    <jsp:include page="/includes/header.jsp"/>
    <div class="container">
        <div class="col-md-8 col-md-offset-2">
            <table class="table table-responsive">
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="subscriberOrders.serviceName" bundle="${labels}"/></th>
                    <th><fmt:message key="subscriberOrders.price" bundle="${labels}"/></th>
                    <th><fmt:message key="subscriberOrders.orderDate" bundle="${labels}"/></th>
                    <th><fmt:message key="subscriberOrders.isPaid" bundle="${labels}"/></th>
                </tr>

                <c:if test="${empty orders}">
                    <tr>
                        <td colspan="5" align="center">
                            <b><fmt:message key="subscriberOrders.NOORDERS" bundle="${labels}"/></b>
                        </td>
                    </tr>
                </c:if>

                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.service.serviceName}</td>
                        <td>${order.price}</td>
                        <td>${order.orderDate}</td>
                        <td>
                            <b><c:if test="${empty order.payment}">-</c:if><c:if test="${not empty order.payment}">+</c:if></b>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
