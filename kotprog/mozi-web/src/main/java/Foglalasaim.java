import org.example.dao.FoglalasDao;
import org.example.model.Vetites;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/foglalasaim")
public class Foglalasaim extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        FoglalasDao fog = new FoglalasDao();

        List<Vetites> vetitesek = fog.szemelyKeres((String) req.getSession().getAttribute("currentUser"));

        req.setAttribute("vetesek", vetitesek);
        getServletConfig().getServletContext().getRequestDispatcher("/pages/foglalasaim.jsp").forward(req, resp);
    }
}
