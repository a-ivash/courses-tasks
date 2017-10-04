<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="lang" value="${empty lang ? 'en_US' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="localization/labels" var="labels"/>