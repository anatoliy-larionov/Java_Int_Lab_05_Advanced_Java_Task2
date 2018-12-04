package io.exception;

public class BalanceNotMatchForTransfer extends RuntimeException {

    public BalanceNotMatchForTransfer(String message) {
        super(message);
    }
}
