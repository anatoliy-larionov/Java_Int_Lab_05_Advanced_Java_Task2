package io.entity;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {
    private int id;
    private String nameAccount;
    private long balance;
    private Lock lock;

    public Account(int id, String nameAccount, long balance) {
        this.id = id;
        this.nameAccount = nameAccount;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public int getId() {
        return id;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Аккаунт[" + "id = " + getId() +
                ", имя = '" + nameAccount + '\'' +
                ", баланс = " + getBalance() + ']';
    }
}
