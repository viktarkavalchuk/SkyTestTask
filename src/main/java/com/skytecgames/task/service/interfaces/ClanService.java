package com.skytecgames.task.service.interfaces;

import com.skytecgames.task.model.Clan;

public interface ClanService {
    Clan getClan(long clanId);
    boolean save(Clan clan);
}