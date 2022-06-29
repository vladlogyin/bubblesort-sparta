package me.vlad.sortingproject.testing;

import me.vlad.sortingproject.ui.SortView;
import me.vlad.sortingproject.ui.ViewCallback;
import me.vlad.sortingproject.sortingalgorithms.Sorter;

import java.util.Map;

public class TestView implements SortView {
    public TestView() {

    }

    @Override
    public boolean displayBackendSelection(Map<String, Class<? extends Sorter>> options, ViewCallback<Class<? extends Sorter>[]> callbackMethod) {
        return false;
    }

    @Override
    public boolean displayArrayLengthSelection(int minAllowed, int maxAllowed, ViewCallback<Integer> callbackMethod) {
        return false;
    }

    @Override
    public boolean displayArray(int[] arr, String headerMessage) {
        return false;
    }

    @Override
    public boolean displayTimingResults(Map<Class<? extends Sorter>, Double> results) {
        return false;
    }

    @Override
    public boolean displayError(String message) {
        return false;
    }

    @Override
    public boolean displayYesNoPrompt(String message, boolean defaultValue, ViewCallback<Boolean> callback) {
        return false;
    }
}
