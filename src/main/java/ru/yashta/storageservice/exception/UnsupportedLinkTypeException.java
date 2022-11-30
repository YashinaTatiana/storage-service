package ru.yashta.storageservice.exception;

public class UnsupportedLinkTypeException extends RuntimeException {
    public UnsupportedLinkTypeException() {
        super();
    }

    public UnsupportedLinkTypeException(String message) {
        super(message);
    }
}
