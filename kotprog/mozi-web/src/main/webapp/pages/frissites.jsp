<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
    <thead>
    <tr>
        <th>terem: <c:out value="${requestScope.terem.id}"/></th>
        <th style="font-size: 20px" colspan=
            <c:out value="${requestScope.terem.oszlopok}"/>>Vászon
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="j" begin="1" end="${requestScope.terem.sorok}">
        <tr>
            <td>Sor: <c:out value="${j}"/></td>
            <c:forEach var="i" begin="1" end="${requestScope.terem.oszlopok}">
                <td>
                    <c:set var="fog" scope="session" value="${false}"/>
                    <c:forEach items="${requestScope.foglalt}" var="szek">
                        <c:if test="${szek.oszlopSzam == i && szek.sorSzam==j}">
                            <span style="color: red"><c:out value="${i}"/></span>
                            <c:set var="fog" scope="session" value="${true}"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${!fog}">
                        <span style="color: green"><c:out value="${i}"/></span>
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="modositom" method="post">
    <div class="form-group">
        <label for="sorszam">Sorszám:</label>
        <input min="1" max=
        <c:out value="${requestScope.terem.sorok}"/> required name="sorszam" type="number" class="form-control"
               id="sorszam"/>
        <label for="oszlopszam">Oszlopszám:</label>
        <input min="1" max=
        <c:out value="${requestScope.terem.oszlopok}"/> required name="oszlopszam" type="number" class="form-control"
               id="oszlopszam"/>
    </div>
    <input type="hidden" name="wet" value=<c:out value="${requestScope.vetitesid}"/>>
    <input type="hidden" name="szekid" value=<c:out value="${requestScope.szekem}"/>>
    <button id="submit" name="submit" type="submit" class="btn btn-primary">Módosítom a foglalástt</button>
</form>