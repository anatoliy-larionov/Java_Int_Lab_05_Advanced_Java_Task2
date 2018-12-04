package io.service;

import io.entity.Account;
import io.utilites.ReadAccountFromFile;
import io.utilites.ThreadTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountService {

    private static final String PATH_TO_READ_FILE = ".\\src\\main\\resources";

    private Logger logger = LoggerFactory.getLogger(AccountService.class);
    private List<Account> accountList;
    private ReadAccountFromFile readAccFromFile = new ReadAccountFromFile();
    private TransferService transferService = new TransferService();

    public void readAccount() {
        try {
            accountList = readAccFromFile.readAccFromFile(PATH_TO_READ_FILE);
        } catch (ClassNotFoundException e) {
            logger.info("Класс не найден!");
        }
    }

    private long countSumBalance() {
        return accountList.stream().mapToLong(Account::getBalance).sum();
    }

    public void printSumAndBalance() {
        for (Account account : accountList) {
            logger.info("Аккаунт {} текущий баланс {} рубля(ей).", account.getId(), account.getBalance());
        }
        logger.info("Общая сумма = {} рубля(ей). " ,countSumBalance());
    }

    public void startExchange(int countThread, int maxTransfer) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);
        for (int i = 0; i < countThread; i++) {
            executorService.execute(new ThreadTransfer(accountList, maxTransfer, transferService));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(2000);
        }
    }
}
