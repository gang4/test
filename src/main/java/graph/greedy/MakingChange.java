package graph.greedy;

/*
 * Making change: You are given n types of coins with values v1, ..., vn 
 * and a cost C. You may assume v1 = 1 so that it is always possible to 
 * make any cost. Give an algorithm for finding the smallest number of 
 * coins required to sum to C exactly.
 * 
 * https://en.wikipedia.org/wiki/Knapsack_problem
 */
public class MakingChange {
	final int [] v;
	final int c;
	public MakingChange(int [] v , int c) {
		this.v = v;
		this.c = c;
	}
	
	public int [][] build() {
		// This is a more generic knapsack problem
		// The first v == 1
		int cost = this.c + 1;
		int [][] table = new int [cost][v.length];
		for (int i = 0; i < cost; i ++) {
			table[i][0] = i;
		}
		

		/**
		 * We use table to store computed value of number of coins we have used.
		 * For the coins in v(1), ..., v(n), we have two cases.
		 * case 1:
		 * 		if coin[k] > charge (this is a value <= this.c), then
		 * 			table[i][k] = table[i - 1][k];
		 * 		we inherent from left of current.
		 * case 2:
		 * 		if coin[k] <= charge (this is a value <= this.c), then
		 * 			table[i][k]	= minimum {table[i - 1][k], table[i - 1][charge - x * coins[i]] + x}
		 * 		where 0 <= charge - x * coins[i]. The x is number of coins used for coins[i]. So
		 * 		it value will be from 1 if 0 <= charge - 1 * coins[i], to the max of 
		 * 		0 <= charge - x * coins[i], that is, x <= charge / coins[i]  
		 */
		for (int i = 1; i < table[0].length; i ++) {
			for (int j = 0; j < cost; j ++) {
				if (j < v[i]) {
					table[j][i] = table[j][i - 1];
				}
				else {
					int count = j / v[i]; // floor
					table[j][i] = Math.min(table[j][i - 1], table[j - count * v[i]][i - 1] + count);
				}
			}
		}
		return table;
	}
}
