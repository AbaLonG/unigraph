<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Unigraph</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Unigraph</h5>
                    <form:form class="form-signin" method="post" modelAttribute="employee"
                               action="${pageContext.request.contextPath}/login">
                        <div class="form-label-group">
                            <form:input path="login" id="inputLogin" cssClass="form-control"/>
                        </div>
                        <div class="form-label-group">
                            <form:password path="password" id="inputPassword" cssClass="form-control"/>
                        </div>
                        <button class="btn btn-sm btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                    </form:form>
                    <form method="get" action="${pageContext.request.contextPath}/register">
                        <button class="btn btn-sm btn-outline-primary btn-block text-uppercase" type="submit">Register
                        </button>
                    </form>
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

    .card-signin {
        border: 0;
        border-radius: 1rem;
        box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
    }

    .card-signin .card-title {
        margin-bottom: 2rem;
        font-weight: 300;
        font-size: 1.5rem;
    }

    .card-signin .card-body {
        padding: 2rem;
    }

    .form-signin .btn, .card-body .btn {
        font-size: 100%;
        border-radius: 1rem;
        letter-spacing: .1rem;
        padding: 0.5rem;
        transition: all 0.2s;
    }

    .form-label-group {
        position: relative;
        margin-bottom: 1rem;
    }

    .form-label-group input {
        height: auto;
        border-radius: 1rem;
    }

    .form-label-group > input,
    .form-label-group > label {
        padding: var(--input-padding-y) var(--input-padding-x);
        background-color: ghostwhite !important;
    +
    }
</style>
</html>
