package com.procamp;

public class App {
    static ThreadLocal<Integer> local = new ThreadLocal<Integer>();

    public static void main(String[] args) {

        new Service().put(5, local);
        new Repo().print(local);
    }

}

class Service{
    public void put(int val, ThreadLocal<Integer> local){
        local.set(val);
    }

}

class Repo{
    public void print( ThreadLocal<Integer> local){
//        ThreadLocal<Integer> local = new ThreadLocal<Integer>();
        Integer integer = local.get();
        System.out.println(integer);
    }
}
