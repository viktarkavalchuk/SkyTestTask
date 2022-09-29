package com.skytecgames.task.model;

public class Clan {
    private long id;     // id клана
    private String name; // имя клана
    private int gold;    // текущее количество золота в казне клана

    public Clan(long id, String name, int gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
    }

    public Clan() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}