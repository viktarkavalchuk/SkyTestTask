package com.skytecgames.task.service;

import com.skytecgames.task.dao.ClanDAOImpl;
import com.skytecgames.task.dao.TaskDAOImpl;
import com.skytecgames.task.dao.interfaces.ClanDAO;
import com.skytecgames.task.dao.interfaces.TaskDAO;
import com.skytecgames.task.model.Clan;
import com.skytecgames.task.model.Task;

import java.util.concurrent.TimeUnit;

public class TaskService extends AbstractDB {
    private ClanDAO clans = ClanDAOImpl.getInstance();
    private TaskDAO tasks = TaskDAOImpl.getInstance();

    private static TaskService instance;

    public static TaskService getInstance(){
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public boolean completeTask(long clanId, long taskId) {
        if (getTask(taskId).execute()) {
            synchronized (getDbMutex())
            {
                Clan clan = clans.getClan(clanId);
                clan.setGold(clan.getGold() + tasks.getTask(taskId).getPrice());
                try {
                    TimeUnit.MILLISECONDS.sleep(500); // эмуляция длительного выполнения данного метода для проверки консистентности данных
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clans.save(clan);
                System.out.println("The quest: " + tasks.getTask(taskId).getTaskName() +
                        " was successfully completed by the " + clan.getName());
            }
            return true;
        } else
            return false;
    }
    public Task getTask(long taskId) {
        return tasks.getTask(taskId);
    }
}
