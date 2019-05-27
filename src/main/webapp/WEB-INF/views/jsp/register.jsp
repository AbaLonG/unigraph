<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card card-register my-5">
                <div class="card-header" style="border-top-left-radius: 1rem; border-top-right-radius: 1rem">
                    <h5 class="card-title text-center" style="margin: 10px">Registration</h5>
                </div>
                <div class="card-body">
                    <form:form class="form-register" method="post" modelAttribute="employee"
                               action="${pageContext.request.contextPath}/register">
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <label for="inputLogin" class="align-self-center">Login</label>
                            </div>
                            <div class="col-md">
                                <div class="form-label-group">
                                    <form:input path="login" id="inputLogin" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <label for="inputPassword">Password</label>
                            </div>
                            <div class="col-md">
                                <div class="form-label-group">
                                    <form:password path="password" id="inputPassword" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <label for="inputEmail">Email</label>
                            </div>
                            <div class="col-md">
                                <div class="form-label-group">
                                    <form:input path="email" id="inputEmail" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <label for="inputFirstName">First Name</label>
                            </div>
                            <div class="col-md">
                                <div class="form-label-group">
                                    <form:input path="firstName" id="inputFirstName" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <label for="inputLastName">Last Name</label>
                            </div>
                            <div class="col-md">
                                <div class="form-label-group">
                                    <form:input path="lastName" id="inputLastName" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <label for="inputDateOfBirth">Date Of Birth</label>
                            </div>
                            <div class="col-md">
                                <div class="form-label-group">
                                    <form:input path="dateOfBirth" id="inputDateOfBirth" cssClass="form-control"/>
                                </div>
                            </div>
                        </div>
                        <button class="btn btn-sm btn-primary btn-block text-uppercase" type="submit">Register</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<style>
    :root {
        --input-padding-x: 1.5rem;
        --input-padding-y: .75rem;
    }

    body {
        background-color: ghostwhite;
    }

    .card-register {
        border: 0;
        border-radius: 1rem;
        box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
    }

    .card-body .row {
        margin-bottom: 1rem;
    }

    .card-register .card-title {
        margin-bottom: 2rem;
        font-weight: 300;
        font-size: 1.5rem;
    }

    .card-register .card-body {
        padding: 2rem;
    }

    .form-register .btn, .card-body .btn {
        font-size: 100%;
        border-radius: 1rem;
        letter-spacing: .1rem;
        padding: 0.5rem;
        transition: all 0.2s;
    }

    .form-label-group {
        position: relative;
    }

    .form-label-group input {
        height: auto;
        border-radius: 0.5rem;
    }

    .form-label-group > input,
    .form-label-group > label {
        padding: var(--input-padding-y) var(--input-padding-x);
    }
</style>
</html>
