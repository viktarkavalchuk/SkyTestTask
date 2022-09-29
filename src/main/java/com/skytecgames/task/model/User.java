package com.skytecgames.task.model;

import java.util.ArrayList;

public class User {
    private long id;     // id пользователя
    private String name; // имя пользователя
    private int userGold; // количество золота у пользователя
    private ArrayList<Clan> clan; // принадлежность пользователя к кланам

    public User() {
    }

    public User(long id, String name, int userGold) {
        this.id = id;
        this.name = name;
        this.userGold = userGold;
        this.clan = new ArrayList();
    }

    public long getId() {
        return id;
    }

    public int getUserGold() {
        return userGold;
    }

    public void setUserGold(int userGold) {
        this.userGold = userGold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Clan> getClan() {
        return clan;
    }

    public void setClan(ArrayList<Clan> clan) {
        this.clan = clan;
    }
}
