package me.vlad.sortingproject.sortingalgorithms;

import me.vlad.sortingproject.sortingalgorithms.Sorter;

public class MergeSort implements Sorter {

    @Override
    public int[] sortArray(int[] arrayToSort) throws NullPointerException {
        if (arrayToSort == null) {
            throw new NullPointerException();
        }
        return unsafeMergeSort(arrayToSort);
    }

    protected int[] unsafeMergeSort(int[] arr) throws NullPointerException {
        int split = arr.length / 2;
        if (split == 0) {
            return arr;
        }
        int[] a = new int[split];
        int[] b = new int[arr.length - split];

        System.arraycopy(arr, 0, a, 0, a.length);
        System.arraycopy(arr, a.length, b, 0, b.length);
        a = unsafeMergeSort(a);
        b = unsafeMergeSort(b);
        return mergeSortedArrays(a, b);
    }

    protected int[] mergeSortedArrays(int[] a, int[] b) throws NullPointerException {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < merged.length && j < a.length && k < b.length) {
            if (b[k] > a[j]) {
                merged[i++] = a[j++];
            } else {
                merged[i++] = b[k++];
            }
        }
        while (j < a.length) {
            merged[i++] = a[j++];
        }
        while (k < b.length) {
            merged[i++] = b[k++];
        }
        return merged;
    }
}
