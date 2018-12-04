package io.exception;

public class ErrorTransferIsMadeToTheAccount extends RuntimeException {

    public ErrorTransferIsMadeToTheAccount(String message) {
        super(message);
    }
}
