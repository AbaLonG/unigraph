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
<body style="background-color: ghostwhite">
<div class="container">
    <nav class="navbar navbar-expand-sm bg-primary" style="border-bottom-left-radius: 0.5rem; border-bottom-right-radius: 0.5rem">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
            <h4 style="color: white; text-shadow: 0 0 1rem black; font-size: 1.5rem; font-weight: 400; font-family: 'Segoe UI', sans-serif">Unigraph</h4>
        </a>
        <div class="collapse navbar-collapse" id="navb">
            <ul class="navbar-nav mr-auto">

            </ul>
            <form action="${pageContext.request.contextPath}/settings/logout"
                  class="form-inline my-2 my-lg-0" method="post">
                <button class="btn btn-sm btn-light" type="submit" style="box-shadow: 0 0 0.5rem 0.1rem rgba(0, 0, 0, 0.25)">Log Out</button>
            </form>
        </div>
    </nav>

</div>
</body>
</html>
