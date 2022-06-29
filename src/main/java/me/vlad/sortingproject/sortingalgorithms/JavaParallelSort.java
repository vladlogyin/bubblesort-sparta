package me.vlad.sortingproject.sortingalgorithms;

import java.util.Arrays;

public class JavaParallelSort implements Sorter {

    @Override
    public int[] sortArray(int[] arrayToSort) {
        int[] arrCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.parallelSort(arrCopy);
        return arrCopy;
    }
}
