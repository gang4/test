package graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import graph.Vertex;

public class MinDistance {
	static class Node {
		final Vertex self;
		final Node pair;
		public Node(Vertex self, Node pair) {
			this.self = self;
			this.pair = pair;
		}
	}
	final Vertex start;
	final Vertex end;
	public MinDistance(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
	}
	
	public List<Vertex> search() {
		Queue<Node> q = new ArrayDeque<>();
		Node v = new Node(this.start, null);
		q.add(v);
		// Tracking the path
		while (!q.isEmpty()) {
			v = q.remove();
			//System.out.println("v : " + v.self.getId() + " end : " + this.end.getId());
			if (v.self.getId() == this.end.getId()) {
				break;
			}
			v.self.setVisited(true);
			for (Vertex c: v.self.getConnected()) {
				if (c.isVisited()) {
					continue;
				}
				//System.out.println("c : " + c.getId());
				q.add(new Node(c, v));
			}
		};
		
		if (v.self.getId() == this.end.getId()) {
			List<Vertex> path = new ArrayList<>();
			do {
				path.add(0, v.self);
				v = v.pair;
			} while (v != null);
			return path;
		}
		else {
			return null;
		}
	}
}
