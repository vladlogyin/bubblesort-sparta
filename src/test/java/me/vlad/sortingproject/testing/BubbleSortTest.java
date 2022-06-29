package me.vlad.sortingproject.testing;

import me.vlad.sortingproject.sortingalgorithms.BubbleSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
    BubbleSort bs;

    @BeforeEach
    void setUp() {
        bs = new BubbleSort();
    }

    @Test
    void sortArray() {
        int[] unsorted = new int[]{3, 4, 2, 1, 6, 4, 7};
        int[] sorted = new int[]{1, 2, 3, 4, 4, 6, 7};
        assertArrayEquals(sorted, bs.sortArray(unsorted));
        // returned array should be different
        assertFalse(bs.sortArray(unsorted) == unsorted);
    }

    @Test
    void sortArrayExceptions() {
        assertThrows(NullPointerException.class, () -> {
            bs.sortArray(null);
        });
    }
}