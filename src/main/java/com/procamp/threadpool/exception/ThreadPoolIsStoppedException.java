package com.procamp.threadpool.exception;

public class ThreadPoolIsStoppedException extends RuntimeException {
    public ThreadPoolIsStoppedException(String message) {
        super(message);
    }
}
