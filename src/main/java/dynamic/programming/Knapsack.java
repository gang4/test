package dynamic.programming;

import java.util.ArrayList;
import java.util.List;

/**
 * For i = 1 to n  
 * 		for j = 0 to max{wi}
 * 			a[i, x] = max{a[i -1, j], a[i - 1, j - wi] + vi}
 * @author yyu
 *
 */
public class Knapsack {
	final Integer [] v;
	final Integer [] w;
	int maxCapacity;
	public Knapsack(Integer [] v, Integer [] w, int maxCapacity) {
		this.v = v;
		this.w = w;
		this.maxCapacity = maxCapacity + 1;
		int total = 0;
		for (int i = 0; i < w.length; i ++) {
			total += w[i];
		}
		if (this.maxCapacity > total + 1) {
			this.maxCapacity = total + 1;
		}
	}
	
	/**
	 * Run time O(this.maxCapacity * v.length)
	 * This is bad because if this.maxCapacity = 1,000,000, but 
	 * we only have 10 elements in w, we still need a big table.
	 */
	public int [][] buildNaive() {
		int [][] table = new int [v.length][this.maxCapacity];
		for (int i = 1; i < v.length; i ++) {
			for (int j = 0; j < this.maxCapacity; j ++) {
				int rowIndex = j - w[i];
				if (rowIndex < 0) {
					table[i][j] = table[i - 1][j];
				}
				else {
					table[i][j] = Math.max(table[i - 1][j], table[i - 1][rowIndex] + v[i]);
				}
			}
		}
		return table;
	}
	
	public List<Integer> getOptimalSolution(int [][] table) {
		List<Integer> list = new ArrayList<>();
		
		for (int j = table[0].length - 1; 0 <= j; j --) {
			for (int i = table.length - 1; 0 <= i; i --) {
				if (i == 0 || table[i][j] == table[i - 1][j]) {
					int k = i;
					for (; 0 <= k; k --) {
						if (k == 0 || table[k][j] != table[k - 1][j]) {
							break;
						}
					}
					i = k;
				}
				else {
					list.add(i);
					// a[i - 1, j - wi] + vi = table[i][j]
					// a[i - 1, j - wi] = table[i][j] - vi
					int k = j;
					for (; 0 <= k; k --) {
						if (table[i - 1][k] == table[i][j] - v[i]) {
							if (k > 1 && table[i - 1][k] == table[i - 1][k - 1]) {
								continue;
							}
							break;
						}
					}
					j = k;
				}
			}
		}
		return list;
	}
}
