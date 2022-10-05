package com.skytecgames.task.service;

import com.skytecgames.task.utils.UploadScript;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class TransactionServiceTest {
    private TransactionService transactionService = TransactionService.getInstance();

    @Before
    public void setupBefore(){
        UploadScript uploadScript = new UploadScript();
        uploadScript.upload();
    }

    @Test
    public void test_transactionService_when10Threads_then10Gold() throws Exception {
        int numberOfThreads = 10;
        AtomicInteger countTransactions = new AtomicInteger();
        AtomicInteger countTransactionsByUserId = new AtomicInteger();
        AtomicInteger countTransactionsByClanId = new AtomicInteger();
        AtomicInteger countTransactionsByReasonId = new AtomicInteger();
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                try {
                    countTransactions.set(transactionService.getAllTransactions().size());
                    countTransactionsByUserId.set(transactionService.getTransactionsByUser(1).size());
                    countTransactionsByClanId.set(transactionService.getTransactionsByClan(2).size());
                    countTransactionsByReasonId.set(transactionService.getTransactionsByReason(1).size());
                } catch (Exception e) {
                    System.out.println(e);
                }
                latch.countDown();
            });
        }
        latch.await();
        assertEquals(4, countTransactions.get());
        assertEquals(1, countTransactionsByUserId.get());
        assertEquals(0, countTransactionsByClanId.get());
        assertEquals(3, countTransactionsByReasonId.get());
    }
}