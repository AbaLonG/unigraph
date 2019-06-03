<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="tab-pane container active" id="faculty">...</div>
            <div class="tab-pane container fade" id="cathedra">...</div>
            <div class="tab-pane container fade" id="group">...</div>
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
