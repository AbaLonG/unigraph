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
<div>
    <h1>Login</h1>
    <form:form method="post" action="${pageContext.request.contextPath}/login" modelAttribute="employee">
        <form:label path="email">Email</form:label>
        <form:input path="email"/>
        <form:label path="password">Password</form:label>
        <form:password path="password"/>
        <input type="submit" value="Log in">
    </form:form>
</div>
</body>
</html>
