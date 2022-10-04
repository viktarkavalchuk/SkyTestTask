package com.skytecgames.task.dao.interfaces;

import com.skytecgames.task.model.Task;

public interface TaskDAO {
    Task getTask(long taskId);
}
