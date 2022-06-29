package me.vlad.sortingproject.testing;

import me.vlad.sortingproject.sortingalgorithms.Sort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void arraySortTest() {
        // Empty array test
        Integer[] unsorted = new Integer[]{};
        Integer[] sorted = new Integer[]{};
        Sort.sort(unsorted);
        assertArrayEquals(sorted, unsorted);
        // single element array test
        unsorted = new Integer[]{1};
        sorted = new Integer[]{1};
        Sort.sort(unsorted);
        assertArrayEquals(sorted, unsorted);
        // low lement count test
        unsorted = new Integer[]{3, 1, 5, 2, 4};
        sorted = new Integer[]{1, 2, 3, 4, 5};
        Sort.sort(unsorted);
        assertArrayEquals(sorted, unsorted);
    }

    @Test
    void arrayDoubleTest() {

    }

    @Test
    void listSortTest() {
        ArrayList<Integer> unsorted = new ArrayList<Integer>(Arrays.asList(3, 1, 5, 2, 4));
        ArrayList<Integer> sorted = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    void stringListTest() {
        ArrayList<String> unsorted = new ArrayList<String>(Arrays.asList("Lew", "Alfie", "James", "Xenia", "Carla"));
        ArrayList<String> sorted = new ArrayList<String>(Arrays.asList("Alfie", "Carla", "James", "Lew", "Xenia"));
        Sort.sort(unsorted);
        assertArrayEquals(sorted.toArray(), unsorted.toArray());
    }
}