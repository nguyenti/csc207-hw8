package edu.grinnell.csc207.sorting;

import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * A variety of utilities for working with sorting algorithms. Most are designed
 * to help with testing or experiments.
 * 
 * @author Samuel A. Rebelsky
 * @author Tiffany Nguyen
 * @author Mark Lewis
 * @author John Brady
 */
class Utils {

    // +---------------+---------------------------------------------------
    // | Static Fields |
    // +---------------+

    /**
     * A random number generator for use in permutations and such.
     */
    static Random generator = new Random();

    /**
     * field that indicates the count of the swaps, in order to analyze the
     * number of steps(swaps);
     */
    static int count = 0;

    // +----------------+--------------------------------------------------
    // | Static Methods |
    // +----------------+

    /**
     * get the current count of swaps
     */

    public static int getCount() {
	return count;
    } // getCount

    /**
     * reset the count field to zero, for another test
     */

    public static void resetCount() {
	count = 0;
    } // resetCount

    public static <T> T[] merge(Comparator<T> order, T[] a1, T[] a2) {
	return merge(order, a1, 0, a1.length, a2, 0, a2.length);
    } // merge(Comparator<T>, T[], T[])

    /**
     * Merge the values in arrays a1 and a2 into a new array.
     * 
     * concept from this source (and Sam's help):
     * http://stackoverflow.com/questions
     * /15629651/recursive-merge-sort-java-program
     * 
     * @return merged, an array
     * 
     * @pre sorted(a1, order)
     * @pre sorted(a2, order)
     * @post sorted(merged, order).
     * @post merged is a permutation of the concatenation of a1 and a2.
     * 
     * @invariant both a1 and a2 still have unprocessed elements. That is, there
     *            exists at least one value in both a1 and a2 that have yet to
     *            be compared with one another.
     */

    public static <T> T[] merge(Comparator<T> order, T[] a1, int lb1, int ub1,
	    T[] a2, int lb2, int ub2) {
	T[] ay1 = (T[]) new Object[ub1 - lb1];
	System.arraycopy(a1, lb1, ay1, 0, ub1 - lb1);
	T[] ay2 = (T[]) new Object[ub2 - lb2];
	System.arraycopy(a2, lb2, ay2, 0, ub2 - lb2);
	T[] result = (T[]) new Object[ay1.length + ay2.length];
	int m = 0;
	int n = 0;
	int count = 0;
	// loop that will go through and return the smallest value from each
	// array
	while (m < ay1.length && n < ay2.length) {
	    if (order.compare(ay1[m], ay2[n]) < 0) {
		result[count++] = ay1[m++];
	    } else if (order.compare(ay1[m], ay2[n]) >= 0) {
		result[count++] = ay2[n++];
	    } // if
	} // while

	// finishing moves, copy any leftover if one array is exhausted before
	// the other
	if (m != ay1.length) {
	    while (m < ay1.length) {
		result[count++] = ay1[m++];
	    } // while
	} // if
	if (n != ay2.length) {
	    while (n < ay2.length) {
		result[count++] = ay2[n++];
	    } // while
	} // if
	return result;
    } // merge(Comparator<T>, T[], int, int, T[], int, int)

    /**
     * "Randomly" permute an array in place.
     */
    public static <T> T[] permute(T[] values) {
	for (int i = 0; i < values.length; i++) {
	    swap(values, i, generator.nextInt(values.length));
	} // for
	return values;
    } // permute(T)

    /**
     * Generate a "random" sorted array of integers of size n.
     */
    public static Integer[] randomSortedInts(int n) {
	if (n == 0) {
	    return new Integer[0];
	}
	Integer[] values = new Integer[n];
	// Start with a negative number so that we have a mix
	values[0] = generator.nextInt(10) - n;
	// Add remaining values. We use a random increment between
	// 0 and 3 so that there are some duplicates and some gaps.
	for (int i = 1; i < n; i++) {
	    values[i] = values[i - 1] + generator.nextInt(4);
	} // for
	return values;
    } // randomSortedInts

    /**
     * Determine if elements l..(u-1) of an array are in sorted order.
     * 
     * @param values
     *            , the array.
     * @param order
     *            , the comparator that determines the ordering.
     * @param l
     *            , an integer
     * @param u
     *            , an integer
     * @return true if the subarray is ordered, false otherwise
     * @pre order can be applied to any two values in the array.
     * @pre 0 <= l <= values.length
     * @pre 0 <= u <= values.length
     */
    public static <T> boolean sorted(T[] values, Comparator<T> order, int l,
	    int u) {
	for (int i = u - 1; i > l; i--) {
	    if (order.compare(values[i - 1], values[i]) > 0)
		return false;
	} // for
	  // At this point, we've checked every pair. It must be sorted
	return true;
    } // sorted

    /**
     * Determine if an array is sorted.
     * 
     * @param values
     *            , the array.
     * @param order
     *            , the comparator that determines the ordering.
     * @return true if the subarray is ordered, false otherwise
     * @pre order can be applied to any two values in the array.
     */
    public static <T> boolean sorted(T[] values, Comparator<T> order) {
	return sorted(values, order, 0, values.length);
    } // sorted(T[], Comparator<T>)

    /**
     * Swap two elements in an array.
     * 
     * @param values
     *            , the array
     * @param i
     *            , one of the indices
     * @param j
     *            , another index
     * @pre 0 <= i,j < values.length
     * @pre a = values[i]
     * @pre b = values[j]
     * @post values[i] = b
     * @post values[j] = a
     */
    public static <T> void swap(T[] values, int i, int j) {
	T tmp = values[i];
	values[i] = values[j];
	values[j] = tmp;
	count++;
    } // swap(T[], int, int)

    // +-------------+-----------------------------------------------------
    // | Expermients |
    // +-------------+

    /**
     * A simple experiment in permutations.
     */
    public static <T> void permutationExperiment(PrintWriter pen,
	    Sorter<T> sorter, Comparator<T> compare, T[] sorted) {
	T[] values = sorted.clone();
	permute(values);
	checkSorting(pen, sorted, values, sorter.sort(values, compare));
    } // permutationExperiment(PrintWriter, Sorter<T>, Comparator<T>

    /**
     * Check the result of sorting.
     */
    public static <T> void checkSorting(PrintWriter pen, T[] sorted,
	    T[] values, T[] resorted) {
	// Print a quick prefix so that we can see whether or not the
	// sort worked.
	if (Arrays.equals(sorted, resorted)) {
	    pen.print("OK:  ");
	} else {
	    pen.print("BAD: ");
	} // if the sorted array does not equal the original array.

	// Print the transformation for folks who like to look.
	pen.println("sort(" + Arrays.toString(values) + ") => ");
	pen.println("          " + Arrays.toString(resorted));
    } // checkSorting

    /**
     * Run some experiments using an integer sorter.
     */
    public static void iExperiments(Sorter<Integer> sorter) {
	PrintWriter pen = new PrintWriter(System.out, true);
	Integer[] vals1 = new Integer[] { 1, 2, 2, 2, 4, 5, 7, 7, 11, 13 };

	// A case that's proven problematic
	Integer[] vals2 = new Integer[] { 1, 1, 2, 3, 4, 5, 7, 9, 11, 13, 13, 0 };
	checkSorting(pen,
		new Integer[] { 0, 1, 1, 2, 3, 4, 5, 7, 9, 11, 13, 13 }, vals2,
		sorter.sort(vals2, StandardIntegerComparator.comparator));

	// Five random permutation experiments seems like enough
	for (int i = 0; i < 5; i++) {
	    permutationExperiment(pen, sorter,
		    StandardIntegerComparator.comparator, vals1);
	} // for

	// A permutation experiment with different sizes
	for (int i = 1; i < 5; i++) {
	    permutationExperiment(pen, sorter,
		    StandardIntegerComparator.comparator,
		    randomSortedInts(i * 10));
	} // for
    } // experiments(Sorter<Integer>)

    /**
     * Run some experiments using a string sorter.
     */
    public static void sExperiments(Sorter<String> sorter) {
	PrintWriter pen = new PrintWriter(System.out, true);
	String[] vals1 = new String[] { "a", "b", "b", "f", "g", "g", "w", "x",
		"y", "z", "z", "z" };
	// Five random permutation experiments seems like enough
	for (int i = 0; i < 5; i++) {
	    permutationExperiment(pen, sorter,
		    StandardStringComparator.comparator, vals1);
	} // for
    } // experiments(Sorter<String>)
} // class Utils
