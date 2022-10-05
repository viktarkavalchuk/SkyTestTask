package com.skytecgames.task.service;

import com.skytecgames.task.utils.UploadScript;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class TestAll {
    private ClanService clanService = ClanService.getInstance();
    private TaskService taskService = TaskService.getInstance();
    private UserService userService = UserService.getInstance();

    @Before
    public void setupBefore(){
        UploadScript uploadScript = new UploadScript();
        uploadScript.upload();
    }

    @Test
    public void test_taskService_when20Threads_then100Gold() throws Exception {
        int numberOfThreads = 20;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                try {
                    taskService.completeTask(1, 1);
                    taskService.completeTask(3, 1);
                    userService.addGoldToClan(1, 4, 1);
                    clanService.paymentByClan(3, 1);
                } catch (Exception e) {
                    System.out.println(e);
                }
                latch.countDown();
            });
        }
        latch.await();
        assertEquals(numberOfThreads, clanService.getClan(1).getGold());
        assertEquals(numberOfThreads, clanService.getClan(1).getGold());
        assertEquals(100, clanService.getClan(3).getGold());
    }
}
