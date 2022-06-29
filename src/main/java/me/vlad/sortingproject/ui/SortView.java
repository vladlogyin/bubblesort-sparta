package me.vlad.sortingproject.ui;

import me.vlad.sortingproject.sortingalgorithms.Sorter;

import java.util.Map;

public interface SortView {
    /**
     * displays a backend selection dialog
     *
     * @param options        list of different backends to choose from
     * @param callbackMethod method that the selection is relayed to
     * @return true if the selection dialog could be displayed
     */
    boolean displayBackendSelection(Map<String, Class<? extends Sorter>> options, ViewCallback<Class<? extends Sorter>[]> callbackMethod);

    /**
     * displays an array length selection dialog
     *
     * @return
     */
    boolean displayArrayLengthSelection(int minAllowed, int maxAllowed, ViewCallback<Integer> callbackMethod);

    /**
     * displays an array in a relevant way
     *
     * @param arr array to be displayed
     * @return true if the array could be displayed
     */
    boolean displayArray(int[] arr, String headerMessage);

    /**
     * displays timing results in a tabular manner
     *
     * @param results Mapped results in milliseconds
     * @return true if the data could be displayed
     */
    boolean displayTimingResults(Map<Class<? extends Sorter>, Double> results);

    /**
     * displays an error message
     *
     * @param message message to be displayed
     * @return true if the message could be displayed
     */
    boolean displayError(String message);

    /**
     * @param message
     * @param defaultValue
     * @param callback
     * @return
     */
    boolean displayYesNoPrompt(String message, boolean defaultValue, ViewCallback<Boolean> callback);
}
