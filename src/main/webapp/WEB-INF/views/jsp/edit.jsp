<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Edit</title>
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
        <div class="card-header p-0 pl-3 pt-2 border-0 rounded-0 text-white"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <h5 class="card-title">Edit ${user.login}</h5>
        </div>
        <div class="card-body">
          <form class="form-register" method="post" action="${pageContext.request.contextPath}/edit">
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputLogin" class="align-self-center">Login</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <input type="text" name="login" id="inputLogin" class="form-control" value="${user.login}"/>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputPassword">Password</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <input type="password" name="password" id="inputPassword" value="${user.password}"
                         class="form-control"/>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputEmail">Email</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <input type="text" name="email" id="inputEmail" class="form-control" value="${user.email}"/>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputFirstName">First Name</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <input type="text" name="firstName" id="inputFirstName" class="form-control"
                         value="${user.firstName}"/>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputLastName">Last Name</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <input type="text" name="lastName" id="inputLastName" class="form-control" value="${user.lastName}"/>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputType">Type</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <select name="type" id="inputType" class="custom-select-sm block btn-block">
                    <c:if test="${user.type ne null}">
                      <option selected>${user.type.toString()}</option>
                    </c:if>
                    <option value="Student">Student</option>
                    <option value="Teacher">Teacher</option>
                    <option value="Stuff">Stuff</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputGroup">Group</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <select name="group" id="inputGroup" class="custom-select-sm block btn-block">
                    <c:if test="${user.group ne null}">
                      <option selected>${user.group.title}</option>
                    </c:if>
                    <c:forEach var="group" items="${service.groupService.findAll()}">
                      <c:if test="${user.group ne null and user.group.title ne group.title}">
                        <option value="${group.title}">${group.title}</option>
                      </c:if>
                    </c:forEach>
                  </select>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label>Gender</label>
              </div>
              <div class="col-md">
                <div class="custom-control custom-radio custom-control-inline">
                  <input type="radio" class="custom-control-input" id="customRadio" name="gender" value="Male"
                         checked="checked">
                  <label class="custom-control-label" for="customRadio">Male</label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                  <input type="radio" class="custom-control-input" id="customRadio2" name="gender" value="Female">
                  <label class="custom-control-label" for="customRadio2">Female</label>
                </div>
              </div>
            </div>
            <div class="row my-2 align-items-center">
              <div class="col-md-2">
                <label for="inputDateOfBirth">Date Of Birth</label>
              </div>
              <div class="col-md">
                <div class="form-label-group">
                  <input type="date" name="dateOfBirth" id="inputDateOfBirth" class="form-control"
                         value="${user.dateOfBirth}"/>
                </div>
              </div>
            </div>
            <button class="btn btn-sm btn-primary btn-block text-uppercase my-2" type="submit">Save</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
