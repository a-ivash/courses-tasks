<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/includes/links.jsp" %>
    <%@include file="/includes/i18n.jsp" %>
    <title>
        <fmt:message key="paymentsPage.title" bundle="${labels}"/>
    </title>

    <jsp:useBean id="currentUser" type="project.model.users.AbstractUser" scope="session"/>
    <jsp:useBean id="payments" type="java.util.List<project.model.orders.Payment>" scope="request"/>
</head>
<body>
<%@include file="/includes/header.jsp" %>
<c:set var="adminView" value="${currentUser.administrator}"/>
<c:set var="subscriberView" value="${not currentUser.administrator}"/>

<div class="container">
    <c:if test="${adminView}">
        <div class="row">
            <div class="col-md-4 col-md-offset-1">
                ${paymentsCreationDateTooLate}
                <form method="post" action="/createPayments">
                    <button type="submit" class="btn btn-success btn-block">
                        <fmt:message key="paymentsPage.createPaymentButton" bundle="${labels}"/>
                    </button>
                </form>

            </div>
        </div>
    </c:if>

    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <table class="table table-responsive">
                <tr>
                    <th>ID</th>
                    <c:if test="${adminView}">
                        <th>
                            <fmt:message key="paymentsPage.subscriberName" bundle="${labels}"/>
                        </th>
                    </c:if>
                    <th>
                        <fmt:message key="paymentsPage.orderDate" bundle="${labels}"/>
                    </th>
                    <th>
                        <fmt:message key="paymentsPage.paymentDate" bundle="${labels}"/>
                    </th>
                    <c:if test="${subscriberView}">
                        <th><!-- for complete payment button --></th>
                    </c:if>
                </tr>

                <c:forEach var="payment" items="${payments}">
                    <tr>
                        <td>${payment.id}</td>
                        <c:if test="${adminView}">
                            <td>${payment.subscriber.firstName} ${payment.subscriber.lastName}</td>
                        </c:if>
                        <td>${payment.orderDate}</td>
                        <td>
                                ${payment.paymentDate}
                            <c:if test="${payment.paymentDate == null}">Not paid</c:if>
                        </td>
                        <c:if test="${subscriberView}">
                            <td>
                                <form action="/confirmPayment">
                                    <input type="hidden" name="paymentId" value="${payment.id}">
                                    <button type="submit" class="btn btn-success">
                                        <fmt:message key="paymentsPage.confirmPayment" bundle="${labels}"/>
                                    </button>
                                </form>
                            </td>
                        </c:if>
                        <td>
                            <a href="/payment?paymentId=${payment.id}">
                                <button class="btn btn-info">
                                    <fmt:message key="paymentsPage.showDetails" bundle="${labels}"/>
                                </button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
