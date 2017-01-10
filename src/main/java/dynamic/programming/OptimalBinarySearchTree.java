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
 * (0) a(k) = sum(p(i) ... p(j)) + a(i, k-1) + a(k+1,j)
 * (1) 	for all keys that under a(k), they all have to pass a(k), that is where 
 * 		sum(p(i) ... p(j)) comes from.
 * (2) 	a(i, k-1) is the tree that has keys less than the key for a(k)
 * 		It is a optimal tree.
 * (3) 	a(k+1, j) is the tree that has keys bigger than the key for a(k)
 * 		It is a optimal tree.	
 * (4) 	sine k in i <= k <= j range, we need to compute will a(k)s in this range.
 * (5) 	All those ranges are
 * 		for every range of keys in 1 <= i <= j <= n 
 *			select the root k within i <= k <= j
 *			for every such k by using (0) above
 * 				a(k) = sum(p(i) ... p(j)) + a(i, k-1) + a(k+1,j)
 * 			optimal tree = min{a(i) ....a(j)}
 * 		So the runtime is O(n * n * n)
 */
public class OptimalBinarySearchTree {

}
