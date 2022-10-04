package com.skytecgames.task.dao;

import com.skytecgames.task.dao.interfaces.ClanDAO;
import com.skytecgames.task.model.Clan;
import com.skytecgames.task.utils.MySQLConnectionPool;

import java.sql.*;

public class ClanDAOImpl implements ClanDAO {
    private static ClanDAOImpl instance;

    public static ClanDAOImpl getInstance() {
        if (instance == null) {
            instance = new ClanDAOImpl();
        }
        return instance;
    }

    @Override
    public Clan getClan(long clanId) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM clan WHERE idClan = " + clanId;
        Clan clan = null;
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Long idClan = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    int gold = resultSet.getInt(3);
                    clan = new Clan(idClan, name, gold);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clan;
    }

    @Override
    public synchronized boolean save(Clan clan) {
        Clan clanUpdate = null;
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM clan WHERE name = '" + clan.getName() + "'";
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Long idClan = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    int gold = resultSet.getInt(3);
                    clanUpdate = new Clan(idClan, name, gold);
                }
                clanUpdate.setName(clan.getName());
                clanUpdate.setGold(clan.getGold());

                PreparedStatement updateStatement = conn
                        .prepareStatement("UPDATE clan SET idClan = ?, name = ?, gold = ? WHERE idClan = ?;");

                updateStatement.setLong(1, clanUpdate.getId());
                updateStatement.setString(2, clanUpdate.getName());
                updateStatement.setInt(3, clanUpdate.getGold());
                updateStatement.setLong(4, clanUpdate.getId());
                updateStatement.executeUpdate();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
