package org.example.dao;

import org.example.config.Configuration;
import org.example.model.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilmDao implements MoziDao<Film> {
    private static final String SELECT_ALL_FILM = "SELECT * FROM FILM";
    private static final String SEARCH_FILM = "SELECT * FROM FILM WHERE ID = ?";
    private static final String INSERT_FILM = "INSERT INTO FILM(HOSSZ,KORHATAR,RENDEZO,SZEREPLOK,LEIRAS,KEP,KLIP,CIM) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_FILM = "UPDATE FILM SET HOSSZ=?, KORHATAR=?, RENDEZO=?, SZEREPLOK=?, LEIRAS=?, KEP=?, KLIP=?, CIM=? WHERE ID=?";
    private static final String DELETE_FILM = "DELETE FROM FILM WHERE ID=?";

    private final Properties props = new Properties();
    private final String connectionURL;

    public FilmDao() {
        this.connectionURL = Configuration.getValue("db.url");
    }

    @Override
    public List<Film> listaz() {
        List<Film> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_FILM)) {
            while (rs.next()) {
                Film uj = new Film();
                uj.setId(rs.getInt("id"));
                uj.setCim(rs.getString("cim"));
                uj.setHossz(rs.getInt("hossz"));
                uj.setRendezo(rs.getString("rendezo"));
                uj.setKorhatar(rs.getInt("korhatar"));
                uj.setSzereplok(rs.getString("szereplok"));
                uj.setLeiras(rs.getString("leiras"));
                uj.setKep(rs.getString("kep"));
                uj.setKlip(rs.getString("klip"));
                res.add(uj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public Film keres(int id) {
        List<Film> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(SEARCH_FILM)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Film uj = new Film();
                uj.setId(rs.getInt("id"));
                uj.setCim(rs.getString("cim"));
                uj.setHossz(rs.getInt("hossz"));
                uj.setRendezo(rs.getString("rendezo"));
                uj.setKorhatar(rs.getInt("korhatar"));
                uj.setSzereplok(rs.getString("szereplok"));
                uj.setLeiras(rs.getString("leiras"));
                uj.setKep(rs.getString("kep"));
                uj.setKlip(rs.getString("klip"));
                res.add(uj);
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res.get(0);
    }

    @Override
    public Film hozzaad(Film type) {
        try (Connection c = DriverManager.getConnection(connectionURL)

        ) {
            PreparedStatement stmt;
            if (type.getId() == 0) {
                stmt = c.prepareStatement(INSERT_FILM);

            } else {
                stmt = c.prepareStatement(UPDATE_FILM);
                stmt.setInt(9, type.getId());
            }
            stmt.setInt(1, type.getHossz());
            stmt.setInt(2, type.getKorhatar());
            stmt.setString(3, type.getRendezo());
            stmt.setString(4, type.getSzereplok());
            stmt.setString(5, type.getLeiras());
            stmt.setString(6, type.getKep());
            stmt.setString(7, type.getKlip());
            stmt.setString(8, type.getCim());


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
             PreparedStatement stmt = c.prepareStatement(DELETE_FILM)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
