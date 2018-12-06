package io.service;

import io.entity.Account;
import io.exception.BalanceNotMatchForTransfer;
import io.exception.ErrorTransferIsMadeToTheAccount;
import io.exception.NegativeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransferService {
    private Logger logger = LoggerFactory.getLogger(TransferService.class);
    private Lock lock = new ReentrantLock();
    private volatile AtomicInteger numOfTransaction = new AtomicInteger();
    /* private static final long ONE_SECONDS = 1L;*/

    public void transferStart(Account accountOne, Account accountTwo, long countMoney, int maxTransaction) throws InterruptedException {
        Thread.sleep(200);
        if (numOfTransaction.get() < maxTransaction) {
            lock.lock();
           /* if (accountOne.getLock().tryLock(ONE_SECONDS, TimeUnit.SECONDS)) {
                if (accountTwo.getLock().tryLock(ONE_SECONDS, TimeUnit.SECONDS)) {*/
            try {
                if (accountOne.getBalance() < countMoney) {
                    throw new BalanceNotMatchForTransfer("Баланс не соответствует для перевода!");
                }
                if (accountOne.equals(accountTwo)) {
                    throw new ErrorTransferIsMadeToTheAccount("Ошибка перевод совершен на один и тот же аккаунт!");
                }
                long tempOne = accountOne.getBalance() - countMoney;
                long tempTwo = accountTwo.getBalance() + countMoney;
                if (tempOne < 0 || tempTwo < 0) {
                    throw new NegativeException("Отрицательный баланс!!!");
                } else {
                    accountOne.setBalance(tempOne);
                    accountTwo.setBalance(tempTwo);
                    numOfTransaction.incrementAndGet();
                    logger.info("Сумма в размере {} рубля(ей) переведена со счета {} (текущий баланс = {}) -> " +
                                    "на счет {} (текущий баланс = {}). Номер транзакции {} Успешно",
                            countMoney, accountOne.getNameAccount(), accountOne.getBalance(), accountTwo.getNameAccount(),
                            accountTwo.getBalance(), numOfTransaction);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            } finally {
                       /* accountOne.getLock().unlock();
                        accountTwo.getLock().unlock();*/
                lock.unlock();
            }
              /*  }
            }*/
        } else {
            Thread.currentThread().interrupt();
        }
    }
}
