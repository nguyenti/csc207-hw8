package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Sort using Quicksort.
 *
 * We consulted Mira Hall, Matt Dole, and Andrew Kelley's code
 * for the bugs in Sam's code.
 * Implemented while frequently referencing Sam's online guide at:
 * http://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2013F/readings/Quicksort.html
 * @author Samuel A. Rebelsky
 * @author John Brady
 * @author Mark Lewis
 * @author Tiffany Nguyen
 */

public class Quicksorter<T> extends SorterBridge<T> {
   /**
    * Sort vals using Quicksort.  See the Sorter<T> interface
    * for additional details.
    */
   @Override
   public T[] sorti(T[] vals, Comparator<T> order) {
       qsort(vals, order, 0, vals.length);
       return vals;
   } // sorti(T[], Comparator<T>)

   /**
    * Sort the elements in positions [lb..ub) using Quicksort.
    */
   public void qsort(T[] vals, Comparator<T> order, int lb, int ub) {
       // One element arrays are sorted.
       if (lb >= ub - 1) {
           return;
       } else {
           int mid = partition(vals, order, lb, ub);
           qsort(vals, order, lb, mid);
           qsort(vals, order, mid+1, ub);
       } // More than one element
   } // sorti(T[], Comparator<T>, int, int)

   /**
    * Pick a random pivot and reorganize the elements in positions 
    * [lb..ub) of vals such that elements smaller than the pivot appear
    * to the left, elements bigger than the pivot appear to the right
    * of the pivot, and the pivot is in the middle.  
    *
    * @param
    *    values, an array.
    * @param
    *    order, a comparator.
    * @param
    *    lb, an integer.
    * @param
    *    ub, an integer.
    * @return
    *    mid, the index of the pivot.
    *
    * @pre
    *    order can be applied to any pair of elements in vals.
    * @pre
    *    0 <= lb < ub < values.length.
    * @post
    *    lb <= mid < ub
    * @post
    *    values[mid] == pivot, for some value pivot
    * @post
    *    For all i, lb <= i < mid, order.compare(values[i],pivot) <= 0
    *    For all i, mid < i < ub, order.compare(pivot, values[i]) < 0
    *    
    * Invariant:
    * 	Pivot in first position (lb)
    * 	all values <= pivot to the immediate right of the pivot
    * 	all values > pivot to the immediate left of ub
    * 	all unproccessed values are between these processed values
    */
   int partition(T[] vals, Comparator<T> order, int lb, int ub) {
       // keep track of the highest position of smaller values
       int smallPos = lb;

       // keep track of the position to swap large numbers into
       int largePos = ub -1;
       // generate a random pivot between lb and ub inclusive
       Random rand = new Random();
       int pivot = rand.nextInt(ub-lb) + lb;
       // put the pivot as the first element
       Utils.swap(vals, lb, pivot);
       // smallPos is no longer lb
       smallPos++;
       
       while(smallPos < largePos) {
	   if(order.compare(vals[lb], vals[smallPos]) >= 0) {
	       smallPos++;
	   } else {
	       Utils.swap(vals, smallPos, largePos);
	       largePos--;
	   } // else   
       } // while
       
       // put the pivot in place, need to compart to value at smallPos
       if (order.compare(vals[lb], vals[smallPos]) >= 0) {
	   Utils.swap(vals, lb, smallPos);
	   return smallPos;
       } // if
       Utils.swap(vals, lb, smallPos - 1);
       return smallPos - 1;
   } // partition
} // Quicksorter<T>
