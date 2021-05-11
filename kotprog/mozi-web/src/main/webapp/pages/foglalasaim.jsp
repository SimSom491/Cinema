<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <style>
        table {
            margin: auto;
        }

        table, td {
            border: 2px solid black;
        }

        td {
            text-align: center;
            font-size: 1.4rem;
            padding: 15px;
        }
    </style>
</head>

<body>
<div style="text-align: right">
    <p>Bejelentkezve mint:</p>
    <p>
        <c:out value="${sessionScope.currentUser}"/>
    </p>
    <a href="notLogin">Vissza a főmenübe.</a>
    <br>
    <a href="/mozi_web_war">Kilépés.</a>


</div>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">TeremSzám</th>
            <th scope="col">FilmCím</th>
            <th scope="col">Nap</th>
            <th scope="col">Időpont</th>
            <th scope="col">Foglalás megtekintése</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.vetesek}">
            <tr>
                <td>${item.terem.id}</td>
                <td>${item.film.cim}</td>
                <td>${item.idopont}</td>
                <td>${item.ora}</td>
                <td>
                    <a href="/mozi_web_war/vetitfoglal?vetites=${item.id}">Részletek<i class="fas fa-edit"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>