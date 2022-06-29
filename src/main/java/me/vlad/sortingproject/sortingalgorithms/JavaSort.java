package me.vlad.sortingproject.sortingalgorithms;

import java.util.Arrays;

public class JavaSort implements Sorter {

    @Override
    public int[] sortArray(int[] arrayToSort) {
        int[] arrCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrCopy);
        return arrCopy;
    }
}
