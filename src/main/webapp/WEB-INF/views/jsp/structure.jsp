<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Structure</title>
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
          <h5 class="card-title">Create Structure</h5>
        </div>
        <div class="card-body p-0">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link rounded-0 active" data-toggle="tab" href="#faculty">Faculty</a>
            </li>
            <li class="nav-item">
              <a class="nav-link rounded-0" data-toggle="tab" href="#cathedra">Cathedra</a>
            </li>
            <li class="nav-item">
              <a class="nav-link rounded-0" data-toggle="tab" href="#group">Group</a>
            </li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane container active border border-top-0 p-0" id="faculty">
              <div class="row mw-100 p-0 m-0">
                <div class="col-12 m-0 p-0">
                  <div class="card border-0">
                    <div class="card-header bg-white">
                      <h5 class="card-title m-0">Add Faculty</h5>
                    </div>
                    <div class="card-body">
                      <form method="post"
                            action="${pageContext.request.contextPath}/university/structure/addFaculty">
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="facultyTitle" class="align-self-center">Set title</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <input type="text" name="title" id="facultyTitle" class="form-control"/>
                            </div>
                          </div>
                        </div>
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="facultyManager" class="align-self-center">Choose manager</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <select name="manager" id="facultyManager" class="custom-select-sm block btn-block">
                                <c:forEach var="freeManager" items="${service.freeManagersForFaculty}">
                                  <option value="${freeManager.login}">${freeManager.fullName}</option>
                                </c:forEach>
                              </select>
                            </div>
                          </div>
                        </div>
                        <button class="btn btn-sm btn-primary float-right" type="submit" value="Submit">
                          Add Faculty
                        </button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="tab-pane container fade border border-top-0 p-0" id="cathedra">
              <div class="row mw-100 p-0 m-0">
                <div class="col-12 m-0 p-0">
                  <div class="card border-0">
                    <div class="card-header bg-white">
                      <h5 class="card-title m-0">Add Cathedra</h5>
                    </div>
                    <div class="card-body">
                      <form method="post"
                            action="${pageContext.request.contextPath}/university/structure/addCathedra">
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="cathedraTitle" class="align-self-center">Set title</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <input type="text" name="title" id="cathedraTitle" class="form-control"/>
                            </div>
                          </div>
                        </div>
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="cathedraFaculty" class="align-self-center">Choose faculty</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <select name="faculty" id="cathedraFaculty" class="custom-select-sm block btn-block">
                                <c:forEach var="faculty" items="${service.facultyService.findAll()}">
                                  <option value="${faculty.title}">${faculty.title}</option>
                                </c:forEach>
                              </select>
                            </div>
                          </div>
                        </div>
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="cathedraManager" class="align-self-center">Choose manager</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <select name="manager" id="cathedraManager" class="custom-select-sm block btn-block">
                                <c:forEach var="freeManager" items="${service.freeManagersForCathedra}">
                                  <option value="${freeManager.login}">${freeManager.fullName}</option>
                                </c:forEach>
                              </select>
                            </div>
                          </div>
                        </div>
                        <button class="btn btn-sm btn-primary float-right" type="submit" value="Submit">
                          Add Cathedra
                        </button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="tab-pane container fade border border-top-0 p-0" id="group">
              <div class="row mw-100 p-0 m-0">
                <div class="col-12 m-0 p-0">
                  <div class="card border-0">
                    <div class="card-header bg-white">
                      <h5 class="card-title m-0">Add Group</h5>
                    </div>
                    <div class="card-body">
                      <form method="post"
                            action="${pageContext.request.contextPath}/university/structure/addGroup">
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="groupTitle" class="align-self-center">Set title</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <input type="text" name="title" id="groupTitle" class="form-control"/>
                            </div>
                          </div>
                        </div>
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="groupCathedra" class="align-self-center">Choose cathedra</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <select name="cathedra" id="groupCathedra" class="custom-select-sm block btn-block">
                                <c:forEach var="cathedra" items="${service.cathedraService.findAll()}">
                                  <option value="${cathedra.title}">${cathedra.title}</option>
                                </c:forEach>
                              </select>
                            </div>
                          </div>
                        </div>
                        <div class="row align-items-center my-3">
                          <div class="col-md-2">
                            <label for="groupManager" class="align-self-center">Choose manager</label>
                          </div>
                          <div class="col-md">
                            <div class="form-label-group">
                              <select name="manager" id="groupManager" class="custom-select-sm block btn-block">
                                <c:forEach var="freeManager" items="${service.freeManagersForGroup}">
                                  <option value="${freeManager.login}">${freeManager.fullName}</option>
                                </c:forEach>
                              </select>
                            </div>
                          </div>
                        </div>
                        <button class="btn btn-sm btn-primary float-right" type="submit" value="Submit">
                          Add Group
                        </button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
<style>
  body {
    background-color: ghostwhite;
  }

  .links {
    color: #e1e1e1;
    font-size: 1rem;
  }

  .links:hover {
    color: white;
  }
</style>
</html>
