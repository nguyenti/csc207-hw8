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
     * Invariant: Subarrays of size size are sorted
     * 
     */
    /*
     * @SuppressWarnings("unchecked")
     * 
     * @Override public T[] sorti(T[] vals, Comparator<T> order) { for (int i =
     * 1; i <= vals.length / 2 + 1; i *= 2) { for (int j = i; j < vals.length; j
     * += 2 * i) { inPlaceMerge(vals, j - i, j, Math.min(j + i, vals.length),
     * order); } }
     * 
     * return vals; } // sorti(T[], Comparator<T>)
     */

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
		    vals[i + numMerge - size] = output[i];
		} // for
		numMerge += size * 2;
	    } // while
	    numMerge -= size;
	    int diff = vals.length - numMerge;
	    if (diff > 1) {
		System.arraycopy(vals, numMerge, temp1, 0, diff);
		temp2 = (T[]) new Object[0];
		output = Utils.merge(order, temp1, temp2);
		// FILL IN!
		for (int i = 0; i < diff; i++) {
		    vals[numMerge + i] = output[i];
		}
	    }
	    // The merged subarrays are now twice as large
	    size *= 2;
	} // while
	return vals;
    } // sorti(T[], Comparator<T>)

    private void inPlaceMerge(T[] array, int start, int middle, int end,
	    Comparator<T> comparer) {
	T[] merge = (T[]) new Object[end - start];
	int l = 0, r = 0, i = 0;
	while (l < (middle - start) && r < (end - middle)) {
	    merge[i++] = comparer.compare(array[start + l], array[middle + r]) < 0 ? array[start
		    + l++]
		    : array[middle + r++];
	}

	while (r < end - middle)
	    merge[i++] = array[middle + r++];

	while (l < middle - start)
	    merge[i++] = array[start + l++];

	System.arraycopy(merge, 0, array, start, merge.length);
    }
} // IterativeMergeSorter<T>
