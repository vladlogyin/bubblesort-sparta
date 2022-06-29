package org.example;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3,2,4,5,1};
        for(int i : arr)
        {

            System.out.print(i + " ");
        }
        System.out.println("");
        Sort.sort(arr);
        for(int i : arr)
        {

            System.out.print(i + " ");
        }
    }
}