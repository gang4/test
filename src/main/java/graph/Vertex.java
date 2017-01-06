package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {	
	final int id;
	boolean visited = false;
	List<Vertex> connected = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public List<Vertex> getConnected() {
		return connected;
	}
	public void setConnected(List<Vertex> list) {
		connected = list;
	}
	public Vertex(int index) {
		this.id = index;
	}
}
