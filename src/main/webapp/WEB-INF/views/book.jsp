<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section class="container">
    <div class="row">
        <div class="col-md-5">
            <img src="<c:url value="/covers/${book.cover}"></c:url>"
                 alt="image" style="width: 100%"/>
        </div>

        <div class="col-md-5">
            <h1>${book.title}</h1>
            <%--Rating system--%>
            <sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
            <div ng-app="myApp" style="background-color: #fcf8e3; padding: 18px; border: 3px outset saddlebrown">
                <div ng-controller = "BookRatingDtoController as ctrl" ng-init="ctrl.getBookRating(${book.id},
                                                       '${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}');">
                    <div class="row rating-info">
                        <div class="col-sm-6">Average rating: <span class="value">{{ctrl.bookRatingDto.rating  | number : 2}}</span></div>
                        <div class="col-sm-5">Number votes: <span class="value">{{ctrl.bookRatingDto.votes}}</span></div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-6"><div star-rating ng-model="ctrl.rating1" readonly="ctrl.isReadonly" max="5"></div></div>
                        <div class="col-sm-5">
                            <form ng-submit="ctrl.submit()" method="POST">
                                <button id="submit" type="submit" class="btn btn-light btn-vote">
                                    <span class="glyphicon glyphicon-bullhorn"></span> {{ctrl.buttonLabel()}}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </sec:authorize>
            <table class="table table-hover"  style="margin-top: 5%">
                <tbody>
                <tr>
                    <td class="book-detail">Author:</td>
                    <td>${book.author}</td>
                </tr>
                <tr>
                    <td class="book-detail">Publishing:</td>
                    <td>${book.publishing}</td>
                </tr>
                <tr>
                    <td class="book-detail">Description:</td>
                    <td>${book.description}</td>
                </tr>
                <tr>
                    <td class="book-detail" style="width: 30%">Download link:</td>
                    <td style="text-align: center">
                        <sec:authentication var="principal" property="principal"/>

                        <c:if test="${empty pageContext.request.userPrincipal}">
                            <h4><span class="label label-danger" style="font-size: medium">You must be registered to see this link</span>
                            </h4>
                        </c:if>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
                            <a href="<spring:url value="/books/${book.link}"/>">
                                <h4><span class="label label-success" style="font-size: medium"><i class="fa fa-download" aria-hidden="true"></i> download</span></h4>
                            </a>
                        </sec:authorize>
                    </td>
                </tr>
                </tbody>
            </table>
            <a href="<spring:url value="/books/all" />" class="btn btn-primary" role="button"
               aria-pressed="true"><i class="fa fa-arrow-left" aria-hidden="true"></i> Back to books</a>
        </div>
    </div>
    <jsp:include page="comment.jsp"/>
</section>


