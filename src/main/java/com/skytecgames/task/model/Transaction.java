package com.skytecgames.task.model;

import java.util.Date;

public class Transaction {
    private long id; //id транзакции
    private Date date; //дата транзакции
    private long userId; //id пользователя, совершившего перевод
    private long clanId; //id клана, получившего перевод
    private int gold; //количество золота, поступившего в казну клана

    public Transaction() {
    }

    public Transaction(Date date, long userId, long clanId, int gold) {
        this.id = Long.parseLong(null);
        this.date = date;
        this.userId = userId;
        this.clanId = clanId;
        this.gold = gold;
    }

    public Transaction(long id, Date date, long userId, long clanId, int gold) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.clanId = clanId;
        this.gold = gold;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getClanId() {
        return clanId;
    }

    public void setClanId(int clanId) {
        this.clanId = clanId;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
