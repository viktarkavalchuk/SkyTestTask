package com.skytecgames.task.service;

import com.skytecgames.task.utils.UploadScript;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    private ClanService clanService = ClanService.getInstance();
    private UserService userService = UserService.getInstance();

    @Before
    public void setupBefore(){
        UploadScript uploadScript = new UploadScript();
        uploadScript.upload();
    }

    @Test
    public void test_userService_when10Threads_then10Gold() throws Exception {
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                try {
                    userService.addGoldToClan(1, 4, 1);
                } catch (Exception e) {
                    System.out.println("UserServiceTest exception" + e);
                }
                latch.countDown();
            });
        }
        latch.await();
        assertEquals(numberOfThreads, clanService.getClan(4).getGold());
    }
}