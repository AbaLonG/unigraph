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
                    <a class="p-0 pt-1 px-2 text-decoration-none links" href="#">University</a>
                    <a class="p-0 pt-1 px-2 text-decoration-none links" href="#">Settings</a>
                    <a class="p-0 pt-1 px-2 text-decoration-none links" href="#">Search</a>
                    <form method="post" class="p-0 m-0 ml-auto"
                          action="${pageContext.request.contextPath}/settings/logout">
                        <button type="submit" class="btn btn-sm text-white">Log Out</button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
    <div class="row mw-100 p-0 m-0 justify-content-between">
        <div class="col-sm-3 p-0">
            <div class="card border-0" style="background-color: ghostwhite">
                <div class="p-0 m-0 mh-100 mw-100">
                    <img class="card-img-top p-0 m-0 rounded-0" src="${controller.getAvatarForEmployee(employee)}"
                         alt="Card image">
                </div>
                <div class="card-body p-0">
                    <div class="w-100 bg-primary text-center text-white pointer p-1"
                         style="font-family: 'Segoe UI', sans-serif; font-size: 1.25rem">${employee.login}
                    </div>
                    <c:if test="${employee.login.equals(sessionScope.get('user').login)}">
                        <button type="button" class="btn btn-block btn-sm btn-primary mt-0" data-toggle="modal"
                                data-target="#changeAvatar" style="border-radius: 0 0 0.5rem 0.5rem;">
                            Cange Profile Picture
                        </button>
                    </c:if>
                    <c:if test="${!employee.login.equals(sessionScope.get('user').login) && userFriends != null && !userFriends.contains(employee)}">
                        <form class="p-0 m-0" method="post"
                              action="${pageContext.request.contextPath}/index/subscribe?id=${employee.login}">
                            <input type="submit" class="btn btn-block btn-sm btn-primary mt-0" value="Add a friend"
                                   style="border-radius: 0 0 0.5rem 0.5rem;">
                        </form>
                    </c:if>
                    <c:if test="${!employee.login.equals(sessionScope.get('user').login) && userFriends != null && userFriends.contains(employee)}">
                        <form class="p-0 m-0" method="post"
                              action="${pageContext.request.contextPath}/index/unsubscribe?id=${employee.login}">
                            <input type="submit" class="btn btn-block btn-sm btn-primary mt-0"
                                   value="Remove from friends"
                                   style="border-radius: 0 0 0.5rem 0.5rem;">
                        </form>
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
        <div class="col-sm-9 p-0">
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
                            <td>Faculty</td>
                            <td>${employee.group.cathedra.faculty.title}</td>
                        </tr>
                        <tr>
                            <td>Cathedra</td>
                            <td>${employee.group.cathedra.title}</td>
                        </tr>
                        <tr>
                            <td>Group</td>
                            <td>${employee.group.title}</td>
                        </tr>
                        <tr>
                            <td>Type</td>
                            <td>${employee.type.toString()}</td>
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
            <div class="card my-5 border-0">
                <div class="card-header pl-3 pb-2"
                     style="background: linear-gradient(to bottom, #285EBE, #396dc3)">
                    <a class="text-white text-decoration-none" href="#">Friends</a>
                </div>
                <div class="card-body p-0">
                    <div class="d-flex flex-row p-0 m-0 border border-top-0 border-primary"
                         style="background-color: ghostwhite; border-radius: 0 0 0.5rem 0.5rem">
                        <c:forEach var="friend" items="${fiveFriends}">
                            <div class="m-3 p-0" style="display: block">
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