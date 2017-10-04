<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/includes/i18n.jsp"%>
    <%@include file="/includes/links.jsp"%>
    <title>
        <fmt:message key="subscribersDetailsPage.title" bundle="${labels}"/>
    </title>
    <jsp:useBean id="subscriberService" class="project.service.implementation.DefaultSubscriberService"/>
    <%--<jsp:useBean id="subscriber" type="project.model.users.Subscriber" scope="page"/>--%>
    <c:set var="subscriber" value="${subscriberService.getSubscriber(param.subscriberId)}"/>
</head>
<body>
<div class="container">
    <div class="col-md-6 col-md-offset-3">

        <h3><fmt:message key="subscribersDetailsPage.detailsAbout" bundle="${labels}"/> ${subscriber.firstName}  ${subscriber.lastName}</h3>
        <table>
            <tr>
                <td><fmt:message key="subscribersDetailsPage.firstName" bundle="${labels}"/></td>
                <td>${subscriber.firstName}</td>
            </tr>
            <tr>
                <td><fmt:message key="subscribersDetailsPage.lastName" bundle="${labels}"/></td>
                <td>${subscriber.lastName}</td>
            </tr>
            <tr>
                <td><fmt:message key="subscribersDetailsPage.email" bundle="${labels}"/></td>
                <td>${subscriber.email}</td>
            </tr>
            <tr>
                <td><fmt:message key="subscribersDetailsPage.joinDate" bundle="${labels}"/></td>
                <td>${subscriber.joinDate}</td>
            </tr>
            <tr>
                <td><fmt:message key="subscribersDetailsPage.phone" bundle="${labels}"/></td>
                <td>
                    <c:if test="${not empty subscriber.phone}">
                        ${subscriber.phone.phoneNumber}
                    </c:if>
                    <c:if test="${empty subscriber.phone}">
                        <fmt:message key="subscribersDetailsPage.NA" bundle="${labels}"/>
                    </c:if>
                </td>
            </tr>
        </table>

        <c:if test="${not subscriber.active}">
            <form action="/activateUser" method="post">
                <input type="hidden" name="subscriberId" value="${subscriber.id}">
                <button type="submit" class="btn btn-success">
                    <fmt:message key="subscribersDetailsPage.activateAccount" bundle="${labels}"/>
                </button>
            </form>
        </c:if>

        <c:if test="${subscriber.active}">
            <form action="/blockUser" method="post">
                <input type="hidden" name="subscriberId" value="${subscriber.id}">
                <button type="submit" class="btn btn-danger">
                    <fmt:message key="subscribersDetailsPage.blockAccount" bundle="${labels}"/>
                </button>
            </form>
        </c:if>
        <a href="/subscribers"><fmt:message key="subscribersDetailsPage.goBack" bundle="${labels}"/></a>
    </div>
</div>
</body>
</html>
