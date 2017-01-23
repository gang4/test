package dynamic.programming;

import org.junit.Test;

public class TestMinimunJumpToArrayEnd {
	@Test
	public void test() throws Exception {
		int [] array = {1,3,5,8,9,2,6,7,6,8,9};
		MinimunJumpToArrayEnd m = new MinimunJumpToArrayEnd(array);
		boolean [][] table = m.buildTable1();
		Util.dump(table, array);
		System.out.println();
		m.buildTable();
		int [][] t = m.buildTable();
		Util.dump(t);
		System.out.println();
	}
	
	@Test
	public void test1() throws Exception {
		int [] array = {2,3,1,1,4};
		MinimunJumpToArrayEnd m = new MinimunJumpToArrayEnd(array);
		m.buildTable();
		int [][] t = m.buildTable();
		Util.dump(t);
		System.out.println();
	}
}
