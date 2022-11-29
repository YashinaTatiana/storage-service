package ru.yashta.storageservice.exception;

public class InvalidPathFormatException extends RuntimeException {
    public InvalidPathFormatException(String message) {
        super(message);
    }

    public InvalidPathFormatException() {
        super();
    }
}
