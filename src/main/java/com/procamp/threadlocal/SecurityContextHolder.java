package com.procamp.threadlocal;

public class SecurityContextHolder {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static int get() {
        return threadLocal.get();
    }

    public static void put(int value) {
        threadLocal.set(value);
    }
}
