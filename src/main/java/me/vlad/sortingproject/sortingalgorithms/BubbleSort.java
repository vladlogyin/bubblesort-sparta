package me.vlad.sortingproject.sortingalgorithms;

import java.lang.NullPointerException;

public class BubbleSort implements Sorter {
    @Override
    public int[] sortArray(int[] arrayToSort) throws NullPointerException {
        if (arrayToSort == null) {
            throw new NullPointerException();
        }
        // Create a copy of the array
        int[] arrayCopy = new int[arrayToSort.length];
        System.arraycopy(arrayToSort, 0, arrayCopy, 0, arrayCopy.length);
        // Sort the copied array in-place and return it by reference
        ArrayComparable compHelper = (o, i, j) -> {
            return ((int[]) o)[i] - ((int[]) o)[j];
        };
        Swappable swapHelper = (o, i, j) -> {
            int temp = ((int[]) o)[i];
            ((int[]) o)[i] = ((int[]) o)[j];
            ((int[]) o)[j] = temp;
        };
        Sort.bubbleSort(arrayCopy, arrayCopy.length, compHelper, swapHelper);
        return arrayCopy;
    }
}
