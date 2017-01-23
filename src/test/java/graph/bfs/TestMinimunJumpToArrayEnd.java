package graph.bfs;

import java.util.List;

import org.junit.Test;

import graph.GraphGenerator;
import graph.Vertex;

public class TestMinimunJumpToArrayEnd {
	@Test
	public void test() throws Exception {
		int [] array = {2,3,1,1,4};
		MinimunJumpToArrayEnd m = new MinimunJumpToArrayEnd(array);
		m.findPath();
		
		GraphGenerator.dump(m.graph);
		List<Vertex> list = m.findPath();
		list.stream().forEach(v -> System.out.print("->[" + v.getId() + "]"));
		System.out.println();
	}
	
	@Test
	public void test1() throws Exception {
		int [] array = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		MinimunJumpToArrayEnd m = new MinimunJumpToArrayEnd(array);
		m.findPath();
		
		GraphGenerator.dump(m.graph);
		List<Vertex> list = m.findPath();
		list.stream().forEach(v -> System.out.print("[" + v.getId() + "] "));
		System.out.println();
	}
}
