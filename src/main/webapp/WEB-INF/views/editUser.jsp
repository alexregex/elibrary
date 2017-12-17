<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


    <form:form method="POST" modelAttribute="editUser" class="form-horizontal">
        <fieldset>
            <legend><span style="font-weight: bolder">Edit user:</span> <span style="text-decoration: underline; color: saddlebrown; font-weight: bolder">${editUser.firstName} ${editUser.lastName} (${editUser.login})</span></legend>
            <form:input type="hidden" path="id" id="id"/>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="firstName">First name</label>
                <div class="col-lg-10">
                    <form:input id="firstName" path="firstName" type="text"
                                class="form:input-large" />
                    <form:errors path="firstName" cssClass="has-error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="lastName">Last name</label>
                <div class="col-lg-10">
                    <form:input id="lastName" path="lastName" type="text"
                                class="form:input-large" />
                    <form:errors path="lastName" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="login">User login</label>
                <div class="col-lg-10">
                    <form:input id="login" path="login" type="text"
                                class="form:input-large" />
                    <form:errors path="login" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="email">email</label>
                <div class="col-lg-10">
                    <form:input id="email" path="email" type="text"
                                class="form:input-large" />
                    <form:errors path="email" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="email">Password</label>
                <div class="col-lg-10">
                    <form:input id="password" path="password" type="password"
                                class="form:input-large" />
                    <form:errors path="password" cssClass="has-error"/>
                </div>
            </div>

            <div class="form-group">
                <div class="form-group col-md-12">
                    <label class="control-label col-lg-2 col-lg-2" for="userProfiles">Roles</label>
                    <div class="col-md-7">
                        <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="userProfiles" class="help-inline"/>
                        </div>
                    </div>
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
