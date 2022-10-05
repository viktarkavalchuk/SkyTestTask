package com.skytecgames.task.service;

public abstract class AbstractDB {
    private static Object mutex = new Object();

    public static Object getDbMutex() {
        return mutex;
    }
}
