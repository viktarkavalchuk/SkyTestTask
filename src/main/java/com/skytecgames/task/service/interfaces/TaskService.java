package com.skytecgames.task.service.interfaces;

import com.skytecgames.task.model.Task;

public interface TaskService {
    void completeTask(long clanId, long taskId);
    Task getTask(long taskId);
}
