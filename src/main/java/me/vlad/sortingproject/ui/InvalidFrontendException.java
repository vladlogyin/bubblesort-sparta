package me.vlad.sortingproject.ui;

public class InvalidFrontendException extends Exception {
    public InvalidFrontendException(String message) {
        super(message);
    }

    public InvalidFrontendException() {
        super("Dev couldn't be bothered to write a message. Good luck!");
    }
}
