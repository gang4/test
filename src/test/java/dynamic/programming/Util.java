package dynamic.programming;

import java.util.Arrays;

public class Util {
	static void dumpInput(Integer [] v, Integer [] w) {
		Arrays.asList(v).stream().forEach(i -> System.out.print(" 	" + i));
		System.out.println();
		Arrays.asList(w).stream().forEach(i -> System.out.print(" 	" + i));
		System.out.println();	
	}
	
	static public void dump(int [] a) {
		for (int i = 0; i < a.length; i ++) {
			System.out.print("	" + a[i]);
		}
		System.out.println();
	}
	
	static public void dump(int [][] table) {
		for (int i = table[0].length - 1; 0 <= i; i --) {
			for (int j = 0; j < table.length; j ++) {
				if (table[j][i] == Integer.MAX_VALUE) {
					System.out.print("	" + "-");
				}
				else {
					System.out.print("	" + table[j][i]);
				}
			}
			System.out.println();
		}
	}
	
	static public void dump(boolean [][] table, int[] set) {
		for (int i = 0; i < table[0].length; i ++) {
			System.out.print(set[i]);
			for (int j = 0; j < table.length; j ++) {
				System.out.print("	" + (table[j][i]? "T" : "F"));
			}
			System.out.println();
		}
	}
	
	static public void dump(double [][] table) {
		for (int i = 0; i < table[0].length; i ++) {
			for (int j = 0; j < table.length; j ++) {
				System.out.printf(" %.2f", table[j][i]);
			}
			System.out.println();
		}
	}
}
