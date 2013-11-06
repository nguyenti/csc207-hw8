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
     * We received advice on using Math.min in our implementation from Matt,
     * Mira, and Andrew's group
     * 
     * We also referenced:
     * 	http://java.dzone.com/articles/recursive-and-iterative-merge
     * 
     * Invariant: Subarrays of size size are sorted
     * 
     */

    @SuppressWarnings("unchecked")
    @Override
    public T[] sorti(T[] vals, Comparator<T> order) {
	T[] temp;
	// start with single element arrays
	int size = 1;

	while (size < vals.length) {
	    // Merge neighboring subarrays of size size
	    int currentPos = 0;
	    while (currentPos < vals.length) {
		// To avoid out of bounds exception
		temp = (T[]) new Object[(Math.min((currentPos + size * 2),
			vals.length) - currentPos)];
		temp = Utils.merge(order, vals, currentPos,
			Math.min(currentPos + size, vals.length), vals,
			Math.min(currentPos + size, vals.length),
			Math.min((currentPos + size * 2), vals.length));
		// copy values into vals
		System.arraycopy(temp, 0, vals, currentPos, temp.length);
		currentPos += size * 2;
	    } // while

	    // The merged subarrays are now twice as large
	    size *= 2;
	} // while

	return vals;
    } // sorti(T[], Comparator<T>)
} // IterativeMergeSorter<T>
