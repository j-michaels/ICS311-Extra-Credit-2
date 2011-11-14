package ics311;

public class Edge {
	private Vertex u;
	private Vertex v;
	private Object annotation;
	private boolean directed;
	
	public Edge(Vertex u, Vertex v, Object info, boolean d) {
		this.u = u;
		this.v = v;
		u.insertAdjacentEdge(this);
		v.insertAdjacentEdge(this);
		annotation = info;
		this.directed = d;
	}
	
	public Vertex[] endVertices() {
		Vertex[] va = new Vertex[2];
		va[0] = u;
		va[1] = v;
		return va;
	}

	public Vertex origin() {
		return u;
	}
	
	public Vertex destination() {
		return v;
	}
	
	public void setDirected(boolean d) {
		directed = d;
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDestination(Vertex newDestination) {
		setDirected(true);
		u = newDestination;
	}

	public void setOrigin(Vertex newOrigin) {
		setDirected(true);
		v = newOrigin;
	}

}
