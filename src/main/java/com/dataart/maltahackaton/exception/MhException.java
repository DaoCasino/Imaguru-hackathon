package com.dataart.maltahackaton.exception;

public class MhException extends RuntimeException {

    public MhException() {
    }

    public MhException(String message) {
        super(message);
    }

    public MhException(String message, Throwable cause) {
        super(message, cause);
    }
}
