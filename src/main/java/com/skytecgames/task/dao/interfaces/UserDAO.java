package com.skytecgames.task.dao.interfaces;

import com.skytecgames.task.model.User;

public interface UserDAO {
    User getUser(long idUser);
    boolean save(User user);
}
