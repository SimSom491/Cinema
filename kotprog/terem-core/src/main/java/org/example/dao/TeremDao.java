package org.example.dao;

import org.example.config.Configuration;
import org.example.model.Terem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TeremDao implements MoziDao<Terem> {
    private static final String SELECT_ALL_TEREM = "SELECT*FROM TEREM";
    private static final String SEARCH_TEREM = "SELECT*FROM TEREM WHERE id=?";
    private static final String INSERT_TEREM = "INSERT INTO TEREM(SOROK,OSZLOPOK) VALUES (?,?)";
    private static final String UPDATE_TEREM = "UPDATE TEREM SET SOROK=?, OSZLOPOK=? WHERE ID=?";
    private static final String DELETE_TEREM = "DELETE FROM TEREM WHERE ID=?";
    private static final String SZEK_ADD = "INSERT INTO SZEK(SORSZAM,OSZLOPSZAM) SELECT ?, ? WHERE NOT EXISTS (SELECT * FROM SZEK WHERE SORSZAM=? AND OSZLOPSZAM=?)";

    private final Properties props = new Properties();
    private final String connectionURL;

    public TeremDao() {
        this.connectionURL = Configuration.getValue("db.url");
    }

    @Override
    public List<Terem> listaz() {
        List<Terem> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_TEREM)) {
            while (rs.next()) {
                Terem uj = new Terem();
                uj.setId(rs.getInt("id"));
                uj.setOszlopok(rs.getInt("oszlopok"));
                uj.setSorok(rs.getInt("sorok"));
                res.add(uj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public Terem keres(int id) {
        List<Terem> res = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = connection.prepareStatement(SEARCH_TEREM)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Terem uj = new Terem();
                uj.setId(rs.getInt("id"));
                uj.setOszlopok(rs.getInt("oszlopok"));
                uj.setSorok(rs.getInt("sorok"));
                res.add(uj);
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res.get(0);
    }


    @Override
    public Terem hozzaad(Terem type) {
        try (Connection c = DriverManager.getConnection(connectionURL)

        ) {
            PreparedStatement stmt;
            if (type.getId() == 0) {
                stmt = c.prepareStatement(INSERT_TEREM);
                szekAdd(type.getSorok(), type.getOszlopok());
            } else {
                stmt = c.prepareStatement(UPDATE_TEREM);
                stmt.setInt(3, type.getId());
            }
            stmt.setInt(1, type.getSorok());
            stmt.setInt(2, type.getOszlopok());


            int hatottsorok = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    public void szekAdd(int sor, int oszlop) {
        for (int i = 1; i < sor + 1; i++) {
            for (int j = 1; j < oszlop + 1; j++) {
                try (Connection c = DriverManager.getConnection(connectionURL)

                ) {
                    PreparedStatement stmt;
                    stmt = c.prepareStatement(SZEK_ADD);

                    stmt.setInt(1, i);
                    stmt.setInt(3, i);
                    stmt.setInt(2, j);
                    stmt.setInt(4, j);


                    int hatottsorok = stmt.executeUpdate();
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


    @Override
    public void delete(int id) {

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_TEREM)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
