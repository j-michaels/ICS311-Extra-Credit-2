package ics311;

import java.util.ArrayList;
import java.util.Iterator;

public class DirectedGraph {
	ArrayList<Edge> edges;
	ArrayList<Vertex> vertices;
	
	public int numVertices() {
		return vertices.size();
	}
	
	public Iterator<Vertex> vertices() {
		return vertices.iterator();
	}

	public int numEdges() {
		return edges.size();
	}
	
	public Iterator<Edge> edges() {
		return edges.iterator();
	}
	
	// Undirected Graph accessors
	
	public int degree(Vertex v) {
		return adjacentVerticesArray(v).size();
	}

	public ArrayList<Vertex> adjacentVerticesArray(Vertex v) {
		Iterator<Edge> itr = edges.iterator();
		ArrayList<Vertex> va = new ArrayList<Vertex>();
		
		while (itr.hasNext()) {
			Edge e = itr.next();
			Vertex[] ev = endVertices(e);
			if ((ev[0] == v) && (ev[1] != v)) {
				va.add(ev[1]);
			} else if ((ev[1] == v) && (ev[0] != v)) {
				va.add(ev[0]);
			}
		}
		
		return va;
	}
	
	public Iterator<Vertex> adjacentVertices(Vertex v) {
		return adjacentVerticesArray(v).iterator();
	}
	
	public Iterator<Edge> incidentEdges(Vertex v) {
		ArrayList<Edge> ea = new ArrayList<Edge>();
		Iterator<Edge> itr = edges.iterator();
		while (itr.hasNext()) {
			Edge e = itr.next();
			Vertex[] ev = endVertices(e);
			if ((ev[0] == v) || (ev[1] == v)) {
				ea.add(e);
			}
		}
		
		return ea.iterator();
	}
	
	public Vertex[] endVertices(Edge e) {
		Vertex[] va = new Vertex[2];
		va[0] = e.destination();
		va[1] = e.origin();
		return va;
	}
	
	public Vertex opposite(Vertex v, Edge e) throws InvalidEdgeException {
		if (e.destination() == v) {
			return e.origin();
		} else if (e.origin() == v) {
			return e.destination();
		} else {
			throw new InvalidEdgeException();
		}
	}
	
	public boolean areAdjacent(Vertex v1, Vertex v2) {
		Iterator<Edge> itr = edges.iterator();
		
		while (itr.hasNext()) {
			Vertex[] va = endVertices(itr.next());
			if (((va[0] == v1) && (va[1] == v2)) || ((va[0] == v2) && (va[1] == v1))) {
				return true;
			}
		}
		
		return false;
	}
	
	// Directed Graph Accessors
	public Iterator<Edge> directedEdges() {
		ArrayList<Edge> directedEdges = new ArrayList<Edge>();
		Iterator<Edge> itr = edges.iterator();
		
		while (itr.hasNext()) {
			Edge e = itr.next();
			if (e.isDirected() == true) {
				directedEdges.add(e);
			}
		}
		return directedEdges.iterator();
	}
	
	public Iterator<Edge> undirectedEdges() {
		ArrayList<Edge> directedEdges = new ArrayList<Edge>();
		Iterator<Edge> itr = edges.iterator();
		
		while (itr.hasNext()) {
			Edge e = itr.next();
			if (e.isDirected() == false) {
				directedEdges.add(e);
			}
		}
		return directedEdges.iterator();
	}
	
	public int inDegree(Vertex v) {
		return v.inDegree();
	}
	
	public int outDegree(Vertex v) {
		return v.outDegree();
	}
	
	public Iterator<Vertex> inAdjacentVertices(Vertex v) {
		return v.inAdjacentVertices();
	}
	
	public Iterator<Vertex> outAdjacentVertices(Vertex v) {
		return v.inAdjacentVertices();
	}
	
	// Mutators
	
	public Edge insertEdge(Vertex u, Vertex v, Object info) {
		Edge e = new Edge(u, v, info, false);
		edges.add(e);
		return e;
	}
	
	public Vertex insertVertex(Object info) {
		Vertex v = new Vertex(info);
		vertices.add(v);
		return v;
	}
	
	public Edge insertDirectedEdge(Vertex u, Vertex v, Object info) {
		Edge e = new Edge(u, v, info, true);
		edges.add(e);
		return e;
	}
	
	public Object removeVertex(Vertex v) {
		vertices.remove(v);
		v.removeSelf();
		// TODO: remove each edge on v and remove associated edges on other v's
		
		return v;
	}
	
	public Object removeEdge(Edge e) {
		edges.remove(e);
		Vertex[] va = e.endVertices();
		va[0].removeSelf();
		va[1].removeSelf();
		// TODO: Same as above.
		
		return e;
	}
	
	public void setDirectionFrom(Edge e, Vertex newOrigin) {
		e.setOrigin(newOrigin);
	}
	
	public void setDirectionTo(Edge e, Vertex newDestination) {
		e.setDestination(newDestination);
	}
	
	public void makeUndirected(Edge e) {
		e.setDirected(false);
	}
	
	public void reverseDirection(Edge e) throws InvalidEdgeException {
		if (e.isDirected() == false) {
			throw new InvalidEdgeException();
		} else {
			Vertex u = e.origin();
			e.setOrigin(e.destination());
			e.setDestination(u);
		}
	}
	
	public void dfs() {
		
	}
	
	public void scc() {
		
	}
}