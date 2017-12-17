<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


    <form:form method="POST" modelAttribute="editBook" class="form-horizontal" enctype="multipart/form-data">
        <fieldset>
            <legend><span style="font-weight: bolder">Edit book: </span> <span style="text-decoration: underline; color: saddlebrown; font-weight: bolder">${editBook.title}</span></legend>
            <form:input type="hidden" path="id" id="id"/>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="title"><spring:message code="addNewBook.form.title.label" /></label>
                <div class="col-lg-10">
                    <form:input id="title" path="title" type="text"
                                class="form:input-large" />
                    <form:errors path="title" cssClass="has-error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="author"><spring:message code="addNewBook.form.author.label" /></label>
                <div class="col-lg-10">
                    <form:input id="author" path="author" type="text"
                                class="form:input-large" />
                    <form:errors path="author" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="publishing"><spring:message code="addNewBook.form.publishing.label" /></label>
                <div class="col-lg-10">
                    <form:input id="publishing" path="publishing" type="text"
                                class="form:input-large" />
                    <form:errors path="publishing" cssClass="has-error"/>
                </div>
            </div>

          <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="description"><spring:message code="addNewBook.form.description.label" /></label>
                <div class="col-lg-10">
                    <form:textarea cssStyle="width: 50%; height: 130px" id="description" path="description"
                                class="form:input-large" />
                    <form:errors path="description" cssClass="has-error"/>
                </div>
            </div>

            <form:input type="hidden" path="cover" id="cover"/>
            <br/>
            <form:input type="hidden" path="link" id="link"/>


            <div class="form-group">
                <label class="control-label col-lg-2" for="coverBook"> <spring:message code="addNewBook.form.coverBook.label" />
                </label>
                <div class="col-lg-10">
                    <input id="coverBook" accept=".jpeg,.jpg,.jpe,.png,.pns" type="file" name="coverFile" class="form:input-large" />
                    <output class="has-error" title="uploadCoverError">${uploadCoverError}</output>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="uploadBook"> <spring:message code="addNewBook.form.uploadBook.label" />
                </label>
                <div class="col-lg-10">
                    <input id="uploadBook" accept=".txt,.rtf,.doc,.odt,.pdf" type="file" name="bookFile" class="form:input-large" />
                    <output class="has-error" title="uploadBookError">${uploadBookError}</output>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="submit" class="btn btn-success">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i> Save
                    </button>
                </div>
            </div>

        </fieldset>
    </form:form>
