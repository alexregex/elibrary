<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><tiles:insertAttribute name="title"/></title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Kavivanar" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/additional.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

</head>

<body>
<section class="container">
    <div class="pull-right" style="padding-right: 50px">
        <c:choose>
            <c:when test="${empty pageContext.request.userPrincipal}">
                <span style="color: red; font-weight: bolder">You are not authorized !</span>
            </c:when>
            <c:otherwise>
                <span style="font-weight: bolder">Welcome, dear
                            <span style="color: blue; text-decoration: underline"><sec:authentication
                                    property="principal.username"/></span>
                    to our library</span>
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="<spring:url value="/books/admin-booklist"/>">
                        [admin panel]
                    </a>
                </sec:authorize>
            </c:otherwise>
        </c:choose>
    </div>
</section>

<div class="container">
    <div class="jumbotron gradient" style="padding-top: 3px !important;">
        <div class="header">
            <ul class="nav nav-pills pull-right">
                <tiles:insertAttribute name="navigation"/>
            </ul>
            <h3 class="label-el"> <img src="${pageContext.request.contextPath}/static/img/logo-book.png" /> Electronic Library</h3>
        </div>

        <h1>
            <tiles:insertAttribute name="heading"/>
        </h1>
        <p>
            <tiles:insertAttribute name="tagline"/>
        </p>
    </div>

    <div class="row">
        <tiles:insertAttribute name="content"/>
    </div>
</div>
<footer class="footer">
    <div class="container customize-footer-container">
        <p class="text-muted"><tiles:insertAttribute name="footer"/></p>
    </div>
</footer>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-resource.js"></script>
    <script src="<c:url value='/static/js/app.js'/>"></script>
    <script src="<c:url value='/static/js/rating_controller/rating_controller.js'/>"></script>
    <script src="<c:url value='/static/js/rating_service/rating_service.js'/>"></script>
</body>
</html>