<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Faculty</title>
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
      <div class="card rounded-0 border-0" style="background-color: ghostwhite">
        <div class="card-body p-0">
          <div class="row mw-100 p-0 m-0 justify-content-between">
            <div class="col-sm-3 p-0">
              <div class="card border-0" style="background-color: ghostwhite">
                <div class="p-0 m-0 mh-100 mw-100">
                  <img class="card-img-top p-0 m-0 rounded-0" src="${controller.managerAvatar}" alt="Manager">
                </div>
                <div class="card-body p-0">
                  <div class="w-100 text-center text-white pointer p-1"
                       style="font-family: 'Segoe UI', sans-serif; font-size: 1rem; background: linear-gradient(to bottom, #285EBE, #396dc3)">
                    Faculty Manager:
                  </div>
                  <div class="w-100 text-center text-white pointer p-1"
                       style="font-family: 'Segoe UI', sans-serif; font-size: 1rem; background: linear-gradient(to top, #285EBE, #396dc3); border-radius: 0 0 0.5rem 0.5rem">
                    <a href="${pageContext.request.contextPath}/index?id=${faculty.facultyManager.login}"
                       class="text-decoration-none text-white">
                      ${faculty.facultyManager.fullName}
                    </a>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-sm-9 p-0 mt-3 mt-sm-0">

              <div class="card border-primary border-0"
                   style="border-radius: 0 0 0.5rem 0.5rem; border-top: 0; border-right: 0">
                <div class="card-header pl-3 rounded-0 border-0 text-white"
                     style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
                  <h5 class="m-0">Faculty: ${faculty.title}</h5>
                </div>
              </div>
              <div class="row mw-100 p-0 m-0 w-100">

                <div class="col-12 col-sm-6 p-0">
                  <div class="card border-0">
                    <div class="card-header p-0 text-white border-0 rounded-0"
                         style="background: linear-gradient(to bottom, #396dc3, #4179d7)">
                      <p class="m-0 my-2 pl-3">Cathedras:</p>
                    </div>
                    <div class="card-body p-0">
                      <table class="table table-striped table-hover m-0">
                        <tbody>
                        <c:forEach var="cathedra" items="${faculty.cathedras}">
                          <tr>
                            <td>
                              <a href="${pageContext.request.contextPath}/cathedra?cathedraTitle=${cathedra.title}"
                                 class="text-decoration-none pl-1">
                                  ${cathedra.title}
                              </a>
                            </td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>

                <div class="col-12 col-sm-6 p-0">
                  <div class="card border-0">
                    <div class="card-header p-0 text-white border-0 rounded-0"
                         style="background: linear-gradient(to bottom, #396dc3, #4179d7)">
                      <p class="m-0 my-2 ml-2">Teachers:</p>
                    </div>
                    <div class="card-body p-0 border-0 rounded-0">
                      <table class="table table-striped table-hover m-0">
                        <tbody>
                        <c:forEach var="teacher" items="${controller.teachersOfFaculty}">
                          <tr>
                            <td>
                              <a href="${pageContext.request.contextPath}/index?id=${teacher.login}"
                                 class="text-decoration-none pl-0">
                                  ${teacher.fullName}
                              </a>
                            </td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
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
</html>
