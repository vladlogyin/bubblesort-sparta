package me.vlad.sortingproject.ui;

import me.vlad.sortingproject.sortingalgorithms.Sorter;
import me.vlad.sortingproject.ui.*;

import java.util.*;

import static me.vlad.sortingproject.SortManager.logs;

public class ConsoleSortView implements SortView {
    public ConsoleSortView() {

    }

    ViewCallback<Class<? extends Sorter>> backendSelectionCallback;

    /**
     * Blocking implementation of the backend selection dialog
     */
    @Override
    public boolean displayBackendSelection(Map<String, Class<? extends Sorter>> options, ViewCallback<Class<? extends Sorter>[]> callbackMethod) {
        //backendSelectionCallback = callbackMethod;

        System.out.println("Enter a comma separated list of backends to use (eg: 1,2,3,4)");

        String[] optionStrings = options.keySet().toArray(new String[0]);

        for (int i = 0; i < optionStrings.length; i++) {
            System.out.println((i + 1) + ". " + optionStrings[i]);
        }
        // TODO VVV this is not proper code
        List<Class<? extends Sorter>> selection = new ArrayList<Class<? extends Sorter>>();
        while (selection.size() < 1) {
            System.out.println("Enter a space separated list of backends to use, and be polite! (eg: 1 2 3 4 please)");
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextInt() || sc.hasNext() && !sc.next().toLowerCase().equals("please")) {
                int sel = sc.nextInt() - 1;
                if (sel < 0 || sel >= optionStrings.length || selection.contains(options.get(optionStrings[sel]))) {
                    logs.info("Discarding option " + (sel + 1));
                    continue;
                }
                selection.add(options.get(optionStrings[sel]));

                logs.info("Added " + optionStrings[sel] + " to backend list");
            }
            logs.debug("Selected backends: " + selection.toString());

        }
        callbackMethod.callback(selection.toArray(new Class[0]));
        return true;
    }

    /**
     * Displays an array length selection dialog
     */
    @Override
    public boolean displayArrayLengthSelection(int minAllowed, int maxAllowed, ViewCallback<Integer> callbackMethod) {
        int selection = 10; //default selection
        while (true) {
            System.out.println("Enter a number of elements to generate (" + minAllowed + "-" + maxAllowed + "):");
            try {
                selection = new Scanner(System.in).nextInt();
                if (selection <= maxAllowed && selection >= 0)
                    break;
            } catch (InputMismatchException e) {
            }
        }
        callbackMethod.callback(selection);
        return true;
    }

    @Override
    public boolean displayArray(int[] arr, String arrayName) {
        System.out.println(arrayName + " elements:");

        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            int abs = i > 0 ? i : -i;
            max = max < abs ? abs : max;
        }
        int digits = (int) Math.log10(max) + 1;
        final int elementsPerLine = 20;
        for (int i = 0; i < arr.length; i += elementsPerLine) {
            for (int j = 0; j < elementsPerLine && i + j < arr.length; j++) {
                System.out.format("%" + digits + "d ", arr[i + j]);
            }
            System.out.println("");
        }
        return true;
    }

    @Override
    public boolean displayTimingResults(Map<Class<? extends Sorter>, Double> results) {
        final String[] units = {"ms", "us", "ns"};

        results.forEach((backend, time) -> {
            // rescale numbers to be more meaningful
            int unitIndex;
            for (unitIndex = 0; unitIndex < units.length && time < 10.0; unitIndex++, time *= 1E3) ;

            System.out.format("%20s%20.3f%2s\n", backend.getSimpleName(), time, units[unitIndex]);
        });
        System.out.flush();
        return true;
    }

    @Override
    public boolean displayError(String message) {
        System.out.print(message);
        return true;
    }

    @Override
    public boolean displayYesNoPrompt(String message, boolean defaultValue, ViewCallback<Boolean> callback) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(message + (defaultValue ? " (Y/n)" : " (y/N)"));
            if (sc.hasNext()) {
                String input = sc.next().toLowerCase();
                if (input.equals("y") || input.equals("yes")) {
                    callback.callback(true);
                    return true;
                } else if(input.equals("n") || input.equals("no")) {
                    callback.callback(false);
                    return true;
                } else break;
            } else break;
        }
        callback.callback(defaultValue);
        return true;
    }
}
