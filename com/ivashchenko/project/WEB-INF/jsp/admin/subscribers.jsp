<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/includes/i18n.jsp"%>
    <%@include file="/includes/links.jsp"%>
    <title>
        <fmt:message key="subscribersPage.title" bundle="${labels}"/>
    </title>
    <style>
    </style>

    <jsp:useBean id="subscribers" type="java.util.List<project.model.users.Subscriber>" scope="request"/>
</head>
<body>
<%@include file="/includes/header.jsp"%>
<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <h3><a href="/admin/subscribers2xml.jsp">Get subscribers in XML</a></h3>
        <table>
            <tr>
                <td class="info">Fresh accounts</td>
            </tr>
        </table>
        <table class="table table-responsive">
            <tr>
                <th>ID</th>
                <th><fmt:message key="subscribersPage.firstName" bundle="${labels}"/></th>
                <th><fmt:message key="subscribersPage.lastName" bundle="${labels}"/></th>
                <th><fmt:message key="subscribersPage.email" bundle="${labels}"/></th>
                <th><fmt:message key="subscribersPage.address" bundle="${labels}"/></th>
                <th></th>
            </tr>
            <c:forEach var="subscriber" items="${subscribers}">
                <tr <c:if test="${not subscriber.active}">class="info"</c:if> >
                    <td>${subscriber.id}</td>
                    <td>${subscriber.firstName}</td>
                    <td>${subscriber.lastName}</td>
                    <td>${subscriber.email}</td>
                    <td>${subscriber.address.streetName}</td>
                    <td>
                        <a href="/subscriber?subscriberId=${subscriber.id}">
                            <button type="button" class="btn btn-success">
                                <fmt:message key="subscribersPage.details" bundle="${labels}"/>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
