import org.example.dao.FoglalasDao;
import org.example.dao.VetitesDao;
import org.example.model.Szek;
import org.example.model.Vetites;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/foglalas")
public class Foglalok extends HttpServlet {
    int valtozo = 1;
    int vetitesId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> map = req.getParameterMap();
        vetitesId = Integer.parseInt(map.get("vetitesiddd")[0]);
        if (map.containsKey("inc")) {
            valtozo += 1;
        }
        if (map.containsKey("dec")) {
            if (valtozo > 1) {
                valtozo -= 1;
            }

        }
        req.setAttribute("vetitesid", vetitesId);
        VetitesDao v = new VetitesDao();
        Vetites vetit = v.keres(vetitesId);
        FoglalasDao dao = new FoglalasDao();
        List<Szek> szekek = dao.szekek(vetitesId);
        req.setAttribute("terem", vetit.getTerem());
        req.setAttribute("foglalt", szekek);
        req.setAttribute("szemelyek", valtozo);
        getServletConfig().getServletContext().getRequestDispatcher("/pages/foglalas.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        vetitesId = Integer.parseInt(req.getParameter("vetites"));
        VetitesDao v = new VetitesDao();
        Vetites vetit = v.keres(vetitesId);
        req.setAttribute("vetitesid", vetitesId);
        FoglalasDao dao = new FoglalasDao();
        List<Szek> szekek = dao.szekek(vetitesId);

        req.setAttribute("terem", vetit.getTerem());
        req.setAttribute("foglalt", szekek);
        req.setAttribute("szemelyek", valtozo);
        getServletConfig().getServletContext().getRequestDispatcher("/pages/foglalas.jsp").forward(req, resp);
    }
}
