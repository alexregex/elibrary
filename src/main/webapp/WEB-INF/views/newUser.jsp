<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row main">
        <div class="main-login main-center" style="width: 50%; margin: 0 auto">
            <form:form method="POST" modelAttribute="user">
            <form:input type="hidden" path="id" id="id"/>

            <div class="form-group">
                <label for="firstName" class="cols-sm-2 control-label">First Name</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <form:input type="text" path="firstName" class="form-control" name="firstName"
                                    id="firstName" placeholder="Enter your Name"/>
                    </div>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="lastName" class="cols-sm-2 control-label">Last Name</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user-md" aria-hidden="true"></i></span>
                        <form:input type="text" path="lastName" class="form-control" name="lastName" id="lastName"
                                    placeholder="Enter your Name"/>
                    </div>
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="cols-sm-2 control-label">Your Email</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                        <form:input path="email" type="text" class="form-control" name="email" id="email"
                                    placeholder="Enter your Email"/>
                    </div>
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="Login" class="cols-sm-2 control-label">Login</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <form:input path="login" type="text" class="form-control" name="login" id="login"
                                    placeholder="Enter your Login"/>
                    </div>
                    <div class="has-error">
                        <form:errors path="login" class="help-inline"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label">Password</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <form:input path="password" type="password" class="form-control" name="password" id="password"
                               placeholder="Enter your Password"/>
                    </div>
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="confirmPassword" class="cols-sm-2 control-label">Confirm Password</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <form:input path="confirmPassword" type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                               placeholder="Confirm your Password"/>
                    </div>
                    <div class="has-error">
                        <form:errors path="confirmPassword" class="help-inline"/>
                    </div>
                </div>
            </div>

                <div style="text-align: center">
                    <input style="font-size: medium" type="submit" value="Register" class="btn btn-primary btn-sm"/>
                </div>

                </form:form>
            </div>
        </div>
    </div>