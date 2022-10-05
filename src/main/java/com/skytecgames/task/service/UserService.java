package com.skytecgames.task.service;

import com.skytecgames.task.dao.UserDAOImpl;
import com.skytecgames.task.dao.interfaces.UserDAO;
import com.skytecgames.task.model.Clan;
import com.skytecgames.task.model.User;

import java.util.ArrayList;

public class UserService extends AbstractDB { // пользователь добавляет золото из собственного кармана
    private ClanService clansService = ClanService.getInstance();
    private UserDAO users = UserDAOImpl.getInstance();
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean addGoldToClan(long userId, long clanId, int gold) {

        if (gold < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        synchronized (getDbMutex()) {
            Clan clan = clansService.getClan(clanId);
            User user = users.getUser(userId);

            ArrayList<Clan> list = user.getClan();
            if (list.stream().anyMatch(x -> x.getName().equals(clan.getName()))) {
                if (user.getUserGold() >= gold) {
                    user.setUserGold(user.getUserGold() - gold);
                    clan.setGold(clan.getGold() + gold);
                    users.save(user);
                    clansService.save(clan);
                    System.out.println(gold + " gold transferred to the " + clan.getName() + " from the user " + user.getName());
                    return true;
                } else {
                    System.out.println("User " + user.getName() + " doesn't have enough gold");
                    return false;
                }
            } else {
                System.out.println(user.getName() + " is not a member of this clan: " + clan.getName());
                return false;
            }
        }
    }
    public User getUser(long userId) {
        return users.getUser(userId);
    }
    public boolean save(User user) {
        return users.save(user);
    }
}