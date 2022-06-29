package me.vlad.sortingproject.ui;

/**
 * This whole interface was created as a replacement for standard concurrent programming
 *
 * @param <T>
 */
@FunctionalInterface
public interface ViewCallback<T> {
    /**
     * @param returnVal
     * @return true if callback data is accepted, false if there was an error accepting the callback data
     */
    public boolean callback(T returnVal);
}
