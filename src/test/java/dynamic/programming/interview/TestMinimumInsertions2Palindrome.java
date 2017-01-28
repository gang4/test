package dynamic.programming.interview;

import org.junit.Assert;
import org.junit.Test;

public class TestMinimumInsertions2Palindrome {
	@Test
	public void test() {
		String out = new MinimumInsertions2Palindrome().convert("abcda");
		System.out.println(out);
		Assert.assertTrue(out.compareTo("adcbcda") == 0);
	}
	
	@Test
	public void test1() {
		String out = new MinimumInsertions2Palindrome().convert("ab");
		System.out.println(out);
		Assert.assertTrue(out.compareTo("bab") == 0);
	}
	
	@Test
	public void test2() {
		String out = new MinimumInsertions2Palindrome().convert("aa");
		System.out.println(out);
		Assert.assertTrue(out.compareTo("aa") == 0);
	}
	
	@Test
	public void test3() {
		String out = new MinimumInsertions2Palindrome().convert("abcd");
		System.out.println(out);
		Assert.assertTrue(out.compareTo("dcbabcd") == 0);
	}
}
