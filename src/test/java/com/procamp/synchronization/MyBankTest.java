package com.procamp.synchronization;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MyBankTest {

    private MyBank myBank;

    private Runnable command;

    @Before
    public void init() {
        Account account1 = Account.builder()
                .id(1)
                .amount(1000).build();
        Account account2 = Account.builder()
                .id(2)
                .amount(2000).build();

        myBank = new MyBank(Arrays.asList(account1, account2));

        command = () -> {
            myBank.transfer(1, 2, 100);
            myBank.transfer(2, 1, 100);
        };
    }

    @Test
    public void testSingleThreadTransfer() {
        int totalBalanceBefore = myBank.total();

        myBank.transfer(1, 2, 1000);
        myBank.transfer(2, 1, 1000);
        int totalBalanceAfter = myBank.total();

        assertEquals(totalBalanceBefore, totalBalanceAfter);
    }

    @Test
    public void testTwoThreadTransfer() {
        int totalBalanceBefore = myBank.total();
        System.out.println("before : " + totalBalanceBefore);

        IntStream.range(0, 100).forEach(a -> new Thread(command).start());

        int totalBalanceAfter = myBank.total();

        System.out.println("after  : " + totalBalanceAfter);

    }

}