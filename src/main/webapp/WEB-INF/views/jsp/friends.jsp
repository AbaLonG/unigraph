<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Friends</title>
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
        <div class="card-header rounded-0 text-white"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <h5>Friends</h5>
        </div>
        <div class="card-body border-left border-right border-bottom border-primary p-0">
          <c:forEach var="friend" items="${employee.friends}">
            <div class="row mw-100 m-0 py-3 border border-primary justify-content-between">
              <div class="col-12 col-sm-5 p-0">
                <div class="p-0 mx-auto" style="max-width: 200px">
                  <a href="${pageContext.request.contextPath}/index?id=${friend.login}">
                    <div class="rounded"
                         style="max-width: 200px; max-height: 200px; width: 200px; height: 200px;
                                 background-image: url(${controller.getAvatarForEmployee(friend)});
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
        <div class="card-header rounded-0 text-white"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <h5>Filters</h5>
        </div>
        <div class="card-body p-3 border border-primary">
          <form>
            <div class="form-group">
              <label for="facultyTitle">Faculty:</label>
              <select class="form-control" id="facultyTitle" name="facultyTitle">
                <c:forEach var="faculty" items="${faculties}">
                  <option>${faculty.title}</option>
                </c:forEach>
              </select>
              <br>
              <label for="cathedraTitle">Cathedra:</label>
              <select class="form-control" id="cathedraTitle" name="cathedraTitle">
                <c:forEach var="cathedra" items="${cathedras}">
                  <option>${cathedra.title}</option>
                </c:forEach>
              </select>
              <br>
              <label for="groupTitle">Group:</label>
              <select class="form-control" id="groupTitle" name="groupTitle">
                <c:forEach var="group" items="${groups}">
                  <option>${group.title}</option>
                </c:forEach>
              </select>
              <br>
              <label for="nameFilter">Name Filter:</label>
              <input type="text" class="form-control" name="nameFilter" id="nameFilter">
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
