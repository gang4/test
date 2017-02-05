package dynamic.programming;

import org.junit.Test;

public class TestBoxStacking {
	@Test
	public void test() {
		int [] b1 = {1, 3, 5};
		int [] b2 = {4, 4, 4};
		int [][] boxes = {b1, b2};
		BoxStacking b = new BoxStacking(boxes);
		int [][]table = b.build();
		Util.dump(table);
		//Assert.assertTrue(rt == 1);
	}
}
