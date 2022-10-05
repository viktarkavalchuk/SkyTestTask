package com.skytecgames.task.dao;

import com.skytecgames.task.dao.interfaces.ClanDAO;
import com.skytecgames.task.model.Clan;
import com.skytecgames.task.utils.MySQLConnectionPool;

import java.sql.*;

public class ClanDAOImpl implements ClanDAO  {
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
    public boolean save(Clan clan) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        try (Connection conn = pool.getConnection()) {
                PreparedStatement updateStatement = conn
                        .prepareStatement("UPDATE clan SET name = ?, gold = ? WHERE idClan = ?;");
                updateStatement.setString(1, clan.getName());
                updateStatement.setInt(2, clan.getGold());
                updateStatement.setLong(3, clan.getId());
                updateStatement.executeUpdate();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
    }

