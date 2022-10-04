package com.skytecgames.task.service;

import com.skytecgames.task.dao.ClanDAOImpl;
import com.skytecgames.task.dao.TaskDAOImpl;
import com.skytecgames.task.dao.interfaces.ClanDAO;
import com.skytecgames.task.dao.interfaces.TaskDAO;
import com.skytecgames.task.model.Clan;
import com.skytecgames.task.model.Task;

public class TaskService {
    private ClanDAO clans = ClanDAOImpl.getInstance();
    private TaskDAO tasks = TaskDAOImpl.getInstance();

    private static TaskService instance;

    public static TaskService getInstance(){
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public synchronized boolean completeTask(long clanId, long taskId) {
        TaskDAO tasks = TaskDAOImpl.getInstance();
        if (tasks.getTask(taskId).execute()) {
            System.out.println("Quest started");
            Clan clan = clans.getClan(clanId);
            clan.setGold(clan.getGold() + tasks.getTask(taskId).getPrice());
            clans.save(clan);
            System.out.println("Quest " + tasks.getTask(taskId).getTaskName() +  " completed successfully");
            return true;
        } else
            return false;
    }
    public Task getTask(long taskId) {
        return tasks.getTask(taskId);
    }
}
