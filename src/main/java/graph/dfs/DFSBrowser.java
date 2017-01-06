package graph.dfs;

import graph.Vertex;

public class DFSBrowser {
	final Vertex [] vertice;
	
	public DFSBrowser(Vertex [] in) {
		this.vertice = in;
	}
	
	public void browser() {
		for (Vertex vertex: this.vertice) {
			visit(vertex);
		}
	}

	public void visit(Vertex vertex) {
		vertex.setVisited(true);
		for (Vertex v : vertex.getConnected()) {
			if (v.isVisited()) {
				continue;
			}
			visit(v);
		}
	}
}
