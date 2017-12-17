<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<section class="container">
    <div class="row" style="text-align: center;width: 90%;margin: 0 auto">
        <div class="alert alert-danger" style="font-size: large">
            <span>Dear <span style="color: blue; text-decoration: underline"><sec:authentication property="principal.username"/></span>,
                you are not authorized to access this page.</span>
        </div>
    </div>
</section>
