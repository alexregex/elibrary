<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<br/>
<h3 style="font-weight: 500;">Comments</h3>
<hr style="border-top: 2px ridge darkcyan !important; margin-top: 10px">

<c:forEach items="${comments}" var="comment">
    <article class="row">
        <div class="col-md-2 col-sm-2 hidden-xs">
            <figure class="thumbnail">
                <img class="img-responsive" src="http://www.keita-gaming.com/assets/profile/default-avatar-c5d8ec086224cb6fc4e395f4ba3018c2.jpg" />
                <figcaption class="text-center">${comment.user.firstName} ${comment.user.lastName}</figcaption>
            </figure>
        </div>
        <div class="col-md-10 col-sm-10">
            <div class="panel panel-default arrow left">
                <div class="panel-body">
                    <header class="text-left">
                        <div class="comment-user"><i class="fa fa-user"></i> <span style="font-weight: bold"> ${comment.user.login}</span></div>
                        <time class="comment-date"><i class="fa fa-clock-o"></i><span style="font-weight: bold"> <fmt:formatDate type="both" value="${comment.dateTime}"/></span></time>
                    </header>
                    <div class="comment-post">
                        <br/>
                        <p>
                                ${comment.commentText}
                        </p>
                    </div>
                    <p class="text-right"><a href="#" class="btn btn-default btn-sm"><i class="fa fa-reply"></i> reply</a></p>
                </div>
            </div>
        </div>
    </article>
    <br/>
</c:forEach>
<div id="comment">
    <hr style="border-top: 2px ridge darkcyan !important; margin-top: 10px">
    <h3 style="font-weight: 500">Add Comment:</h3>
    <form:form modelAttribute="newComment" method="post" action="/books/book-${book.id}/comments/add" class="form-horizontal">
        <form:textarea cssStyle="width: 55%; height: 150px!important;" path="commentText"/>
        <br/>
        <button type="submit" class="btn btn-success" style="margin-top: 20px">
            <i class="fa fa-comment-o" aria-hidden="true"></i> Add comment
        </button>
    </form:form>
</div>