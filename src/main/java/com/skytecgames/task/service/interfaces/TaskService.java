package com.skytecgames.task.service.interfaces;

import com.skytecgames.task.model.Task;

public interface TaskService {
    boolean completeTask(long clanId, long taskId);
    Task getTask(long taskId);
}
