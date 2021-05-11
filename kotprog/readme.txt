Desctop:
    Javafx:run után egy menü fogadja a dolgozót ahol kiválaszthatja, hogy melyik tábla adatait akarja módosítani. Egy gomb megnyomása után ha vissza akarunk jutni a főmenübe akkor azt a file/vissza a fömenübe gombal teheti meg.
    Adat felvitele az adatbázisba mindegyik oldalon az edit/...hozzáadása.


    Követelmények:
        Terem: A termek oldalon található az elrendezés webes felületen látszik, a székek számozása egy új terem létrehozásakor mindíg automatikusan történik

        Filmek: A filmek oldalon található, ezen az oldalon van megoldva a keresés két input mezővel az oldal alján.

        Vetítések: A vetítések oldalon található, a jegyárak mivel nem kellet letárolni adatbázisban a webes felületen kalkulálódnak egy bizonyos képlet alapján.

        Foglalás: Erre vonatkozóan csak frissítés van megoldva a listázás mellet, mivel a feladat csak ezt kérte.

Webes felület:
    Tomcat-es futtatás után böngészőbe beírva a "http://localhost:8080/mozi_web_war/"-linket (legalábbis nálam gondolom akkor amott is) megjelenik a kezdő felület.
    A felhasználó beírja a nevét, majd ez session-ben tárolódva navigálhat az oldalak közt. A főldalon két gomb áll rendelkezésre egy a foglalásnak, egy pedig az eddig foglalt ülőhelyek listázásának. Valamint van egy Kilépés gomb is a kijelentkezéshez minden oldalon.

    Követelmények:
        A vetítésre kattintva kilistázódnak a vetítések, mely lisából kiválasztva adott vetítésre lehet helyet foglalni ahol látja az adott foglaltsági állapotot.

        A foglalásaim gombra kattintva az egyes filmekre foglalt helyek vállnak láthatóvá keresésre a ctrl+f billentyűkombináció áll rendelkezésre. A vetítés kiválasztása után a felhasználó elé tárul az arra a vetítésre foglalt összes helye valamint az ára, emellett egy összesített ár is található. A foglalásokat lehet módosítani. A felhasználó a vetítés előtt 24 órával tudja csak lemondani foglalást, módosítani viszont tudja.