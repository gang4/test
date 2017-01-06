package graph.dfs;

import java.util.ArrayList;
import java.util.List;

import graph.Vertex;

public class SCC {
	public static class Node {
		int score = 0;
		final Vertex v;
		List<Node> connected = new ArrayList<>();
		public Node(Vertex v) {
			this.v = v;
		}
	}
	
	final Vertex [] vertice;
	final Node [] reversedNodes;
	int score = 0;
	final Vertex [] scoredVertice;
	
	public SCC(Vertex [] vertice) {
		this.vertice = vertice;
		reversedNodes = new Node[this.vertice.length];
		scoredVertice = new Vertex [this.vertice.length];
		
		for (int i = 0; i < this.vertice.length; i++) {
			reversedNodes[i] = new Node(this.vertice[i]);
		}
		for (int i = 0; i < this.vertice.length; i++) {
			List<Vertex> list = this.vertice[i].getConnected(); 
			if (list != null) {
				for (Vertex v : list) {
					this.reversedNodes[v.getId()].connected.add(reversedNodes[this.vertice[i].getId()]);
				}
			}
		}
		System.out.println(" --------------- SCC reversed node-------------------------");
		dump();
	}

	public List<Vertex> search() {
		// Reverse DFS and score every node. 
		for (Node n: this.reversedNodes) {
			if (n.v.isVisited()) {
				continue;
			}
			visit(n);
			n.score = this.score;
			scoredVertice[this.score] = n.v;
			this.score ++;
		}
		// Score
		System.out.println(" --------------- SCC score-------------------------");
		dump();
		for (Vertex v : this.scoredVertice) {
			assert v != null;
		}
		
		// Find SCC
		for (int i = this.scoredVertice.length - 1; i >= 0; i --) {
			this.scoredVertice[i].setVisited(false);
		}
		// Save leading node for each SCC
		List<Vertex> scc = new ArrayList<>();
		DFSBrowser browser = new DFSBrowser(this.scoredVertice);
		for (int i = this.scoredVertice.length - 1; i >= 0; i --) {		
			if (this.scoredVertice[i].isVisited()) {
				continue;
			}
			scc.add(this.scoredVertice[i]);
			browser.visit(this.scoredVertice[i]);
		}
		return scc;
	}

	private void visit(Node n) {
		n.v.setVisited(true);
		for (Node node : n.connected) {
			if (node.v.isVisited()) {
				continue;
			}
			visit(node);
			node.score = this.score;
			scoredVertice[node.score] = node.v;
			this.score ++;
		}
	}
	
	private void dump() {
		for (Node node : reversedNodes) {
			System.out.println("[" + node.v.getId() + "]." + node.score);
			System.out.print("connected: ");
			for (Node n : node.connected) {
				System.out.print("[" + n.v.getId() + "]." + n.score);
			}
			System.out.println("\n");
		}
	}
}
