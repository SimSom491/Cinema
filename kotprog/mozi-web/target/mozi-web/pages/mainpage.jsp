<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>


<div style="text-align: right">
    <p>Bejelentkezve mint:</p>
    <p>
        <c:out value="${sessionScope.currentUser}"/>
    </p>
    <a href="/mozi_web_war">Kilépés.</a>

</div>
<div style="text-align: center; font-size: 20px">
    <c:out value="${requestScope.success}"/>
</div>
<div style="width: 500px; padding: 10px; margin: auto">
    <form action="listVetites"  style=" width: 50%; margin: auto; alignment: center">
        <button type="submit" style="margin: auto 100px;" >
            Vetítések
        </button>
    </form>
</div>
<div style="width: 500px; padding: 10px; margin: auto">
    <form action="foglalasaim" style=" width: 50%; margin: auto; alignment: center">
        <button type="submit" style="margin: auto 92px;">
            Foglalásaim
        </button>
    </form>
</div>
</body>
</html>