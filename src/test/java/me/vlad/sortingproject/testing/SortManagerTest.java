package me.vlad.sortingproject.testing;

import me.vlad.sortingproject.SortManager;
import me.vlad.sortingproject.sortingalgorithms.BackendProvider;
import me.vlad.sortingproject.sortingalgorithms.Sorter;
import me.vlad.sortingproject.util.ArrayGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SortManagerTest {

    private static final int staticArrayLength = 10000;
    private static int[] unsorted;
    private static int[] unsortedCopy;
    private Random rnd;

    @BeforeEach
    @DisplayName("Preparing arrays")
    void setUp() {
        if (unsortedCopy == null || unsorted == null) {
            rnd = new Random();
            unsorted = new int[staticArrayLength];
            unsortedCopy = new int[staticArrayLength];
            for (int i = 0; i < staticArrayLength; i++) {
                unsorted[i] = rnd.nextInt(staticArrayLength * 10);
            }
        }
        unsorted = Arrays.copyOf(unsortedCopy, unsortedCopy.length);
    }

    @Test
    @DisplayName("Can all backends sort an array")
    void testAllBackends() {
        int[] sorted = Arrays.copyOf(unsortedCopy, unsortedCopy.length);
        Collection<Class<? extends Sorter>> backends = BackendProvider.listBackends().values();
        for (Class<? extends Sorter> backend : backends) {
            SortManager sm = constructedWith(backend);
            Assertions.assertArrayEquals(unsortedCopy, sm.sortArray(unsorted), "Testing " + backend.getSimpleName());
        }
    }

    private static SortManager constructedWith(Class<? extends Sorter> sorterBackend) {
        AtomicReference<SortManager> smar = new AtomicReference<>();
        Assertions.assertDoesNotThrow(() -> {
            smar.set(new SortManager(sorterBackend));
        });
        return smar.get();
    }

    @Test
    void backendSwitching() {
    }

    @Test
    @DisplayName("Does logging behave as expected")
    void logTest() {
        SortManager sm;
        AtomicReference<SortManager> smar = new AtomicReference<>();
        Assertions.assertDoesNotThrow(() -> {
            smar.set(new SortManager());
            //change view to our testing jig
            smar.get().instantiateView(BogusView.class);
        });
        sm = smar.get();
        sm.start();

    }

    @Test
    void userInteractionTest() {
        AtomicReference<SortManager> smar = new AtomicReference<>();
        Assertions.assertDoesNotThrow(() -> {
            smar.set(new SortManager());
            //change view to our testing jig
            smar.get().instantiateView(BogusView.class);
        });
        SortManager sm = smar.get();
        sm.start();
    }

    @Test
    void benchmarkBackends() {
        Collection<Class<? extends Sorter>> backends = BackendProvider.listBackends().values();
        Map<Class<? extends Sorter>, double[]> results = new HashMap<>();
        int[] sorted = Arrays.copyOf(unsortedCopy, unsortedCopy.length);
        System.out.format("%20s%9d%9d%9d%9d%9d\n", "Algorithm", 10, 100, 1000, 10000, 100000);
        for (Class<? extends Sorter> backend : backends) {
            double[] backendResults = new double[5];
            int j = 0;
            results.put(backend, backendResults);
            SortManager sm = constructedWith(backend);
            Assertions.assertArrayEquals(unsortedCopy, sm.sortArray(unsorted), "Testing " + backend.getSimpleName());
            for (int length = 10; length <= (long) 1E5; length *= 10, j++) {
                long totalNano = 0;
                int i;
                for (i = 0; totalNano <= (long) 1E9; i++) {
                    int[] arrayToSort = ArrayGenerator.generateArray(length);
                    long thisNano = System.nanoTime();
                    sm.sortArray(arrayToSort);
                    totalNano += System.nanoTime() - thisNano;
                }
                backendResults[j] = (double) totalNano / i / 1E6;
            }
            System.out.format("%20s%9.2f%9.2f%9.2f%9.2f%9.2f\n", backend.getSimpleName(),
                    backendResults[0], backendResults[1], backendResults[2],
                    backendResults[3], backendResults[4]);
            System.out.flush();
        }
    }
}
