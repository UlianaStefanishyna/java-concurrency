package com.procamp.sync;

import java.util.Arrays;
import java.util.List;

public class MyBank {

    private List<Account> accountList;

    private MyBank(List<Account> accountList) {
        this.accountList = accountList;
    }

    private void transfer(int fromAccountId, int toAccountId, int amount) {
        Account from = findById(fromAccountId);
        Account to = findById(toAccountId);

        synchronized (from){
            from.withdraw(amount);
            synchronized (to){
                to.deposit(amount);
            }
        }
    }

    private int total() {
        return accountList.stream()
                .mapToInt(Account::getAmount).sum(); // sum (accounts)
    }

    private Account findById(int id) {
        return this.accountList.stream()
                .filter(account -> id == account.getId())
                .findFirst().orElseThrow(() -> new RuntimeException("not found"));
    }

    public static void main(String[] args) {

        Account x = Account.builder().id(1).amount(1000).build();
        Account y = Account.builder().id(2).amount(2000).build();
        List<Account> accounts = Arrays.asList(x, y);

        MyBank myBank = new MyBank(accounts);
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                myBank.transfer(1, 2, 10);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                myBank.transfer(2, 1, 10);
            }
        }).start();


//        for (int i = 0; i < 10000; i++) {
//            new Thread(() -> myBank.transfer(1, 2, 100)).start();
//        }
//
//        for (int i = 0; i < 10000; i++) {
//            new Thread(() -> myBank.transfer(2, 1, 100)).start();
//        }
//                IntStream.range(0, 1000)
//                .forEach(i -> myBank.transfer(2, 1, 10))).start();

//            IntStream.range(0, 1000)
//                    .forEach(i -> myBank.transfer(1, 2, 10))
        int total = myBank.total();
        System.out.println(total);
    }
}
