package dynamic.programming.interview;

import dynamic.programming.LongestCommonSubstring;

/**
 * 
 * https://www.quora.com/What-are-the-top-10-most-popular-dynamic-programming-problems-among-interviewers
 *
 * This is the similar problem as LongestCommonSubstring.
 * We only need to form the second string by reverse order
 * of input string.
 * 
 */
public class LongestPalindromicSubstring {
	final public String s;
	public LongestPalindromicSubstring(String s) {
		this.s = s;
	}
	
	public int[][] buildTable() {
		StringBuilder builder = new StringBuilder();
		for (int i = this.s.length() - 1; 0 <= i; i --) {
			builder.append(this.s.charAt(i));
		}
		return new LongestCommonSubstring(this.s, builder.toString()).buildTable();
	}
}
