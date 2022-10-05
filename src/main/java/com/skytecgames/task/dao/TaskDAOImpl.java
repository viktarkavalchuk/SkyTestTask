package com.skytecgames.task.dao;

import com.skytecgames.task.dao.interfaces.TaskDAO;
import com.skytecgames.task.model.Task;
import com.skytecgames.task.utils.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskDAOImpl implements TaskDAO { // какой-то сервис с заданиями
    private static TaskDAOImpl instance;

    public static TaskDAOImpl getInstance() {
        if (instance == null) {
            instance = new TaskDAOImpl();
        }
        return instance;
    }

    @Override
    public Task getTask(long taskId) {
        MySQLConnectionPool pool = new MySQLConnectionPool();
        String sql = "SELECT * FROM task WHERE idTask = " + taskId;
        Task task = null;
        try (Connection conn = pool.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String taskName = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    task = new Task(id, taskName, price);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return task;
    }
}
