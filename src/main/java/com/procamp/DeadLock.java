package com.procamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
//        Thread.currentThread().join();
        List<Integer> numbers = new ArrayList<Integer>(asList(1, 2, 3, 4, 5, 6));
        List<Integer> numbers1 = new ArrayList<Integer>(asList(1, 2, 3, 4, 5, 6));
        List<List<Integer>> list = Arrays.asList(
                numbers.subList(0, 3),
                numbers1.subList(3, 6)
        );
        System.out.println(list);

        list.get(0).add(4);
        System.out.println(list);
        System.out.println(list.get(1).size());
    }
}
