<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <table class="table">
            <thead class="thead-inverse">
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Author</th>
                <th>Publishing</th>
                <th>Update</th>
                <th>Added</th>
                <th>Cover</th>
                <th>Link</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.publishing}</td>
                    <td>${book.date}</td>
                    <td>${book.user.login}</td>
                    <td><img src="<c:url value="/covers/${book.cover}"></c:url>" alt="img"
                             height="250" width="200"></td>
                    <td>
                        <a href="<spring:url value="/books/${book.link}"/>">download</a>
                    </td>
                    <td>
                        <a href="<c:url value='/books/delete-book-${book.id}' />" class="btn btn-danger custom-width">
                        <i class="fa fa-times" aria-hidden="true"></i>  Delete</a>
                    </td>
                    <td>
                        <a href="<c:url value='/books/update-book-${book.id}' />" class="btn btn-success custom-width">
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>  Edit</a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
