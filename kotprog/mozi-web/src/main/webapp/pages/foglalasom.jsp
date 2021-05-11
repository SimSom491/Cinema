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
<div>
    A te helyeid:
    <c:forEach items="${requestScope.foglalasaim}" var="text">
        <p>
            Sorszám: <c:out value="${text.sorSzam}"/> Oszlopszám: <c:out value="${text.oszlopSzam}"/>
            <c:if test="${(text.sorSzam > 3)&&(text.sorSzam<8)}">
                Ár: <c:out value="${requestScope.vetitesar+200}"/>
            </c:if>
            <c:if test="${!(text.sorSzam > 3)||!(text.sorSzam<8)}">
                Ár: <c:out value="${requestScope.vetitesar}"/>
            </c:if>
            <a href="/mozi_web_war/DeleteFoglalas?vettem=${requestScope.vetitesid}&szek=${text.id}">Foglalás törlése</a>
            <a href="/mozi_web_war/FrissitFoglalas?vettem=${requestScope.vetitesid}&szek=${text.id}">Foglalás
                módosítása</a>
        </p>
    </c:forEach>
    <p>Összesen fizetendő: <c:out value="${requestScope.osszar}"/><p>
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


</body>



