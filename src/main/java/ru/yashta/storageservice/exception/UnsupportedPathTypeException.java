package ru.yashta.storageservice.exception;

public class UnsupportedPathTypeException extends RuntimeException {
    public UnsupportedPathTypeException() {
        super();
    }

    public UnsupportedPathTypeException(String message) {
        super(message);
    }
}
