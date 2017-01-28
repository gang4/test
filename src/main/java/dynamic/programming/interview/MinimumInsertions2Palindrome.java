package dynamic.programming.interview;

/**
 * 
 * This is hardly a DP problem.
 * By name we can figure out how to do it
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-28-minimum-insertions-to-form-a-palindrome/
 * 
 */
public class MinimumInsertions2Palindrome {
	public String convert(String in) {
		StringBuilder buidler = new StringBuilder(in);
		int len = in.length() / 2;
		for (int i = 0; i < len; i ++) {
			char c = in.charAt(in.length() - 1 - i);
			if (in.charAt(i) != c) {
				buidler.insert(i, c);
				in = buidler.toString();
				len ++;
			}
		}
		return buidler.toString();
	}
}
