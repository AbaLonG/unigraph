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
  <div class="row mw-100 m-0 mb-5 border-0 justify-content-between">
    <div class="col-12 col-md-7 p-0" style="min-height: 400px;">
      <div class="card rounded-0 border-0">
        <div class="card-header rounded-0 border-0 text-white"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <div class="row p-0 m-0 mw-100">
            <div class="col-6 p-0 m-0">
              <h5>University</h5>
            </div>
            <div class="col-6 p-0 m-0 text-right">
              <c:if test="${user.type eq 'Teacher'}">
                <a class="p-0 m-0 text-white text-decoration-none float-right"
                   href="${pageContext.request.contextPath}/university/structure">Structure</a>
              </c:if>
            </div>
          </div>
        </div>
        <div class="card-body border-0 p-0">
          <c:forEach var="friend" items="${filteredEmployees}">
            <div class="row mw-100 m-0 py-3 border justify-content-between">
              <div class="col-12 col-sm-5 p-0">
                <div class="p-0 mx-auto" style="max-width: 200px">
                  <a href="${pageContext.request.contextPath}/index?id=${friend.login}">
                    <div class="rounded"
                         style="max-width: 200px; max-height: 200px; width: 200px; height: 200px;
                                 background-image: url(${employeesUtil.getAvatarForEmployee(friend)});
                                 background-repeat: no-repeat; background-size: cover; background-position: 50% 50%;">
                    </div>
                  </a>
                </div>
              </div>
              <div class="col-12 col-sm-7 p-0">
                <table class="table table-borderless m-0">
                  <tbody>
                  <tr>
                    <td>Name</td>
                    <td>${friend.firstName} ${friend.lastName}</td>
                  </tr>
                  <tr>
                    <td>Email</td>
                    <td>${friend.email}</td>
                  </tr>
                  <tr>
                    <td>Date Of Birth</td>
                    <td>${friend.dateOfBirth}</td>
                  </tr>
                  <tr>
                    <td>Type</td>
                    <c:if test="${empty friend.type}">
                      <td>N/A</td>
                    </c:if>
                    <c:if test="${not empty friend.type}">
                      <td>${friend.type}</td>
                    </c:if>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
    <div class="col-12 col-md-5 p-0" style="min-height: 400px">
      <div class="card rounded-0 border-0">
        <div class="card-header rounded-0 border-0 text-white"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <h5>Filters</h5>
        </div>
        <div class="card-body p-3 border">
          <form action="${pageContext.request.contextPath}/university/filter" method="post">
            <div class="form-group">
              <label for="facultyTitle">Faculty:</label>
              <select class="form-control" id="facultyTitle" name="facultyTitle">
                <option>Any</option>
                <c:forEach var="faculty" items="${service.facultyService.findAll()}">
                  <option>${faculty.title}</option>
                </c:forEach>
              </select>
              <br>
              <label for="cathedraTitle">Cathedra:</label>
              <select class="form-control" id="cathedraTitle" name="cathedraTitle">
                <option>Any</option>
                <c:forEach var="cathedra" items="${service.cathedraService.findAll()}">
                  <option>${cathedra.title}</option>
                </c:forEach>
              </select>
              <br>
              <label for="groupTitle">Group:</label>
              <select class="form-control" id="groupTitle" name="groupTitle">
                <option>Any</option>
                <c:forEach var="group" items="${service.groupService.findAll()}">
                  <option>${group.title}</option>
                </c:forEach>
              </select>
              <br>
              <label for="employeeType">Employee type:</label>
              <select class="form-control" id="employeeType" name="employeeType">
                <option>Any</option>
                <option>Student</option>
                <option>Teacher</option>
                <option>Stuff</option>
              </select>
              <br>
              <label for="nameFilter">Name Filter:</label>
              <input type="text" class="form-control" name="nameFilter" id="nameFilter">
              <br>
              <label for="countFilter">Count fiLter:</label>
              <input type="text" class="form-control" name="countFilter" id="countFilter">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
