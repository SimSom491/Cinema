import org.example.dao.FoglalasDao;
import org.example.dao.VetitesDao;
import org.example.model.Foglalas;
import org.example.model.Szek;
import org.example.model.Vetites;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/DeleteFoglalas")
public class DeleteFoglalas extends HttpServlet {


    int vetitesId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        vetitesId = Integer.parseInt(req.getParameter("vettem"));
        FoglalasDao fog = new FoglalasDao();
        VetitesDao v = new VetitesDao();
        Vetites vetit = v.keres(vetitesId);
        if (vetit.getIdopont().isAfter(LocalDate.now().plusDays(1))) {
            fog.deleteSzekFoglal(vetitesId, (String) req.getSession().getAttribute("currentUser"), Integer.parseInt(req.getParameter("szek")));
            req.setAttribute("success", "Sikeres törlés!");
            getServletConfig().getServletContext().getRequestDispatcher("/pages/mainpage.jsp").forward(req, resp);
        } else {
            req.setAttribute("success", "Sikertelen törlés, csak 24órával az előadás kezdete előtt lehet törölni!");
            getServletConfig().getServletContext().getRequestDispatcher("/pages/mainpage.jsp").forward(req, resp);

        }


    }
}
