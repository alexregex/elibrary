<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li><a href="<spring:url value="/books/all"/>">All books</a></li>
<li><a href="<spring:url value="/newuser"/>">Create account</a></li>
<li><a href="<spring:url value="/login"/>">
    <c:choose>
        <c:when test="${empty pageContext.request.userPrincipal}">
            Sign-in
        </c:when>
        <c:otherwise>
            Sign-out
        </c:otherwise>
    </c:choose>
</a></li>
