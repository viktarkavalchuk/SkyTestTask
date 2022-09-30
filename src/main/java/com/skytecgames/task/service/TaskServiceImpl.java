package com.skytecgames.task.service;

import com.skytecgames.task.model.Clan;
import com.skytecgames.task.model.Task;
import com.skytecgames.task.service.interfaces.ClanService;
import com.skytecgames.task.service.interfaces.TaskService;
import com.skytecgames.task.utils.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskServiceImpl implements TaskService { // какой-то сервис с заданиями

    private static TaskServiceImpl instance;
    private ClanService clans;

    public static TaskServiceImpl getInstance() {
        if (instance == null) {
            instance = new TaskServiceImpl();
        }
        return instance;
    }

    public TaskServiceImpl() {
        this.clans = ClanServiceImpl.getInstance();
    }

    public boolean completeTask(long clanId, long taskId) {
        TaskService tasks = TaskServiceImpl.getInstance();
        if (getTask(taskId).execute()) {
            System.out.println("Quest started");
            Clan clan = clans.getClan(clanId);
            clan.setGold(clan.getGold() + tasks.getTask(taskId).getPrice());
            clans.save(clan);
            System.out.println("Quest " + tasks.getTask(taskId).getTaskName() +  " completed successfully");
            return true;
        } else
            return false;
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
                    Integer price = resultSet.getInt(3);
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
