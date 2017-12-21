<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="container">
    <div class="row">
        <c:forEach items="${books}" var="book">
            <div class="col-sm-6 col-md-3" style="margin-bottom: 20px !important;">
                <div class="thumbnail" style="padding-top:10px; border: 2px solid #9dbfff; border-radius: 8px;height: 530px !important;">
                    <img src="<c:url value="/covers/${book.cover}"></c:url>" alt="cover_image" height="250"
                         width="200"/>
                    <div class="caption">
                        <h3>${book.title}</h3>
                        <p style="font-size: larger"><span style="font-family: Arial;font-weight: bold">
                                   Author: </span>${book.author}</p>
                        <p style="font-size: medium"><span style="font-family: Arial;font-weight: bold">
                                   Publishing: </span>${book.publishing}</p>
                        <p style="position:absolute; left:10%; top:87%">
                            <a href="<c:url value='/books/book-${book.id}' />" class="btn btn-info"
                               role="button" aria-pressed="true"><span style="font-size: medium !important;"><i class="fa fa-book" aria-hidden="true"></i> Details</span></a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>

