package com.procamp.threadpool;

import com.procamp.threadpool.exception.ThreadPoolIsStoppedException;
import com.procamp.threadpool.exception.WorkQueueIsFullException;
import com.procamp.threadpool.service.MyExecutorService;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class MyExecutors implements MyExecutorService {

    /**
     * Creates a new ThreadPool with the given initial number of threads and work queue size
     *
     * @param poolSize      the number of threads to keep in the pool, even
     *                      if they are idle
     * @param workQueueSize the queue to use for holding tasks before they are
     *                      executed.  This queue will hold only the {@code Runnable}
     *                      tasks submitted by the {@code execute} method.
     */

    private final Queue<Runnable> workQueue;
    private volatile boolean isRunning = true;
    private int workQueueSize;

    private MyExecutors(int poolSize, int workQueueSize) {
        this.workQueue = new LinkedList<>();
        this.workQueueSize = workQueueSize;
        for (int i = 0; i < poolSize; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    public static MyExecutorService newFixedThreadPool(int poolSize, int workQueueSize) {
        return new MyExecutors(poolSize, workQueueSize);
    }

    public static MyExecutorService newFixedThreadPool(int poolSize) {
        return newFixedThreadPool(poolSize, 10);
    }


    public void execute(Runnable command) {
        if (isRunning) {
            this.addToQueue(command);
        } else {
            throw new ThreadPoolIsStoppedException("Thread pool is stopped");
        }
    }

    public void shutdownNow() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {
        public void run() {
            while (isRunning) {
                final Runnable nextTask = poll();
                Optional.ofNullable(nextTask).ifPresent(task -> nextTask.run());
            }
        }
    }

    private synchronized void addToQueue(Runnable command) {
        if (this.workQueue.size() < workQueueSize) {
            this.workQueue.offer(command);
        } else {
            throw new WorkQueueIsFullException("Work queue is full");
        }
    }

    private synchronized Runnable poll() {
        return workQueue.poll();
    }
}