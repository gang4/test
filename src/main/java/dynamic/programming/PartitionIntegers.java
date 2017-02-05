package dynamic.programming;

import java.util.Arrays;

/**
 * https://courses.csail.mit.edu/6.006/fall10/handouts/dpproblems-sol.pdf
 * 
 * Balanced Paritions
 * 
 * Cut integer array into two. make difference of two sum minimum.
 * 
 * @author yyu
 *
 */
public class PartitionIntegers {
	final Integer [] a;
	public PartitionIntegers(Integer [] a) {
		this.a = a;
	}
	
	/*
	 * Reduce to Knapsack problem.
	 * settings: 
	 * (1) all item weight is 1.
	 * (2) Capacity is equal to number of items.
	 * 
	 * We can find optimal values in the table built by Knapsack.
	 * In last row, find the value close to half of the value
	 * of table[n][n].
	 * 
	 */
	public int [][] buildTable() {
		Integer [] w = new Integer[a.length];
		Arrays.fill(w, 1);
		return new Knapsack(a, w, a.length).buildNaive();
	}
}
