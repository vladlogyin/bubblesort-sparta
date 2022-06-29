package me.vlad.sortingproject;

import me.vlad.sortingproject.sortingalgorithms.*;
import me.vlad.sortingproject.ui.*;
import me.vlad.sortingproject.util.ArrayGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Controller
public class SortManager {

    public static Logger logs = LogManager.getLogger();
    protected static final Class<? extends Sorter> defaultBackend = BubbleSort.class;
    protected static final Class<? extends SortView> defaultFrontend = ConsoleSortView.class;

    public SortManager(Class<? extends Sorter> initialBackend) throws NullPointerException, InvalidBackendException {
        if (initialBackend == null) {
            throw new NullPointerException();
        }
        instantiateBackend(initialBackend);
    }

    public SortManager() throws InvalidFrontendException, InvalidBackendException {
        instantiateBackend(defaultBackend);
        instantiateView(defaultFrontend);
    }

    public void instantiateView(Class<? extends SortView> sorterView) throws InvalidFrontendException {
        try {
            this.sorterFrontend = (SortView) (sorterView.getDeclaredConstructor().newInstance());
        } catch (InstantiationException e) {
            throw new InvalidFrontendException("Constructor missing: " + e.getMessage());
        } catch (Exception e) {
            throw new InvalidFrontendException(e.toString());
        }
    }

    void instantiateBackend(Class<? extends Sorter> sorterBackend) throws InvalidBackendException {
        try {
            this.sorterInterface = (Sorter) (sorterBackend.getDeclaredConstructor().newInstance());
        } catch (NoSuchMethodException e) {
            throw new InvalidBackendException("Constructor missing: " + e.getMessage());
        } catch (Exception e) {
            throw new InvalidBackendException(e.toString());
        }
    }


    public int[] sortArray(int[] arr) throws NullPointerException {
        return sorterInterface.sortArray(arr);
    }

    protected Sorter sorterInterface;
    SortView sorterFrontend;
    int arrayLength;

    private Class<? extends Sorter>[] selectedBackends;
    private boolean running;

    public void start() {
        running = true;
        while (running) {
            if (!sorterFrontend.displayBackendSelection(BackendProvider.listBackends(), (o) -> {
                selectedBackends = o;
                try {
                    logs.info("View returned backend[0]: " + o[0].getSimpleName());
                    instantiateBackend(o[0]);
                    logs.info("Backend successfully instantiated: " + o[0].getSimpleName());
                    return true; // happy days
                } catch (InvalidBackendException e) {
                    logs.error("Backend could not be instantiated\nException: " + e.toString());
                    return false;
                }
            })) {

            }
            if (!sorterFrontend.displayArrayLengthSelection(1, 500, (i) -> {
                arrayLength = i;
                return true;
            })) {
                logs.fatal("View could not display the array length selection dialog: " + sorterFrontend.getClass().getSimpleName());
                return;
            }
            // Generate an array of random integers from 0 to length*4 so that the numbers are reasonable for the size of the array
            int[] unsortedArray = ArrayGenerator.generateArray(arrayLength);
            if (!sorterFrontend.displayArray(unsortedArray, "Unsorted")) {
                logs.fatal("View could not display the array: " + sorterFrontend.getClass().getSimpleName());
                return;
            }

            int[] sortedArray = sortArray(unsortedArray);

            if (!sorterFrontend.displayArray(sortedArray, "Sorted")) {
                logs.fatal("View could not display the array: " + sorterFrontend.getClass().getSimpleName());
                return;
            }

            //re-sort arrays with all the selected backends and display timing results
            Map<Class<? extends Sorter>, Double> results = new HashMap<>();
            for (Class<? extends Sorter> backend : selectedBackends) {
                try {
                    logs.info("Trying to instantiate: " + backend.getSimpleName());
                    instantiateBackend(backend);
                    logs.info("Backend successfully instantiated: " + backend.getSimpleName());
                    {
                        long totalNano = 0;
                        int i;
                        for (i = 0; totalNano <= (long) 1E8; i++) {
                            long thisNano = System.nanoTime();
                            sortArray(unsortedArray);
                            totalNano += System.nanoTime() - thisNano;
                        }
                        results.put(backend, totalNano / 1E6 / i);
                    }

                } catch (InvalidBackendException e) {
                    logs.error("Backend could not be instantiated\nException: " + e.toString());
                    sorterFrontend.displayError(backend.getSimpleName() + " could not be used. Check the logs for more information");
                }
            }
            sorterFrontend.displayTimingResults(results);
            sorterFrontend.displayYesNoPrompt("Exit?", false, (o) -> {
                running = !o;
                return true;
            });
        }

    }

}
