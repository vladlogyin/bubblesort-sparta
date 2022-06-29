package org.example;

import java.lang.reflect.Executable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static <T extends Comparable<T>> void sort(T[] arr)
    {
        SwapInterface swapHelper = (o, i, j) -> {
            T temp=((T[])o)[i];
            ((T[])o)[i] = ((T[])o)[j];
            ((T[])o)[j] = temp;
        };
        CompareInterface compareHelper = (o, i, j) -> {
            return ((T[])o)[i].compareTo(((T[])o)[j]);
        };
        bubbleSort(arr,arr.length,compareHelper, swapHelper);
    }
    public static <T extends Comparable<T>> void sort(List<T> col)
    {
        SwapInterface swapHelper = (o, i, j) ->{
            Collections.swap((List<T>) o,i,j);
        };
        CompareInterface compareHelper = (o, i, j) -> {
            return ((T[])o)[i].compareTo(((T[])o)[j]);
        };
        bubbleSort(col,col.size(),compareHelper, swapHelper);
    }

    /** bubbleSort
     *
     * internal function that handles comparison and element swapping in a generic way
     * param:
     * Object o - colelction or array to be passed to the swap/ compare functions
     * int i - index of the lefthand element
     * int j - index of the righthand element
     */
    public static void bubbleSort(Object o,int len, CompareInterface compInterface, SwapInterface swapInterface)
    {
        Boolean sorted=false;
        len--; // "efFicIenCy"
        while(!sorted)
        {
            sorted=true;
            for(int i=0;i<len;i++)
            {
                if(compInterface.compare(o, i, i+1)>0)
                {
                    swapInterface.swap(o, i, i+1);
                    sorted = false;
                }
            }
        }
    }
}
