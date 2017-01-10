package dynamic.programming;

import java.util.Arrays;

public class Util {
	static void dumpInput(Integer [] v, Integer [] w) {
		Arrays.asList(v).stream().forEach(i -> System.out.print(" 	" + i));
		System.out.println();
		Arrays.asList(w).stream().forEach(i -> System.out.print(" 	" + i));
		System.out.println();	
	}
	
	static void dump(int [][] table) {
		for (int i = table[0].length - 1; 0 <= i; i --) {
			for (int j = 0; j < table.length; j ++) {
				System.out.print(" " + table[j][i]);
			}
			System.out.println();
		}
	}
}
