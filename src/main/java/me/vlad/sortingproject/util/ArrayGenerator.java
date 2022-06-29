package me.vlad.sortingproject.util;

import java.util.Random;

import static me.vlad.sortingproject.SortManager.logs;

public class ArrayGenerator {

    static public int[] generateArray(int length) {
        int[] arr = new int[length];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(arr.length * 4);
        }
        logs.trace("Generated a " + length + " element array");
        return arr;

    }
}
