<%--
  Created by IntelliJ IDEA.
  User: ytretiakov
  Date: 31-May-19
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="resources/css/stylesheet.css" type="text/css">
</head>
<body>
<nav class="navbar navbar-expand-sm p-0"
     style="border-bottom-right-radius: 0; background: linear-gradient(to bottom, #1448b4, #285ebe);">
    <div class="row align-items-center w-100">
        <div class="col-sm-3 pl-3">
            <a href="${pageContext.request.contextPath}/index"
               class="navbar-brand text-white ml-3 font-weight-light"
               style="font-size: 1.5rem; font-family: 'Segoe UI', sans-serif">Unigraph</a>
        </div>
        <div class="col-sm px-0 ml-3">
            <div class="d-flex text-white align-items-center">
                <a class="p-0 pt-1 px-3 text-decoration-none links" href="${pageContext.request.contextPath}/university">University</a>
                <a class="p-0 pt-1 px-3 text-decoration-none links" href="${pageContext.request.contextPath}/edit">Edit Profile</a>
                <a class="p-0 pt-1 px-3 text-decoration-none links" href="${pageContext.request.contextPath}/friends">Friends</a>
                <form method="post" class="p-0 m-0 ml-auto"
                      action="${pageContext.request.contextPath}/settings/logout">
                    <button type="submit" class="btn btn-sm text-white">Log Out</button>
                </form>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
