package com.procamp.threadpool;

import com.procamp.threadpool.exception.WorkQueueIsFullException;

import java.util.LinkedList;

@SuppressWarnings("WeakerAccess")
public class BlockingQueue<T> {

    private LinkedList<T> queue = new LinkedList<>();
    private int workQueueSize;

    public BlockingQueue(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }

    public synchronized void put(T command) {
        if (this.queue.size() == this.workQueueSize) {
            throw new WorkQueueIsFullException("Work queue is full");
        }
        if (this.queue.isEmpty()) {
            notifyAll();
        }
        this.queue.add(command);
    }

    public synchronized T poll() {
        while (this.queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.queue.poll();
    }
}