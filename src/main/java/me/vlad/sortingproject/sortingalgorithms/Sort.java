package me.vlad.sortingproject.sortingalgorithms;

import java.util.*;

public class Sort {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        Swappable swapHelper = (o, i, j) -> {
            T temp = ((T[]) o)[i];
            ((T[]) o)[i] = ((T[]) o)[j];
            ((T[]) o)[j] = temp;
        };
        ArrayComparable compareHelper = (o, i, j) -> {
            return ((T[]) o)[i].compareTo(((T[]) o)[j]);
        };
        bubbleSort(arr, arr.length, compareHelper, swapHelper);
    }

    public static <T extends Comparable<T>> void sort(List<T> col) {
        Swappable swapHelper = (o, i, j) -> {
            Collections.swap((List<T>) o, i, j);
        };
        ArrayComparable compareHelper = (o, i, j) -> {
            return ((List<T>) o).get(i).compareTo(((List<T>) o).get(j));
        };
        bubbleSort(col, col.size(), compareHelper, swapHelper);
    }

    /**
     * bubbleSort
     * <p>
     * internal function that handles comparison and element swapping in a generic way
     * param:
     * Object o - colelction or array to be passed to the swap/ compare functions
     * int i - index of the lefthand element
     * int j - index of the righthand element
     */
    public static void bubbleSort(Object o, int len, ArrayComparable compInterface, Swappable swapInterface) {
        boolean sorted = false;
        len--; // "efFicIenCy"
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < len; i++) {
                if (compInterface.compare(o, i, i + 1) > 0) {
                    swapInterface.swap(o, i, i + 1);
                    sorted = false;
                }
            }
        }
    }
}