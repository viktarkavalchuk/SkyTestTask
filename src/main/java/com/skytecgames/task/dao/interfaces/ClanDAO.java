package com.skytecgames.task.dao.interfaces;

import com.skytecgames.task.model.Clan;

public interface ClanDAO {
    Clan getClan(long clanId);
    boolean save(Clan clan);
}