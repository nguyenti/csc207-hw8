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
    @SuppressWarnings("unchecked")
    @Override
    public T[] sorti(T[] vals, Comparator<T> order) {
	int size = 1;
	while (size < vals.length) {
	    // Merge neighboring subarrays of size size
	    int numMerge = size;
	    T[] temp1 = (T[]) new Object[size];
	    T[] temp2 = (T[]) new Object[size];
	    T[] output = (T[]) new Object[size * 2];
	    while (numMerge < vals.length) {
		System.arraycopy(vals, numMerge - size, temp1, 0, size);
		System.arraycopy(vals, numMerge, temp2, 0, size);
		output = Utils.merge(order, temp1, temp2);
		// FILL IN!
		for (int i = 0; i < output.length; i++) {
		    vals[i + numMerge - 1] = output[i];
		} // for
		numMerge += size * 2;
	    } // while
	    // The merged subarrays are now twice as large
	    size *= 2;
	} // while
	return vals;
    } // sorti(T[], Comparator<T>)
} // IterativeMergeSorter<T>
