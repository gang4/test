package dynamic.programming.interview;

import dynamic.programming.LongestCommonSequence;

/*
 * This is the samilar problem as LongestCommonSequence
 * 
 * We can make second input by reverse the order of input string
 */
public class LongestPalindromicSubsequence {
	final public String s;
	public LongestPalindromicSubsequence(String s) {
		this.s = s;
	}
	
	public int[][] buildTable() {
		StringBuilder builder = new StringBuilder();
		for (int i = this.s.length() - 1; 0 <= i; i --) {
			builder.append(this.s.charAt(i));
		}
		return new LongestCommonSequence(this.s, builder.toString()).buildTable();
	}
}
