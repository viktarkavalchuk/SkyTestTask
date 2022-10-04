package com.skytecgames.task.model;

public class Transaction {
    private long id; //id транзакции
    private String date; //дата транзакции
    private Long userId; //id пользователя, совершившего перевод
    private Long taskId;
    private long clanId; //id клана, получившего перевод
    private int goldBefore; //количество золота, поступившего в казну клана
    private int goldAfter;
    private String reason;

    public Transaction() {
    }

    public Transaction(long id, String date, Long userId, Long taskId, long clanId, int goldBefore, int goldAfter, String reason) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.taskId = taskId;
        this.clanId = clanId;
        this.goldBefore = goldBefore;
        this.goldAfter = goldAfter;
        this.reason = reason;
    }

    public Transaction(String date, Long userId, Long taskId, long clanId, int goldBefore, int goldAfter, String reason) {
        this.date = date;
        this.userId = userId;
        this.taskId = taskId;
        this.clanId = clanId;
        this.goldBefore = goldBefore;
        this.goldAfter = goldAfter;
        this.reason = reason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public long getClanId() {
        return clanId;
    }

    public void setClanId(long clanId) {
        this.clanId = clanId;
    }

    public int getGoldBefore() {
        return goldBefore;
    }

    public void setGoldBefore(int goldBefore) {
        this.goldBefore = goldBefore;
    }

    public int getGoldAfter() {
        return goldAfter;
    }

    public void setGoldAfter(int goldAfter) {
        this.goldAfter = goldAfter;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
