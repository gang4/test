package dynamic.programming;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import graph.GraphGenerator;

public class TestMaxSqureMatrix {
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int wide = new GraphGenerator().autoGenUnnsigned(r);
		int len = new GraphGenerator().autoGenUnnsigned(r);
		int [][] matrix = new int[wide][len];
		for (int i = 0; i < wide; i ++) {
			for (int j = 0; j < len; j ++) {
				matrix[i][j] = Math.abs(r.nextInt(2));
			}
		}
		System.out.println("Input matrix");
		Util.dump(matrix);
		int[][] table = new MaxSqureMatrix(matrix).buildTable();
		System.out.println("Output matrix");
		Util.dump(table);
	}
}
