<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <table class="table">
            <thead class="thead-inverse">
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Login</th>
                <th>Email</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="<c:url value='/delete-user-${user.id}' />" class="btn btn-danger custom-width">
                        <i class="fa fa-times" aria-hidden="true"></i>  Delete</a>
                    </td>
                    <td>
                        <a href="<c:url value='/edit-user-${user.id}' />" class="btn btn-success custom-width">
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>  Edit</a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
