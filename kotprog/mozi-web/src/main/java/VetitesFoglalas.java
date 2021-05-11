import org.example.dao.FoglalasDao;
import org.example.dao.TeremDao;
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

@WebServlet("/vetitfoglal")
public class VetitesFoglalas extends HttpServlet {
    int valtozo=1;
    int vetitesId;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ossz;
        ossz=0;
        resp.setContentType("text/html");
        vetitesId=Integer.parseInt(req.getParameter("vetites"));
        VetitesDao v=new VetitesDao();
        Vetites vetit=v.keres(vetitesId);
        req.setAttribute("vetitesid",vetitesId);
        FoglalasDao dao=new FoglalasDao();
        List<Szek>szekek= dao.szekek(vetitesId);
        List<Szek> sajatok = dao.szekeim((String) req.getSession().getAttribute("currentUser"),vetitesId);

        for (Szek szek : sajatok) {
            int alapar=(vetit.getOra()>16)?1200:1000;
            ossz+=((szek.getSorSzam() > 3)&&(szek.getSorSzam()<8))?alapar+200:alapar;
        }

        req.setAttribute("foglalasaim",sajatok);
        req.setAttribute("vetitesar",(vetit.getOra()>16)?1200:1000);
        req.setAttribute("osszar",ossz);
        req.setAttribute("terem",vetit.getTerem());
        req.setAttribute("foglalt",szekek);
        req.setAttribute("szemelyek",valtozo);
        getServletConfig().getServletContext().getRequestDispatcher("/pages/foglalasom.jsp").forward(req,resp);



    }
}
