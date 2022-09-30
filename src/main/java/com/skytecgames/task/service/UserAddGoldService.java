package com.skytecgames.task.service;

import com.skytecgames.task.model.Clan;
import com.skytecgames.task.model.User;
import com.skytecgames.task.service.interfaces.ClanService;
import com.skytecgames.task.service.interfaces.UserService;

import java.util.ArrayList;

public class UserAddGoldService { // пользователь добавляет золото из собственного кармана

    private static UserAddGoldService instance;
    private ClanService clans;
    private UserService users;

    public static UserAddGoldService getInstance() {
        if (instance == null) {
            instance = new UserAddGoldService();
        }
        return instance;
    }

    public UserAddGoldService() {
        this.users = UserServiceImpl.getInstance();
        this.clans = ClanServiceImpl.getInstance();
    }

    public boolean addGoldToClan(long userId, long clanId, int gold) {
        if (gold < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }

        Clan clan = clans.getClan(clanId);
        User user = users.getUser(userId);

        ArrayList<Clan> list = user.getClan();
        if (list.stream().anyMatch(x -> x.getName().equals(clan.getName()))) {
            if (user.getUserGold() >= gold) {
                user.setUserGold(user.getUserGold() - gold);
                clan.setGold(clan.getGold() + gold);
                users.save(user);
                clans.save(clan);
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