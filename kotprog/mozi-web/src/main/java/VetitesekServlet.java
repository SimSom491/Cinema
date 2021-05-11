import org.example.dao.VetitesDao;
import org.example.model.Vetites;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listVetites")
public class VetitesekServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        VetitesDao vetit=new VetitesDao();
        List<Vetites> vetitesek=vetit.listaz();

        req.setAttribute("vetitesek",vetitesek);
        getServletConfig().getServletContext().getRequestDispatcher("/pages/vetitesek.jsp").forward(req,resp);
    }


}
