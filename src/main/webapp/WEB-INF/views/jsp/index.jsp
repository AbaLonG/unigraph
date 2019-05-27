<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="text/html">
<head>
    <title>Unigraph</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body style="background-color: ghostwhite">
<div class="container">
    <nav class="navbar navbar-expand-sm p-1"
         style="border-bottom-left-radius: 0.5rem; border-bottom-right-radius: 0.5rem; background: linear-gradient(to bottom, #1448b4, #4d7acd);">
        <div class="row align-items-center w-100">
            <div class="col-sm-2">
                <a href="${pageContext.request.contextPath}/index" class="navbar-brand text-white ml-3 font-weight-light"
                   style="font-size: 1.5rem; font-family: 'Segoe UI', sans-serif">Unigraph</a>
            </div>
            <div class="col-sm pr-1">
                <div class="d-flex text-white align-items-center">
                    <a class="p-0 pt-1 px-2 text-decoration-none links" href="#">Link 1</a>
                    <a class="p-0 pt-1 px-2 text-decoration-none links" href="#">Link 2</a>
                    <a class="p-0 pt-1 px-2 text-decoration-none links" href="#">Link 3</a>
                    <form method="post" class="p-0 m-0 ml-auto" action="${pageContext.request.contextPath}/settings/logout">
                        <button type="submit" class="btn btn-sm btn-light text-primary">Log Out</button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
    <div class="row">
        <div class="col-sm-4">
            <div class="card border-0" style="background-color: ghostwhite">
                <a href="#">
                    <img class="card-img-top rounded-lg" src="resources/img/img_avatar_man.png"
                         alt="Card image">
                </a>
                <div class="card-body">
                    <h4 class="card-title">${employee.firstName} ${employee.lastName}</h4>
                    <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <h2>TITLE HEADING</h2>
            <h5>Title description, Dec 7, 2017</h5>
            <div class="fakeimg">Fake Image</div>
            <p>Some text..</p>
            <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod
                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                ullamco.</p>
            <br>
            <h2>TITLE HEADING</h2>
            <h5>Title description, Sep 2, 2017</h5>
            <div class="fakeimg">Fake Image</div>
            <p>Some text..</p>
            <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod
                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                ullamco.</p>
        </div>
    </div>
</div>
</body>
<style>
    .fakeimg {
        height: 200px;
        background-color: #aaa;
    }

    .links {
        color: #ffffff;
        font-size: 1rem;
    }

    .links:hover {
        color: white;
        font-weight: bolder;
    }
</style>
</html>