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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/FrissitFoglalas")
public class FrissitFoglalas extends HttpServlet {

    int valtozo=1;
    int vetitesId;
    int szekID;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        vetitesId=Integer.parseInt(req.getParameter("vettem"));
        szekID=Integer.parseInt(req.getParameter("szek"));
        FoglalasDao fog=new FoglalasDao();
        resp.setContentType("text/html");

        VetitesDao v=new VetitesDao();
        Vetites vetit=v.keres(vetitesId);
        req.setAttribute("vetitesid",vetitesId);
        req.setAttribute("szekem",szekID);
        FoglalasDao dao=new FoglalasDao();
        List<Szek>szekek= dao.szekek(vetitesId);

        List<Szek> sajatok = dao.szekeim((String) req.getSession().getAttribute("currentUser"),vetitesId);
        req.setAttribute("foglalasaim",sajatok);
        req.setAttribute("terem",vetit.getTerem());
        req.setAttribute("foglalt",szekek);
        req.setAttribute("szemelyek",valtozo);
        getServletConfig().getServletContext().getRequestDispatcher("/pages/frissites.jsp").forward(req,resp);

    }
}
