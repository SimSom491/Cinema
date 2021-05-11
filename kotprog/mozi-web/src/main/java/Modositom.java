import org.example.dao.FoglalasDao;
import org.example.model.Szek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/modositom")
public class Modositom extends HttpServlet {
    FoglalasDao dao = new FoglalasDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        boolean foglalt = false;
        int vetitesId = Integer.parseInt(map.get("wet")[0]);
        int szekId = Integer.parseInt(map.get("szekid")[0]);
        int sorszam = Integer.parseInt(map.get("sorszam")[0]);
        int oszlopszam = Integer.parseInt(map.get("oszlopszam")[0]);
        Szek uj = dao.szekKeres(sorszam, oszlopszam);


        List<Szek> szekek = dao.szekek(vetitesId);
        for (Szek szek : szekek) {
            if (szek.getSorSzam() == sorszam && szek.getOszlopSzam() == oszlopszam) {
                foglalt = true;
                break;
            }
        }
        if (foglalt) {
            req.setAttribute("success", "Sikertelen módosítás, olyan helyet adott meg ami már foglalt volt!");
            getServletConfig().getServletContext().getRequestDispatcher("/pages/mainpage.jsp").forward(req, resp);
        } else {
            dao.helyfrissit(vetitesId, uj.getId(), (String) req.getSession().getAttribute("currentUser"), szekId);
            req.setAttribute("success", "Sikeres módosítás");
            getServletConfig().getServletContext().getRequestDispatcher("/pages/mainpage.jsp").forward(req, resp);

        }
    }
}
