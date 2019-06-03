<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>University</title>
  <link rel="stylesheet" href="resources/css/stylesheet.css" type="text/css">
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <jsp:include page="header.jsp"/>
  <div class="row">
    <div class="col-12">
      <div class="card rounded-0 border-0">
        <div class="card-header p-1 border-0 rounded-0 text-white"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <div class="row mw-100 p-0 m-0 justify-content-between">
            <h5 class="col-6 card-title">University</h5>
            <div class="col-6 pr-3 text-right">
              <c:if test="${user.type eq 'Teacher'}">
                <a class="p-0 m-0 text-white text-decoration-none float-right"
                   href="${pageContext.request.contextPath}/university/structure">Add structure</a>
              </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
