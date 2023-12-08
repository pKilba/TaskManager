package com.kilbas.exception;

public class AccessEntityException extends RuntimeException {

    static final long serialVersionUID = 100000L;

    public AccessEntityException() {
    }

    public AccessEntityException(String message) {
        super(message);
    }

    public AccessEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessEntityException(Throwable cause) {
        super(cause);
    }

    public AccessEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
