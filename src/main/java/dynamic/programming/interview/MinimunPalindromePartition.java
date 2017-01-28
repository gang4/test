package dynamic.programming.interview;

import dynamic.programming.LongestCommonSubstring;

/**
 * 
 * Given a string s, partition s such that every substring of the partition is 
 * a palindrome
 *
 */
public class MinimunPalindromePartition {
	/**
	 * case 1:
	 * Let's make a (1) sub problem (2) how they overlap each others
	 * 
	 * Assume we have a optimal sub partition already, that is
	 * a(1), ..., a(i), each of them is a palindrome or single char.
	 * First, we need to proof only a(i - 1) and a(i) matter, except 
	 * we flag, then a(i - 2) matters. Let's proof this
	 * Let say if the last char of a(i - 2) is x and we can form
	 * xa(i-1)a(i)x. Then a(i - 1)a(j) has to be a palindrome, which
	 * contradictory to that a(1), ..., a(i) is optimal.
	 * But how about the last char of a(i - 1) is x and if we break
	 * a(i - 1), it will generate too many single chars, then we have to
	 * delay to form palindrome ...x1x2xa(i)xx1x2.... In this case, we
	 * have a(i - 1), a(i), x, x1, x2, ..., then we set flag to indicate
	 * this case.
	 * 
	 * Now add next char x into it. we will have following cases
	 * (1) 	a(i) and x forms a new palindrome, such as a(i) is composed
	 * 		by x or x's only. 
	 * (2)  The last char of a(i - 1) is x, set up flag, if break a(i - 1)
	 * 		will generate many single chars than 2 (by join xa(i)x, we only
	 * 		make two to one), then go on until either we join more than
	 * 		break down or pattern itself break, such as yx1x, a(i), x, x1, x2
	 * (3)	no flag and x is neither cases above. 
	 *      
	 * case 2:
	 * 	We reverse the input string so that we have s1 and s2
	 *  find all match string in s1 and s2 by 2D table either true or false,
	 *  by integer. Then, the problem become to find best combination of
	 *  those matches strings so that the cuts is minimum. The matches strings
	 *  line in diagnose in the table.
	 *  example 
	 *  String s1 = "abcbaab"; 
	 *  String s2 = "baabcba"; // reversed the order of s1
	 *  
	 *     a b c b a a b
	 *   b f T f T f f t
	 *   a T f f f T t f
	 *   a T f f f t T f
	 *   b f T f t f f T
	 *   c f f T f f f f
	 *   b f t f T f f t
	 *   a t f f f T T f
	 *   
	 *   So by scanning table, we have two match strings, 
	 *   "baab" and "abcba" if select "baab", we have a,b,c,baab.
	 *   if select "abcba", we abcba,a,b. then, later is better. 
	 *   
	 *   How to scan the table?
	 *   by loop each column from left to right, such as 
	 *   column1, (1) f, we a, (2) T, next one in diagnos is f, then
	 *   we have a, b. (3) T, next one in diagnos, it is a T, ... to
	 *   the end, we have a,b, "abcba". Then next column, .... 
	 */
	final public String s;
	public MinimunPalindromePartition(String s) {
		this.s = s;
	}
	
	public String buildTable() {
		StringBuilder builder = new StringBuilder();
		for (int i = this.s.length() - 1; 0 <= i; i --) {
			builder.append(this.s.charAt(i));
		}
		String s2 = builder.toString();
		// Fill table
		boolean [][] table = new LongestCommonSubstring(this.s, s2).buildTable0();
		s2 = " " + s2;
		String s1 = " " + this.s;
		dump(table, s1, s2);
		// Scan table
		int min = Integer.MAX_VALUE;
		String rtn = "";
		for (int i = 1; i < table.length; i ++) {
			int count = 0;
			builder = new StringBuilder();
			for (int j = 1; j < table[0].length; j ++) {
				if (!table[i][j]) {
					builder.append(s2.charAt(j));
					builder.append(',');
					count ++;
				}
				else {
					// Found match, trace how long it is.
					int len = Math.min(table.length - i, table.length - j);
					builder.append(s2.charAt(j));
					int k = 1;
					for (; k < len; k++) {
						if (!table[i + k][j + k]) {
							break;
						}
						else {
							builder.append(s2.charAt(j + k));							
						}
					}
					builder.append(',');
					count ++;
					j += k - 1;
				}
			}
			
			if (count < min) {
				rtn = builder.toString();
				min = count;
			}
		}
		return rtn;
	}
	
	public void dump(boolean [][] table, String s1, String s2) {
		for (int i = 0; i < table.length; i ++) {
			System.out.print("	" + s1.charAt(i));
		}
		System.out.println();
		for (int i = 0; i < table[0].length; i ++) {
			System.out.print(s2.charAt(i));
			for (int j = 0; j < table.length; j ++) {
				System.out.print("	" + (table[j][i]? "T" : "F"));
			}
			System.out.println();
		}
	}
}
