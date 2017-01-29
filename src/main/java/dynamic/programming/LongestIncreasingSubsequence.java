package dynamic.programming;

public class LongestIncreasingSubsequence {
	final public int [] s;
	public LongestIncreasingSubsequence(int [] s) {
		this.s = s;
	}

	/**
	 * This is the similar issue as LongestCommonSequence.
	 * We can sort input s, then find common subsequence
	 * among them. O(n * n)
	 * 
	 * Implement in another way in O(n log n) here.
	 * For input s(0), ..., s(n)
	 * Assume we have increasing subsequences in them, such as 
	 * a(0,0), ..., a(0,j0),
	 * a(1,0), ..., a(1,j1),
	 * ...
	 * and a(i,0), ..., a(i,j) is the longest one
	 * If we add another element a(j + 1), we have 
	 * case 1: 	a(i,0) <, ..., < a(i,j) < a(j + 1), become the longest one 
	 * 			Then new sequence is: 
	 * 			a(i+1,0) < a(i+1,2) <, ..., <  a(i+1,j) < a(i+1,j+1)
	 * 			where a(i+1,j+1) = a(j + 1);
	 * case 2: 	if a(j + 1) < a(i, j). Then, new sequence is:
	 * 			a(i+1,0) <, ..., a(i+1,k-1) < a(i+1,k).
	 * 			where a(i+1,k) = a(j + 1); in case it may grow longer,
	 * 			such as the tail become a(i+1,k+1) <, ..., < a(i+1,j+1).
	 * 
	 * 			How many such sequences we need to track. Actually, only one
	 * 			Because every such new sequence occurs, we can always get 
	 * 			rid of either track sequence or current one. One of two 
	 * 			exists will be tracked again and current will be new 
	 * 			a sequence. 
	 * @return
	 */
	public int [] buildTable() {
		int [] old = null;
		int [] current = new int[s.length];
		int oldLast = 0;
		int currentLast = 0;
		
		// Loop each element in sequence.
		for (int i = 0; i < s.length; i ++) { // row/each element
			if (current[currentLast] < s[i]) {
				currentLast ++;
				current[currentLast] = s[i];
				oldLast = add2Tail(old, oldLast, s[i]);
			}
			else {
				int position = binarySearch(current, 0, currentLast, s[i]);
				if (currentLast == position) {
					current[currentLast] = s[i];
					oldLast = add2Tail(old, oldLast, s[i]);
				}
				else {
					if (oldLast < currentLast) {
						old = current;
						oldLast = currentLast;
						current = resetCurrent(current, currentLast, position, s[i]);
						currentLast = position;
					}
					else {
						current = resetCurrent(current, currentLast, position, s[i]);
						currentLast = position;
					}
				}
			}
		}
		return (oldLast < currentLast) ? current : old;
	}
	
	private int [] resetCurrent(int[] current, int currentLast, int position, int s) {
		int [] tmp = current;
		current = new int[this.s.length];
		for (int j = 0; j < position; j ++) {
			current[j] = tmp[j];
		}
		currentLast = position;
		current[currentLast] = s;
		return current;
	}

	private int add2Tail(int[] old, int oldLast, int element) {
		if (old != null) {
			if (old[oldLast] < element) {
				oldLast ++;
				old[oldLast] = element;
			}
			else {
				int position = binarySearch(old, 0, oldLast, element);
				if (oldLast < position) {
					oldLast ++;
					old[oldLast] = element;
				}
				else if (oldLast == position) {
					old[oldLast] = element;
				}
			}
		}
		return oldLast;
	}
	
	int binarySearch(int [] a, int start, int end, int v) {
		int len = end - start;
		if (len <= 0) {
			return start;
		}
		
		int index = start + (len / 2);
		if (v < a[index]) {
			index = binarySearch(a, start, index, v);
		}
		else {
			index = binarySearch(a, index + 1, end, v);
		}
		
		return index;
	}
	
	public static void main(String [] args) {
		//int [] s = {1,4,7,9,13,15};
		int [] s = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		new LongestIncreasingSubsequence(s).buildTable();
		int last = new LongestIncreasingSubsequence(s).binarySearch(s, 0, s.length, 8);
		System.out.println(last + ";" + s[last]);
	}
}
