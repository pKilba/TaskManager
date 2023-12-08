package com.kilbas.exception;


public class NotValidEntityException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public NotValidEntityException() {
    }

    public NotValidEntityException(String message) {
        super(message);
    }
}
