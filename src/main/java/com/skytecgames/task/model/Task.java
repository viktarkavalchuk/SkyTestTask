package com.skytecgames.task.model;

public class Task {
    private long taskId;
    private String taskName;
    private int price;

    public Task(long taskId, String taskName, int price) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.price = price;
    }

    public long getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getPrice() {
        return price;
    }

    public boolean execute() {
        System.out.println("Task " + taskName + " started.");
        return true;
    }
}
