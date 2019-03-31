package com.procamp.sync;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Builder
@Accessors(chain = true)
class Account {

    private int id;
    private int amount;

    void deposit(int amount) {
        this.amount += amount;
    }

    void withdraw(int amount) {
        this.amount -= amount;
    }

    int getAmount() {
        return this.amount;
    }
}