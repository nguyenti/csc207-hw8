package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using iterative merge sort.
 * 
 * @author Samuel A. Rebelsky
 * @author Tiffany Nguyen
 * @author Mark Lewis
 * @author John Brady
 * 
 */
public class IterativeMergeSorter<T> extends SorterBridge<T> {
    /**
     * Sort vals using iterative merge sort. See the Sorter<T> interface for
     * additional details.
     * 
     * TEST THIS AND CHECK THE INVARIANT
     * 
     * Invariant:
     *   Subarrays of size size are sorted
     * 
     */
    @Override
    public T[] sorti(T[] vals, Comparator<T> order) {
	    for (int i = 1; i <= vals.length / 2 + 1; i *= 2)
	    {
	        for (int j = i; j < vals.length; j += 2 * i)
	        {
	            inPlaceMerge(vals, j - i, j, Math.min(j + i, vals.length), order);
	        }
	    }
	 
	    return vals;	
    } // sorti(T[], Comparator<T>)
    
    
    private void inPlaceMerge(T[] array, int start, int middle, int end, Comparator<T> comparer)
    {
        T[] merge = (T[]) new Object[end-start];
        int l = 0, r = 0, i = 0;
        while (l < (middle-start) && r < (end-middle))
        {
            merge[i++] = comparer.compare(array[start + l], array[middle + r]) < 0
                ? array[start + l++]
                : array[middle + r++];
        }
     
        while (r < end-middle) merge[i++] = array[middle + r++];
     
        while (l < middle-start) merge[i++] = array[start + l++];
     
        System.arraycopy(merge, 0, array, start, merge.length);
    }
} // IterativeMergeSorter<T>
