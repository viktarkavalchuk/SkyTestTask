package com.skytecgames.task.service;

import com.skytecgames.task.dao.ClanDAOImpl;
import com.skytecgames.task.dao.interfaces.ClanDAO;
import com.skytecgames.task.model.Clan;

public class ClanService extends AbstractDB{
    private ClanDAO clans = ClanDAOImpl.getInstance();
    private static ClanService instance;

    public static ClanService getInstance() {
        if (instance == null) {
            instance = new ClanService();
        }
        return instance;
    }

    public boolean paymentByClan(long clanId, int gold) {
        if (gold < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        synchronized (getDbMutex()) {
        Clan clan = clans.getClan(clanId);
            if ((clan.getGold() - gold) >= 0) {
                clan.setGold(clan.getGold() - gold);
                clans.save(clan);
                System.out.println(clan.getName() + " paid " + gold + " gold");
                return true;
            } else {
                System.out.println("Not enough gold in the treasury of the " + clan.getName() + " clan");
                return false;
            }
        }
    }

    public Clan getClan(long clanId) {
        return clans.getClan(clanId);
    }
    public synchronized boolean save(Clan clan) {
        return clans.save(clan);
    }
}
