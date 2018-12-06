package io.service;

import io.entity.Account;
import io.utilites.ReadAccountFromFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountService {

    private static final String PATH_TO_READ_FILE = ".\\src\\main\\resources";
    private static final int COUNT_THREAD = 20;
    private static final int MAX_TRANSACTION = 1000;
    private Logger logger = LoggerFactory.getLogger(AccountService.class);
    private List<Account> accountList = new ArrayList<>();
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
        logger.info("Общая сумма = {} рубля(ей). ", countSumBalance());
    }

    public void startExchange() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_THREAD);
        for (int i = 0; i < MAX_TRANSACTION; i++) {
            executorService.execute(new ThreadTransfer(accountList, MAX_TRANSACTION, transferService));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) { // вызывает true если все задачи завершены
            Thread.sleep(100);
        }
    }
}
