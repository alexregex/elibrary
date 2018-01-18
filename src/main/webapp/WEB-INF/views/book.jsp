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
            <sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
                <div ng-controller="RatingDemoCtrl">
                    <h4>Rating</h4>
                    <uib-rating ng-model="rate" max="max" read-only="isReadonly" on-hover="hoveringOver(value)" on-leave="overStar = null" titles="['one','two','three']" aria-labelledby="default-rating"></uib-rating>
                    <span class="label" ng-class="{'label-warning': percent<30, 'label-info': percent>=30 && percent<70, 'label-success': percent>=70}" ng-show="overStar && !isReadonly">{{percent}}%</span>
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


