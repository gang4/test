package dynamic.programming;

/**
 * (0) https://www.coursera.org/learn/algorithm-design-analysis-2/lecture/3wrTN/a-dynamic-programming-algorithm-i
 * 		Stanford Tim
 * (1) http://www.eli.sdsu.edu/courses/fall95/cs660/notes/OBST/OBST.html
 * (2) http://software.ucv.ro/~cmihaescu/ro/laboratoare/SDA/docs/arboriOptimali_en.pdf
 * 
 * For any given subtree, that i <= k <= j. 
 * 						a(k)
 * 					   /   \
 * 			   a(i, k-1)    a(k+1, j)
 * with p(i) as weight of of key(i)
 * we have following:
 * (0) 	a(k) = sum(p(i) ... p(j)) + a(i, k-1) + a(k+1,j)
 * (1) 	for all keys that under a(k), they all have to pass a(k), that is where 
 * 		sum(p(i) ... p(j)) comes from.
 * (2) 	a(i, k-1) is the tree that has keys less than the key for a(k)
 * 		It is a optimal tree.
 * (3) 	a(k+1, j) is the tree that has keys bigger than the key for a(k)
 * 		It is a optimal tree.	
 * (4) 	sine k in i <= k <= j range, we need to compute will a(k)s in this range.
 * 		for every range of keys in 1 <= i <= j <= n 
 *			select the root k within i <= k <= j
 *			for every such k as root (using (0), we have)
 * 				a(k) = sum(p(i) ... p(j)) + a(i, k-1) + a(k+1,j)
 * 			in this range, optimal tree = min{a(i) ....a(j)}
 * 		So the runtime is O(n * n * n)
 */
public class OptimalBinarySearchTree {
	static interface Callback {
		int getIntValue();
	};
	
	final private Integer [] keys;
	final private double [] percent;
	public OptimalBinarySearchTree(Integer [] keys, double [] weight) {
		this.keys = keys;
		this.percent = weight;
	}
	
	/**
	 * The way we will build is as follows, fill in diagonal one by another.
	 * (1) for i = 0, to n, a[i,i]
	 * (2) for i = 0, to n, a[i, i + 1] = p[i] + p[i + 1] + a[i + 1, i + 1];
	 * (3) for i = 0, to n, a[i, i + 2] = p[i] + p[i + 1] + p[i + 2] + a[i, i + 1] + a[i + 1, i + 2]
	 * ...
	 * ..
	 * For every a[i, i + j], we have cached value.
	 * 
	 * @return
	 */
	public int [][] dumpPaThTable(Callback callback) {
		//System.out.println("Path table");
		int [][] table = new int [this.keys.length][this.keys.length];
		// Loop through all continue ranges by following two loops
		for (int i = 0; i < this.keys.length; i ++) {
			for (int j = 0; j < this.keys.length - i; j ++) {
				//System.out.println("i->" + (j + i) + " j->" + (j));
				table[j][j + i] = callback.getIntValue();
			}
			//System.out.println();
		}
		return table;
	}
	
	public double [][] buildTable() throws Exception {
		double [][] table = new double [this.keys.length][this.keys.length];
		// Loop through all continue ranges by following two loops
		for (int i = 0; i < this.keys.length; i ++) {
			for (int j = 0; j < this.keys.length - i; j ++) {
				table[j][j + i] = getOptimalValue(j, i + j, table);
			}
		}
		return table;
	}
	
	/**
	 * a(k) = sum(p(i) ... p(j)) + min{a(i, k-1) + a(k+1,j)}
	 * i <= j
	 * 
	 * We can get the tree in here also.
	 * allocate list. Save each optimal root in sequence.
	 * point the children to its root and root to children
	 */
	private double getOptimalValue(int i, int j, double[][] table) throws Exception {
		if (i == j) {
			return this.percent[i];
		}
		
		// sum(p(i) ... p(j))
		double p = 0.0; 
		for (int k = i; k <= j; k++) {
			p += this.percent[k];
		};
		
		// Optimal subtree within range i <= k <= j
		double a = Double.MAX_VALUE;
		for (int k = i; k <= j; k++) {
			double pLeft = 0.0;
			double pRight = 0.0;
			if (k > i) {
				if (table[i][k - 1] == 0) {
					System.out.println("table[" + (k + 1) + "][" + j + "]");
					throw new Exception("");
				}
				pLeft = table[i][k - 1];
			}
			if (k < j) {
				if (table[k + 1][j] == 0) {
					System.out.println("table[" + (k + 1) + "][" + j + "]");
					throw new Exception("");
				}
				pRight = table[k + 1][j];
			}
			a = Math.min(a,  pLeft + pRight);
		}
		return (a + p);
	}
}
