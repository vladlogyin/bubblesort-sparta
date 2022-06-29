package me.vlad.sortingproject.sortingalgorithms;

public class InvalidBackendException extends Exception {
    public InvalidBackendException(String message) {
        super(message);
    }

    public InvalidBackendException() {
        super("vlad couldn't be bothered to write a message. Good luck!");
    }
}
