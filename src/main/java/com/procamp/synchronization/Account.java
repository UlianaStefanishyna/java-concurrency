package com.procamp.synchronization;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Builder
@Accessors(chain = true)
@SuppressWarnings("all")
public class Account {

    private int id;
    private volatile int amount;

    synchronized void deposit(int amount) {
//        System.out.println("deposit init :" + Thread.currentThread().getName() + " , amount = " + this.amount);
        this.amount += amount;
//        System.out.println("deposit finished : " + Thread.currentThread().getName() + " , amount = " + this.amount);
    }

    synchronized void withdraw(int amount) {
//        System.out.println("withdraw init : " + Thread.currentThread().getName() + " , amount = " + this.amount);
        this.amount -= amount;
//        System.out.println("withdraw finished : " + Thread.currentThread().getName() + " , amount = " + this.amount);
    }

    int getAmount() {
        return this.amount;
    }
}