package org.example.dao;

import org.example.config.Configuration;
import org.example.model.Foglalas;
import org.example.model.Szek;
import org.example.model.Terem;
import org.example.model.Vetites;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FoglalasDao implements MoziDao<Foglalas> {
    private static final String INSERT_FOGLALAS = "INSERT INTO FOGLALAS(FOGLALONEV,VETITESID,SZEKID) VALUES (?,?,?)";
    private static final String SEARCH_SZEK = "SELECT * FROM SZEK WHERE SORSZAM=? AND OSZLOPSZAM=?";
    private static final String SELECT_VETITES_SZEKEIM = "SELECT SZEK.ID AS id,SZEK.SORSZAM AS sorszam, SZEK.OSZLOPSZAM AS oszlopszam FROM SZEK,FOGLALAS WHERE FOGLALAS.FOGLALONEV=? AND FOGLALAS.VETITESID=? AND SZEK.ID=FOGLALAS.SZEKID";
    private static final String UPDATE_FOGLALAS = "UPDATE FOGLALAS SET SzekID=? WHERE VETITESID=? AND FOGLALONEV=? AND SZEKID=?";
    private static final String SELECT_ALL_SZEK = "SELECT SZEK.ID AS id, SZEK.SORSZAM AS sorszam,SZEK.OSZLOPSZAM AS oszlopszam FROM FOGLALAS,SZEK WHERE vetitesid=? AND SZEK.ID=FOGLALAS.SZEKID";
    private static final String SELECT_SZEMELY_FOGLALAS = "SELECT VETITES.ID AS id, VETITES.TEREMID AS teremid, VETITES.FILMID AS filmid, VETITES.IDOPONT AS idopont, VETITES.ORA AS ora FROM VETITES,FOGLALAS WHERE VETITES.ID=FOGLALAS.VETITESID AND FOGLALAS.FOGLALONEV=? group by VETITES.ID";
    private static final String DELETE_FOGLALAS = "DELETE FROM FOGLALAS WHERE VETITESID=? AND FOGLALONEV=? AND SZEKID=?";
    private static final String SELECT_ALL_FOGLALAS = "SELECT * FROM FOGLALAS";
    private static final String UPDATE_DESC_FOGLALAS = "UPDATE FOGLALAS SET FOGLALONEV=?, VETITESID=?, SZEKID=? WHERE ID=?";

    private final FilmDao filmDao = new FilmDao();
    private final TeremDao teremDao = new TeremDao();

    private final Properties props = new Properties();
    private final String connectionURL;

    public FoglalasDao() {
        this.connectionURL = Configuration.getValue("db.url");
    }

    @Override
    public List<Foglalas> listaz() {
        List<Foglalas> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_FOGLALAS)) {
            while (rs.next()) {
                Foglalas uj = new Foglalas();
                uj.setId(rs.getInt("id"));
                uj.setFoglaloNev(rs.getString("foglalonev"));
                uj.setVetitesId(rs.getInt("vetitesid"));
                uj.setSzekId(rs.getInt("szekid"));
                res.add(uj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public Foglalas keres(int id) {
        return null;
    }

    @Override
    public Foglalas hozzaad(Foglalas type) {
        try (Connection c = DriverManager.getConnection(connectionURL)

        ) {
            PreparedStatement stmt;
            stmt = c.prepareStatement(INSERT_FOGLALAS);

            stmt.setString(1, type.getFoglaloNev());
            stmt.setInt(2, type.getVetitesId());
            stmt.setInt(3, type.getSzekId());


            int hatottsorok = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    public List<Szek> szekek(int id) {
        List<Szek> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_SZEK)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Szek uj = new Szek();
                uj.setId(rs.getInt("id"));
                uj.setOszlopSzam(rs.getInt("oszlopszam"));
                uj.setSorSzam(rs.getInt("sorszam"));
                res.add(uj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public Szek szekKeres(int sor, int oszlop) {
        List<Szek> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(SEARCH_SZEK)) {
            stmt.setInt(1, sor);
            stmt.setInt(2, oszlop);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Szek uj = new Szek();
                uj.setId(rs.getInt("id"));
                uj.setOszlopSzam(rs.getInt("oszlopszam"));
                uj.setSorSzam(rs.getInt("sorszam"));
                res.add(uj);
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res.get(0);
    }

    @Override
    public void delete(int id) {

    }

    public List<Vetites> szemelyKeres(String szemely) {
        List<Vetites> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(SELECT_SZEMELY_FOGLALAS)) {
            stmt.setString(1, szemely);
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
        return res;
    }

    public List<Szek> szekeim(String currentUser, int vetitesId) {
        List<Szek> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(SELECT_VETITES_SZEKEIM)) {
            stmt.setString(1, currentUser);
            stmt.setInt(2, vetitesId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Szek uj = new Szek();
                uj.setId(rs.getInt("id"));
                uj.setOszlopSzam(rs.getInt("oszlopszam"));
                uj.setSorSzam(rs.getInt("sorszam"));
                res.add(uj);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public void deleteSzekFoglal(int vetitesId, String currentUser, int szek) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_FOGLALAS)
        ) {
            stmt.setInt(1, vetitesId);
            stmt.setString(2, currentUser);
            stmt.setInt(3, szek);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void helyfrissit(int vetitesid, int szekid, String foglalo, int regiszek) {


        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(UPDATE_FOGLALAS)) {
            stmt.setInt(1, szekid);
            stmt.setInt(2, vetitesid);
            stmt.setString(3, foglalo);
            stmt.setInt(4, regiszek);
            int hatottsorok = stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Foglalas descfoglalasFrissit(Foglalas fog) {

        try (Connection c = DriverManager.getConnection(connectionURL)

        ) {
            PreparedStatement stmt;

                stmt = c.prepareStatement(UPDATE_DESC_FOGLALAS);
                stmt.setInt(4, fog.getId());

            stmt.setString(1, fog.getFoglaloNev());
            stmt.setInt(2, fog.getVetitesId());
            stmt.setInt(3, fog.getSzekId());


            int hatottsorok = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fog;
    }

}
