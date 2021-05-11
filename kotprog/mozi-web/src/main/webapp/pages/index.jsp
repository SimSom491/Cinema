<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <title>Login</title>
    <meta charset="UTF-8">


</head>
<%
    session.setAttribute("currentUser", "");
%>

<body>
<div class="container">
    <form action="login" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Kérem adja meg a nevét!</label>
            <input name="name" type="text" class="form-control" id="name">
        </div>
        <button type="submit" class="btn btn-primary">Küldés</button>
    </form>
</div>
</body>
</html>
