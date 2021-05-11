package org.example.dao;

import org.example.config.Configuration;
import org.example.model.Vetites;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VetitesDao implements MoziDao<Vetites> {
    private static final String SELECT_ALL_VETITES = "SELECT*FROM VETITES";
    private static final String SEARCH_VETITES = "SELECT*FROM VETITES WHERE id=?";
    private static final String INSERT_VETITES = "INSERT INTO VETITES(TEREMID,FILMID,IDOPONT,ORA) VALUES (?,?,?,?)";
    private static final String UPDATE_VETITES = "UPDATE VETITES SET TEREMID=?, FILMID=?, IDOPONT=?, Ora=? WHERE ID=?";
    private static final String DELETE_VETITES = "DELETE FROM VETITES WHERE ID=?";

    private final Properties props = new Properties();
    private final String connectionURL;
    private final FilmDao filmDao = new FilmDao();
    private final TeremDao teremDao = new TeremDao();

    public VetitesDao() {
        this.connectionURL = Configuration.getValue("db.url");
    }

    @Override
    public List<Vetites> listaz() {
        List<Vetites> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_VETITES)) {
            while (rs.next()) {
                Vetites uj = new Vetites();
                uj.setId(rs.getInt("id"));
                Date date = Date.valueOf(rs.getString("idopont"));
                uj.setIdopont(date == null ? LocalDate.now() : date.toLocalDate());
                uj.setFilm(filmDao.keres(rs.getInt("filmid")));
                uj.setTerem(teremDao.keres(rs.getInt("teremid")));
                uj.setOra(rs.getInt("ora"));
                res.add(uj);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public Vetites keres(int id) {
        List<Vetites> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(SEARCH_VETITES)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vetites uj = new Vetites();
                uj.setId(rs.getInt("id"));
                uj.setFilm(filmDao.keres(rs.getInt("filmid")));
                uj.setTerem(teremDao.keres(rs.getInt("teremid")));
                Date date = Date.valueOf(rs.getString("idopont"));
                uj.setIdopont(date == null ? LocalDate.now() : date.toLocalDate());
                uj.setOra(rs.getInt("ora"));
                res.add(uj);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res.get(0);
    }


    @Override
    public Vetites hozzaad(Vetites type) {
        try (Connection c = DriverManager.getConnection(connectionURL)

        ) {
            PreparedStatement stmt;
            if (type.idProperty().get() == 0) {
                stmt = c.prepareStatement(INSERT_VETITES);
            } else {
                stmt = c.prepareStatement(UPDATE_VETITES);
                stmt.setInt(5, type.getId());
            }
            stmt.setInt(1, type.getTerem().getId());
            stmt.setInt(2, type.getFilm().getId());
            stmt.setString(3, type.getIdopont().toString());
            stmt.setInt(4, type.getOra());


            int hatottsorok = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }


    @Override
    public void delete(int id) {

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_VETITES)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
