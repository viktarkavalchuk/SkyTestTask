package com.skytecgames.task.service.interfaces;

import com.skytecgames.task.model.User;

public interface UserService {
    User getUser(long idUser);
    boolean save(User user);
}
