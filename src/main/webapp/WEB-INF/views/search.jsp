<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width: 90%!important; display:block; text-align:center; margin:0 auto">
    <form onsubmit="return checkCheckboxes()" action="/books/search" method="post">
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <div class="form-row align-items-center">
            <button style="float: right" type="submit" class="btn btn-primary"><i class="fa fa-search" aria-hidden="true"></i> Search</button>
            <div class="col-auto" style="overflow: hidden; padding-right: .5em;">
                <input style="width: 100%;" class="form-control is-invalid" name="textSearch" placeholder="Enter ..."
                       oninvalid="this.setCustomValidity('Please, enter keywords for search !')"
                       oninput="setCustomValidity('')" required>
            </div>

            <br/>
            <div class="search-options">
                <input type="checkbox" id="title_checkbox" name="byTitle" value="1"/> By title
                <input type="hidden" name="byTitle" value="0"/>

                <input style="margin-left: 15px" id="description_checkbox" type="checkbox" name="byDescription" value="1"/> In description
                <input type="hidden" name="byDescription" value="0"/>
            </div>
        </div>
    </form>
    <c:choose>
        <c:when test="${empty searchResult}">
            <c:if test="${not empty searchMessage}">
                <br/>
                <p class="alert alert-danger" style="font-size: x-large" role="alert">
                        ${searchMessage}
                </p>
            </c:if>
        </c:when>
        <c:otherwise>
            <c:forEach items="${searchResult}" var="book" varStatus="loop">
                <div class="row">
                    <hr/>
                    <div class="col-sm-1" style="width: 5px; font-family: 'Times New Roman'; font-size: x-large; font-weight: bold;
                     horiz-align: center; line-height: 200px;">
                            ${loop.index+1}
                    </div>
                    <div class="col-sm-5" style="width: 200px">
                        <img class="img-thumbnail img-responsive" src="<c:url value="/covers/${book.cover}"></c:url>" alt="img"
                             height="250" width="200">
                    </div>
                    <div class="col" style="text-align: left !important;">
                        <div style="font-family: Helvetica, Arial, sans-serif; font-size: medium; font-weight: 600;text-decoration: underline;
                                color: brown">${book.title}</div>
                        <br>
                        <div style="text-align: justify !important;">${book.description}</div>
                        <br/>
                        <div>
                            <a style="font-family: Arial, sans-serif, Helvetica; font-size: 1em" href="<c:url value='/books/book-${book.id}' />"
                               class="btn btn-info custom-width">
                                <i class="fa fa-info-circle" aria-hidden="true"></i> Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<script>
    function checkCheckboxes() {
        var title_checkbox = document.getElementById("title_checkbox").checked;
        var description_checkbox =document.getElementById("description_checkbox").checked;
        if(title_checkbox != true && description_checkbox !=true) {
            alert("Select at least one checkboxes !");
            return false;
        }

        return true;

    }
</script>



