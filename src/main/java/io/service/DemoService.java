package io.service;

import io.utilites.WriteAccountInFile;

import java.io.IOException;

public class DemoService {

    private static final int COUNT_THREAD = 10;
    private static final int MAX_TRANSFER = 1000;

    public static void start() throws IOException, InterruptedException {
        WriteAccountInFile writeAccountInFile = new WriteAccountInFile();
        writeAccountInFile.getRandomAccount();

        AccountService accountService = new AccountService();
        accountService.readAccount();
        accountService.printSumAndBalance(); // начальный баланс
        accountService.startExchange(COUNT_THREAD, MAX_TRANSFER);
        accountService.printSumAndBalance(); // баланс после обмена
    }
}
