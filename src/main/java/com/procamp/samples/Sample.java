package com.procamp.samples;

public class Sample {
    public static void main(String[] args) {
        MyRun myRun = new MyRun();
        Thread thread1 = new Thread(myRun);
        Thread thread2 = new Thread(myRun);
        Thread thread3 = new Thread(myRun);

        thread1.setName("one");
        thread2.setName("two");
        thread3.setName("three");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyRun implements  Runnable{

        public void run() {
            System.out.println("sample");
            ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>();

            stringThreadLocal.set("name");
            String s = stringThreadLocal.get();
            System.out.println(s);
        }
    }
}
