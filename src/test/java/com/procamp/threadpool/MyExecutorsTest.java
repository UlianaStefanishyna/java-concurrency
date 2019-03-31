package com.procamp.threadpool;

import com.procamp.threadpool.exception.ThreadPoolIsStoppedException;
import com.procamp.threadpool.exception.WorkQueueIsFullException;
import com.procamp.threadpool.service.MyExecutorService;
import org.junit.Test;

import java.util.stream.IntStream;

public class MyExecutorsTest {

    @Test(expected = WorkQueueIsFullException.class)
    public void testExecuteWhenQueueIsFull_thenThrowException() {
        MyExecutorService myExecutorService = MyExecutors.newFixedThreadPool(2);
        IntStream.range(0, 100)
                .forEach(k -> myExecutorService.execute(() -> System.out.print("string " + k + "; ")));
        myExecutorService.shutdownNow();
    }

    @Test(expected = ThreadPoolIsStoppedException.class)
    public void testExecuteWhenPoolIsAlreadyShutDown_thenThrowException() {
        MyExecutorService myExecutorService = MyExecutors.newFixedThreadPool(2);
        IntStream.range(0, 5)
                .forEach(k -> myExecutorService.execute(() -> System.out.print("string " + k + "; ")));
        myExecutorService.shutdownNow();
        myExecutorService.execute(() -> System.out.println("wrong call"));
    }
}