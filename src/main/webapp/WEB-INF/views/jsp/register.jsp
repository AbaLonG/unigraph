<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/register" modelAttribute="employee">
    <form:label path="login">Login</form:label><form:input path="login"/>
    <form:label path="password">Password</form:label><form:password path="password"/>
    <form:label path="email">Email</form:label><form:input path="email"/>
    <form:label path="firstName">First name</form:label><form:input path="firstName"/>
    <form:label path="lastName">Last name</form:label><form:input path="lastName"/>
    <form:label path="dateOfBirth">Date of birth</form:label><form:input path="dateOfBirth"/>
    <input type="submit" value="Register">
</form:form>
</body>
</html>
