package io.utilites;

import io.entity.Account;
import io.service.TransferService;

import java.util.List;
import java.util.Random;

public class ThreadTransfer implements Runnable {

    private Random random = new Random();
    private TransferService transferService;
    private List<Account> accounts;
    private int maxTransfer;

    public ThreadTransfer(List<Account> accounts, int maxTransfer, TransferService transferService) {
        this.accounts = accounts;
        this.maxTransfer = maxTransfer;
        this.transferService = transferService;
    }

    @Override
    public void run() {
        int randMoneyCount;
        while (!Thread.currentThread().isInterrupted()) {
            Account accountOneRand = accounts.get(random.nextInt(accounts.size()));
            Account accountTwoRand = accounts.get(random.nextInt(accounts.size()));
            randMoneyCount = random.nextInt(100);
            try {
                transferService.transferStart(accountOneRand, accountTwoRand, randMoneyCount, maxTransfer);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
