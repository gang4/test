package dynamic.programming;

import graph.Vertex;

/*
 * Given an array of integers where each element represents 
 * the max number of steps that can be made forward from that 
 * element. Write a function to return the minimum number of 
 * jumps to reach the end of the array (starting from the 
 * first element). If an element is 0, then cannot move through 
 * that element.
 * 
 * Please see graph.bfs.MinimunJumpToArrayEnd also.
 */
public class MinimunJumpToArrayEnd {
	final int [] array;
	Vertex [] graph = null;
	public MinimunJumpToArrayEnd(int [] array) {
		this.array = array;
	}

	/**
	 * 
	 * @return
	 */
	public boolean [][] buildTable1() {
		boolean [][] table = new boolean[this.array.length][this.array.length];
		// initialize 
		for (int i = 0; i < table.length; i ++) {
			for (int j = 0; j < table.length; j ++) {
				table[i][j] = false;
			}
		}
		
		for (int j = 0; j < table.length; j ++) {
			for (int k = 1; k <= this.array[j]; k ++) {
				if (j + k < table.length) {
					table[j + k][j] = true;
				}
				else {
					break;
				}
			}
		}
		return table;
	}
	
	/**
	 * By input as {1,3,5,8,9,2,6,7,6,8,9}
	 * We can build the table as following:
	 * F -- not reachable, initialized as Integer.MAX_VALUE
	 * # -- The minimum jumps
	 * 
	 * 0    F	F	F	F	F	F	F	F	F	F	F
	 * 1	F	1	F	F	F	F	F	F	F	F	F
	 * 3	F	F	2	2	2	F	F	F	F	F	F
	 * 5	F	F	F	3	3	3	3	3	F	F	F
	 * 8	F	F	F	F	3	3	3	3	3	3	3
	 * 9	F	F	F	F	F	4	4	4	4	4	4
	 * 2	F	F	F	F	F	F	4	4	F	F	F
	 * 6	F	F	F	F	F	F	F	4	4	4	4
	 * 7	F	F	F	F	F	F	F	F	4	4	4
	 * 6	F	F	F	F	F	F	F	F	F	4	4
	 * 8	F	F	F	F	F	F	F	F	F	F	4
	 * 9	F	F	F	F	F	F	F	F	F	F	F
	 * 
	 * if 
	 * i > and j ^
	 * 
	 * From above table, we have
	 * minJumps = min{a(0), ..., a(j - 1)}
	 * if minJumps !=  Integer.MAX_VALUE, then
	 * 		a(j,i) = minJumps + 1
	 * else if a(j - 1,i - 1) != Integer.MAX_VALUE, then
	 * 		a(j,i) = a(j - 1,i - 1) + 1
	 * else if i == 1 and j == 1
	 * 		a(j,i) = 1
	 * else 
	 * 		stop
	 * 
	 * we doo not want to compute minJumps every time, we need to have an
	 * array to cache the this min value for every column we scanned.
	 */
	public int [][] buildTable() {
		int [][] table = new int[this.array.length + 1][this.array.length];
		int [] cached = new int[table.length]; 
		// initialize 
		for (int i = 0; i < table.length; i ++) {
			cached[i] = Integer.MAX_VALUE;
			for (int j = 0; j < table[0].length; j ++) {
				table[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for (int j = 1; j < table.length; j ++) {
			for (int i = j; i < table[0].length; i ++) {
				if (table[j][i - 1] != Integer.MAX_VALUE && i < (j + this.array[j - 1])) {
					table[j][i] = table[j][i - 1];
					if (table[j][i] < cached[i]) {
						cached[i] = table[j][i];
					}
					continue;
				}
				else if (i == (j + this.array[j - 1])) {
					break;
				}
				
				if (cached[j] != Integer.MAX_VALUE) {
					table[j][i] = cached[j] + 1;
				}
				else if (table[j - 1][i - 1] != Integer.MAX_VALUE) {
					table[j][i] = table[j - 1][i - 1] + 1;
					if (table[j][i] < cached[i]) {
						cached[i] = table[j][i];
					}
				}
				else if (i == 1 && j == 1) {
					table[j][i] = 1;
					cached[i] = 1;
					break;
				}
				else {
					break;
				}
			}
		}
		
		return table;
	}
}
