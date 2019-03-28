package com.procamp.threadlocal;

public class Application {

    public static void main(String[] args) {

        new Service().put(5);
        new Repository().print();
    }

}

class Service {
    void put(int val) {
        SecurityContextHolder.put(val);
    }

}

class Repository {
    void print() {
        int value = SecurityContextHolder.get();
        System.out.println(value);
    }
}
