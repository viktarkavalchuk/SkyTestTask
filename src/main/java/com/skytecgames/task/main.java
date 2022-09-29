package com.skytecgames.task;

import com.skytecgames.task.service.ClanServiceImpl;
import com.skytecgames.task.service.TaskServiceImpl;
import com.skytecgames.task.service.UserAddGoldService;
import com.skytecgames.task.service.UserServiceImpl;
import com.skytecgames.task.service.interfaces.ClanService;
import com.skytecgames.task.service.interfaces.TaskService;
import com.skytecgames.task.service.interfaces.UserService;

public class main {
    public static void main(String[] args) {
        ClanService clans = ClanServiceImpl.getInstance();
        UserService users = UserServiceImpl.getInstance();
        TaskService tasks = TaskServiceImpl.getInstance();

        UserAddGoldService userAddGoldService = new UserAddGoldService();
        try{
            userAddGoldService.addGoldToClan(2, 4, -50);
        } catch (IllegalArgumentException e) {
            System.out.println("Значение не может быть меньше нуля");
        }

        tasks.completeTask(4, 4);
    }
}
