import org.example.dao.FoglalasDao;
import org.example.model.Foglalas;
import org.example.model.Szek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/lefoglalom")
public class Lefoglalom extends HttpServlet {
    FoglalasDao dao = new FoglalasDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        int hany = 1;
        int sor = 0;
        int i = 1;
        boolean foglalt = false;
        int vetitesId = Integer.parseInt(map.get("wet")[0]);
        List<Szek> uj = new ArrayList<>();
        for (Map.Entry<String, String[]> stringEntry : map.entrySet()) {

            if (stringEntry.getKey().equals(i + "sor") || stringEntry.getKey().equals(i + "oszlop")) {
                if (hany != 2) {
                    sor = Integer.parseInt(stringEntry.getValue()[0]);
                    hany++;
                } else {
                    hany = 1;
                    i++;
                    uj.add(dao.szekKeres(sor, Integer.parseInt(stringEntry.getValue()[0])));
                }
            }
        }
        List<Szek> szekek = dao.szekek(vetitesId);
        for (Szek foglalando : uj) {
            for (Szek szek : szekek) {
                if (szek.getSorSzam() == foglalando.getSorSzam() && szek.getOszlopSzam() == foglalando.getOszlopSzam()) {
                    foglalt = true;
                    break;
                }
            }
        }
        if (foglalt) {
            req.setAttribute("success", "Sikertelen foglalás, olyan széket adott meg ami már foglalt volt!");
            getServletConfig().getServletContext().getRequestDispatcher("/pages/mainpage.jsp").forward(req, resp);

        } else {
            for (Szek szek : uj) {
                dao.hozzaad(new Foglalas(0, (String) req.getSession().getAttribute("currentUser"), vetitesId, szek.getId()));
            }
            req.setAttribute("success", "Sikeres foglalás!");
            getServletConfig().getServletContext().getRequestDispatcher("/pages/mainpage.jsp").forward(req, resp);

        }


    }
}
