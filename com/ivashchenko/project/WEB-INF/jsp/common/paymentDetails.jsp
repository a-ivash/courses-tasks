<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../includes/i18n.jsp"%>
    <%@include file="../includes/links.jsp"%>
    <title>
        <fmt:message key="paymentDetailsPage.title" bundle="${labels}"/>
    </title>
    <jsp:useBean id="payment" class="project.model.orders.Payment" scope="request"/>
    <jsp:useBean id="currentUser" type="project.model.users.AbstractUser" scope="session"/>
    <c:set var="subscriberView" value="${not currentUser.administrator}"/>
</head>
<body>
<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <h2>
            <fmt:message key="paymentDetailsPage.paymentNo" bundle="${labels}"/> ${payment.id}
        </h2>
        <h3>
            <fmt:message key="paymentDetailsPage.totalCost" bundle="${labels}"/> ${totalCost}
        </h3>

        <c:if test="${subscriberView and payment.completed == false}">
            <form action="/confirmPayment" method="post">
                <input type="hidden" name="paymentId" value="${payment.id}">
                <button type="submit" class="btn btn-success">
                    <fmt:message key="paymentDetailsPage.confirmPayment" bundle="${labels}"/>
                </button>
            </form>
        </c:if>


        <h3>
            <fmt:message key="paymentDetailsPage.ordersList" bundle="${labels}"/> :
        </h3>
        <table class="table table-responsive">
            <tr>
                <th>ID</th>
                <th><fmt:message key="paymentDetailsPage.serviceName" bundle="${labels}"/></th>
                <th><fmt:message key="paymentDetailsPage.orderDate" bundle="${labels}"/></th>
                <th><fmt:message key="paymentDetailsPage.price" bundle="${labels}"/></th>
            </tr>
            <c:forEach var="order" items="${payment.orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.service.serviceName}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.price}</td>
                </tr>
            </c:forEach>
        </table>

        <h3>
            <fmt:message key="paymentDetailsPage.phoneCallsList" bundle="${labels}"/>:
        </h3>
        <table class="table table-responsive">
            <tr>
                <th><fmt:message key="paymentDetailsPage.callDatetime" bundle="${labels}"/></th>
                <th><fmt:message key="paymentDetailsPage.callType" bundle="${labels}"/></th>
                <th><fmt:message key="paymentDetailsPage.cost" bundle="${labels}"/></th>
                <th><fmt:message key="paymentDetailsPage.durationInSeconds" bundle="${labels}"/></th>
            </tr>
            <c:forEach var="phonecall" items="${payment.phoneCalls}">
                <tr>
                    <td>${phonecall.callDate}</td>
                    <td>${phonecall.callType}</td>
                    <td>${phonecall.cost}</td>
                    <td>${phonecall.durationInSeconds}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>




</body>
</html>
