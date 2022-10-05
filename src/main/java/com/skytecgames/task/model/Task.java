package com.skytecgames.task.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Task {
    private long taskId; // id квеста
    private String taskName; // Описание квеста
    private int price; // Добыча

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
        Random rand = new Random();
        Integer timeOut = rand.nextInt(1000);
        System.out.println("Task " + taskName + " started");
        try {
            TimeUnit.MILLISECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + taskName + " ended for " + timeOut + "ms");
        return true;
    }
}
