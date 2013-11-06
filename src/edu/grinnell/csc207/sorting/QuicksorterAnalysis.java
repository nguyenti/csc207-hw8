package edu.grinnell.csc207.sorting;

import java.io.PrintWriter;

public class QuicksorterAnalysis {

    public static void main(String[] args) {
	PrintWriter pen = new PrintWriter(System.out, true);
	// Test 1
	Utils.resetCount();
	TestUtils.testOnePermutation(new Quicksorter<Integer>(),
		StandardIntegerComparator.comparator,
		Utils.randomSortedInts(16));
	pen.println(Utils.getCount());
	
	//Test 2
	Utils.resetCount();
	TestUtils.testOnePermutation(new Quicksorter<Integer>(),
		StandardIntegerComparator.comparator,
		Utils.randomSortedInts(32));
	pen.println(Utils.getCount());
	
	//Test 3
	Utils.resetCount();
	TestUtils.testOnePermutation(new Quicksorter<Integer>(),
		StandardIntegerComparator.comparator,
		Utils.randomSortedInts(64));
	pen.println(Utils.getCount());
	
	//Test 4
	Utils.resetCount();
	TestUtils.testOnePermutation(new Quicksorter<Integer>(),
		StandardIntegerComparator.comparator,
		Utils.randomSortedInts(128));
	pen.println(Utils.getCount());

    } // main

    /*
     * Based on the output numbers given to us by the above tests, we can see
     * that 16 log2 16 is 16*4 which is 64, which is the approximate value of
     * our test case for n=16. This value changes from test to test due to the
     * random nature of the pivot selection. We can see that this trend
     * continues for increasing n, as seen in the output. 32 log2 32 = 32*5 =
     * 160 which is our approximate output for the second case. 64 log2 64 =
     * 64*6 = 384 which is our approximate output for the third case. 128 log2
     * 128 = 128*7 = 896 which is our approximate output for the fourth case
     */

} // class QuicksorterAnalysis
