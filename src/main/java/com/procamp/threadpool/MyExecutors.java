package com.procamp.threadpool;

import com.procamp.threadpool.service.MyExecutorService;

import java.util.List;

public class MyExecutors {

    private List<Runnable> queue;

    public MyExecutors(List<Runnable> queue) {
        this.queue = queue;
    }

    /**
     * Creates a new ThreadPool with the given initial number of threads and work queue size
     *
     * @param poolSize the number of threads to keep in the pool, even
     *        if they are idle
     * @param workQueueSize the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     */
    public static MyExecutorService newFixedThreadPool(int poolSize, int workQueueSize) {

        return null;
    }

    public static MyExecutorService newFixedThreadPool(int poolSize) {
        return newFixedThreadPool(poolSize, 10);
    }

}
