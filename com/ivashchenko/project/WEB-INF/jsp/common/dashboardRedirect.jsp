<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:useBean id="currentUser" type="project.model.users.AbstractUser" scope="session"/>
</head>
<body>
    <c:choose>
        <c:when test="${currentUser.administrator}">
            <jsp:forward page="/admin/dashboard.jsp"/>
        </c:when>
        <c:when test="${not currentUser.administrator}">
            <jsp:forward page="/subscriber/dashboard.jsp"/>
        </c:when>
    </c:choose>
</body>
</html>
