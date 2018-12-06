package io.service;

import io.entity.Account;

import java.util.List;
import java.util.Random;

public class ThreadTransfer implements Runnable {

    private static final int RANDOM_MONEY = 100;
    private Random random = new Random();
    private TransferService transferService;
    private List<Account> accounts;
    private int maxTransaction;

    public ThreadTransfer(List<Account> accounts, int maxTransaction, TransferService transferService) {
        this.accounts = accounts;
        this.maxTransaction = maxTransaction;
        this.transferService = transferService;
    }

    @Override
    public void run() {
        int randMoneyCount;
        while (!Thread.currentThread().isInterrupted()) {
            Account accountOneRand = accounts.get(random.nextInt(accounts.size()));
            Account accountTwoRand = accounts.get(random.nextInt(accounts.size()));
            randMoneyCount = random.nextInt(RANDOM_MONEY);
            try {
                transferService.transferStart(accountOneRand, accountTwoRand, randMoneyCount, maxTransaction);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
