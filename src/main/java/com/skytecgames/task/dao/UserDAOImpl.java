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
    public boolean save(User user) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        try (Connection conn = pool.getConnection()) {
                PreparedStatement updateStatement = conn
                        .prepareStatement("UPDATE user SET name = ?, userGold = ? WHERE idUser = ?;");
                updateStatement.setString(1, user.getName());
                updateStatement.setInt(2, user.getUserGold());
                updateStatement.setLong(3, user.getId());
                updateStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return true;
    }
}
