package graph.bfs;

import java.util.List;

import graph.Vertex;

/**
 * 
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 *
 */
public class MinimunJumpToArrayEnd {
	final int [] array;
	Vertex [] graph = null;
	public MinimunJumpToArrayEnd(int [] array) {
		this.array = array;
	}
	
	/**
	 * Actually, this can be modeled as a graph (can also be DP)
	 * For example 2,3,1,1,4, The shortest jump will be 2,3,4
	 * you can model it as
	 * 	2->3, 2->1; 3->1, 3->1, 3->4; 1->1; 1->4.
	 * @return 
	 */
	public List<Vertex> findPath() {
		// (1) Build graph
		this.graph = new Vertex[this.array.length];
		for (int i = 0; i < this.array.length; i ++) {
			graph[i] = new Vertex(i);
		}
		
		for (int i = 0; i < this.array.length; i ++) {
			List<Vertex> list = graph[i].getConnected();
			for (int j = 0; j < array[i]; j ++) {
				if (this.array.length <= (i + j + 1)) {
					break;
				}
				list.add(graph[i + j + 1]);
			}
		}
		
		MinDistance m = new MinDistance(graph[0], graph[this.array.length - 1]);
		List<Vertex> list = m.search();
		return list;
	}
}
