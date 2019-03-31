package com.procamp.threadpool;

import java.util.LinkedList;

public class BlockingQueue<T> {

    private LinkedList<T> queue = new LinkedList<>();
    private int workQueueSize;

    public BlockingQueue(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }

    public synchronized void put(T runnable) throws InterruptedException {
        if (this.queue.size() == this.workQueueSize) {
            wait();
        }
        if (this.queue.isEmpty()) {
            notifyAll();
        }
        this.queue.add(runnable);
    }

    public synchronized T poll(){
        while (this.queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.queue.size() == this.workQueueSize) {
            notifyAll();
        }
        return this.queue.remove(0);
    }
}
