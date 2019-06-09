<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="text/html">
<head>
  <title>Unigraph</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color: ghostwhite">
<div class="container">
  <jsp:include page="header.jsp"/>
  <div class="row mw-100 p-0 m-0 justify-content-between">
    <div class="col-sm-3 p-0">
      <div class="card border-0" style="background-color: ghostwhite">
        <div class="p-0 m-0 mh-100 mw-100">
          <img class="card-img-top p-0 m-0 rounded-0" src="${controller.getAvatarForEmployee(employee)}"
               alt="Card image">
        </div>
        <div class="card-body p-0">
          <div class="w-100 text-center text-white pointer p-1"
               style="font-family: 'Segoe UI', sans-serif; font-size: 1.25rem; background: linear-gradient(to bottom, #285EBE, #396dc3)">
            ${employee.login}
          </div>
          <c:if test="${employee.login.equals(sessionUser.login)}">
            <button type="button" class="btn btn-block border-0 btn-sm btn-primary mt-0" data-toggle="modal"
                    data-target="#changeAvatar"
                    style="border-radius: 0 0 0.5rem 0.5rem; background: linear-gradient(to top, #285EBE, #396dc3)">
              Cange Profile Picture
            </button>
          </c:if>
          <c:if test="${employee.login ne sessionUser.login}">
            <c:if test="${!sessionUser.friends.contains(employee)}">
              <form class="p-0 m-0" method="post"
                    action="${pageContext.request.contextPath}/index/subscribe?id=${employee.login}">
                <input type="submit" class="btn btn-block border-0 btn-sm btn-primary mt-0" value="Add a friend"
                       style="border-radius: 0 0 0.5rem 0.5rem; background: linear-gradient(to top, #285EBE, #396dc3)">
              </form>
            </c:if>
            <c:if test="${sessionUser.friends.contains(employee)}">
              <form class="p-0 m-0" method="post"
                    action="${pageContext.request.contextPath}/index/unsubscribe?id=${employee.login}">
                <input type="submit" class="btn btn-block border-0 btn-sm btn-primary mt-0"
                       value="Remove from friends"
                       style="border-radius: 0 0 0.5rem 0.5rem; background: linear-gradient(to top, #285EBE, #396dc3)">
              </form>
            </c:if>
          </c:if>
          <div class="modal" id="changeAvatar">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h4 class="modal-title">Profile Picture</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                  <form method="post" enctype="multipart/form-data"
                        action="${pageContext.request.contextPath}/index/updatePicture">
                    <div class="row m-0">
                      <div class="col-9 pr-0">
                        <div class="custom-file">
                          <input type="file" class="custom-file-input" name="customFile"
                                 id="customFile" accept="image/jpeg,image/png">
                          <label class="custom-file-label" for="customFile">Choose
                            file</label>
                        </div>
                      </div>
                      <div class="col pl-4 pr-0">
                        <input type="submit" class="btn btn-primary" value="Upload">
                      </div>
                    </div>
                  </form>
                  <script>
                      $(".custom-file-input").on("change", function () {
                          var fileName = $(this).val().split("\\").pop();
                          $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                      });
                  </script>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-sm-9 p-0 mt-3 mt-sm-0">
      <%--      Table --%>
      <div class="card border-primary" style="border-radius: 0 0 0.5rem 0.5rem; border-top: 0; border-right: 0">
        <div class="card-header pl-3 pb-2 rounded-0 text-white"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <h5>${employee.firstName} ${employee.lastName}</h5>
        </div>
        <div class="card-body p-0 border-right border-primary" style="border-radius: 0 0 0.5rem 0.5rem">
          <table class="table table-striped table-hover m-0 pl-5">
            <tbody>
            <tr>
              <td>University</td>
              <td>Kharkiv National University of Radioelectronics</td>
            </tr>
            <tr>
              <td>Type</td>
              <td>${employee.type.toString()}</td>
            </tr>
            <tr>
              <td>Faculty</td>
              <td>
                <a href="${pageContext.request.contextPath}/faculty?id=${employee.cathedra.faculty.title}">${employee.cathedra.faculty.title}</a>
              </td>
            </tr>
            <tr>
              <td>Cathedra</td>
              <td>
                <a href="${pageContext.request.contextPath}/cathedra?id=${employee.cathedra.title}">${employee.cathedra.title}</a>
              </td>
            </tr>
            <tr>
              <td>Group</td>
              <td>
                <c:if test="${employee.type.toString() == 'Student'}">
                  <a href="${pageContext.request.contextPath}/group?id=${employee.group.title}">${employee.group.title}</a>
                </c:if>
                <c:if test="${employee.type.toString() == 'Teacher'}">
                  <a href="${pageContext.request.contextPath}/group?id=${groupOfTeacher.title}">${groupOfTeacher.title}</a>
                </c:if>
              </td>
            </tr>
            <tr>
              <td>Email</td>
              <td>${employee.email}</td>
            </tr>
            <tr>
              <td>Date of birth</td>
              <td>${employee.dateOfBirth}</td>
            </tr>
            <tr>
              <td>Gender</td>
              <td>${employee.genderType.toString()}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <%--      Friends --%>
      <div class="card my-5 border-0">
        <div class="card-header pl-3 pb-2"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <a class="text-white text-decoration-none"
             href="${pageContext.request.contextPath}/friends?id=${employee.login}">Friends</a>
        </div>
        <div class="card-body p-0">
          <div class="d-flex flex-wrap px-2 pt-3 pb-2 m-0 border border-top-0 border-primary"
               style="background-color: white; border-radius: 0 0 0.5rem 0.5rem; min-height: 100px;">
            <c:forEach var="friend" items="${fiveFriends}">
              <div class="my-0 mx-3 p-0">
                <a href="${pageContext.request.contextPath}/index?id=${friend.login}">
                  <div class="rounded"
                       style="max-width: 100px; max-height: 100px; width: 100px; height: 100px;
                               background-image: url(${controller.getAvatarForEmployee(friend)});
                               background-repeat: no-repeat; background-size: cover; background-position: 50% 50%;">
                  </div>
                </a>
                <p class="p-0 m-0 text-center" style="font-size: 12px">${friend.login}</p>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
      <%--      Wall --%>
      <div class="card my-5 border-0">
        <div class="card-header pl-3 pb-2"
             style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
          <h5 class="card-title text-white" style="font-size: 1rem">Wall</h5>
        </div>
        <div class="card-body p-0 border border-top-0 border-primary" style="border-radius: 0 0 0.5rem 0.5rem;">
          <div class="row p-0 m-0 mt-3">
            <div class="col-12">
              <form action="${pageContext.request.contextPath}/index/comment?from=${sessionUser.login}&to=${employee.login}"
                    method="post">
                <div class="form-group">
                  <label for="comment"></label>
                  <textarea class="form-control" rows="5" id="comment" name="text"
                            style="min-height: 100px; max-height: 100px"></textarea>
                </div>
                <button type="submit" class="btn btn-sm btn-primary float-right">Leave comment</button>
              </form>
            </div>
          </div>
          <div class="row p-0 m-0">
            <div class="col-12 p-2">
              <div class="d-flex flex-column-reverse px-2 pt-0 pb-2 m-0 border-0"
                   style="background-color: white; border-radius: 0 0 0.5rem 0.5rem;">
                <%-- Comments --%>
                <c:forEach var="comment" items="${controller.getCommentsFor(employee)}">
                  <form action="${pageContext.request.contextPath}/index/deleteComment?id=${employee.login}&commentId=${comment.id}"
                        method="post">
                    <div class="row mt-3 pt-2 border-top">
                      <div class="col-6">
                        <p class="mb-1">
                          From: <a class="text-decoration-none text-primary"
                                   href="${pageContext.request.contextPath}/index?id=${comment.fromEmployeeLogin}">${comment.fromEmployeeLogin}</a>
                        </p>
                      </div>
                      <div class="col-6 text-right">
                        <p class="mb-1">Date: ${comment.commentDate}</p>
                      </div>
                      <div class="col-12">
                        <div class="m-0 p-2 border rounded-sm" style="min-height: 75px; max-height: 100px">
                          <p class="m-0 p-0">
                            <c:if test="${comment.content ne null}">
                              ${comment.content.trim()}
                            </c:if>
                          </p>
                        </div>
                      </div>
                      <c:if test="${(comment.toEmployeeLogin == sessionUser.login) or (comment.fromEmployeeLogin == sessionUser.login)}">
                        <div class="col-12">
                          <button type="submit" class="btn btn-sm bg-white float-right text-danger border-0">Delete
                          </button>
                        </div>
                      </c:if>
                    </div>
                  </form>
                </c:forEach>
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
  .links {
    color: #e1e1e1;
    font-size: 1rem;
  }

  .links:hover {
    color: white;
  }
</style>
</html>