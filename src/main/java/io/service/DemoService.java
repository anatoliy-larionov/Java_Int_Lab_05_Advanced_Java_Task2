package io.service;

import io.utilites.WriteAccountInFile;

import java.io.IOException;

public class DemoService {

    public static void start() throws IOException, InterruptedException {
        WriteAccountInFile writeAccountInFile = new WriteAccountInFile();
        AccountService accountService = new AccountService();

        writeAccountInFile.getRandomAccount();
        accountService.readAccount();
        accountService.printSumAndBalance(); // начальный баланс
        accountService.startExchange();
        accountService.printSumAndBalance(); // баланс после обмена
    }
}
