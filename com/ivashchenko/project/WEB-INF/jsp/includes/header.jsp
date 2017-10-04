<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="links.jsp"%>
<%@include file="i18n.jsp"%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="/index.jsp" class="navbar-brand">
                <fmt:message key="header.brand" bundle="${labels}"/>
            </a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <a href="/changeLanguage?lang=ru_RU">Рус</a>
            <a href="/changeLanguage?lang=en_US">Eng</a>
            <c:if test="${currentUser != null}">
                <li>
                    <a href="/logout">
                        <button class="btn btn-danger">
                            <fmt:message key="header.logout" bundle="${labels}"/>
                        </button>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>