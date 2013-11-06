package edu.grinnell.csc207.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class IterativeMergeSorterTest {

    /**
     * Some simple tests of our Iterative Merge sort routine.
     */
    @Test
    public void test1() {
	TestUtils.test1(new IterativeMergeSorter<Integer>());
    } // test1

    @Test
    public void test2() {
	TestUtils.test2(new IterativeMergeSorter<Integer>());
    } // test2

} // IterativeMergeSorterTest