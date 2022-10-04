package com.skytecgames.task.dao;

import com.skytecgames.task.dao.interfaces.ClanDAO;
import com.skytecgames.task.dao.interfaces.UserDAO;
import com.skytecgames.task.model.Clan;
import com.skytecgames.task.model.User;
import com.skytecgames.task.utils.MySQLConnectionPool;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    private ClanDAO clanDAO = ClanDAOImpl.getInstance();
    private static UserDAOImpl instance;

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public User getUser(long userId) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT user.idUser, user.name, user.userGold, clanstouser.idClan " +
                "FROM user, clanstouser WHERE user.idUser = " + userId + " AND clanstouser.idUser = " + userId;

        User user = null;

        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                ArrayList<Clan> clans = new ArrayList<>();
                while (resultSet.next()) {
                    Long idUser = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    int userGold = resultSet.getInt(3);
                    int clanId = resultSet.getInt(4);

                    user = new User(idUser, name, userGold);
                    Clan clan = clanDAO.getClan(clanId);
                    clans.add(clan);
                    user.setClan(clans);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public synchronized boolean save(User user) {
        User userUpdate = null;
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM user WHERE name = '" + user.getName() + "'";
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Long idUser = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    Integer userGold = resultSet.getInt(3);
                    userUpdate = new User(idUser, name, userGold);
                }
                userUpdate.setName(user.getName());
                userUpdate.setUserGold(user.getUserGold());

                PreparedStatement updateStatement = conn
                        .prepareStatement("UPDATE user SET idUser = ?, name = ?, userGold = ? WHERE idUser = ?;");

                updateStatement.setLong(1, userUpdate.getId());
                updateStatement.setString(2, userUpdate.getName());
                updateStatement.setInt(3, userUpdate.getUserGold());
                updateStatement.setLong(4, userUpdate.getId());
                updateStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
