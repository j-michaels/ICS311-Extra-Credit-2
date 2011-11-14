package ics311;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Vertex {
	Object data;
	Map<Object,Object> annotations;
	
	ArrayList<Edge> adjacentEdges;
	
	public Vertex(Object data) {
		this.data = data;
	}
	
	// Accessors
	
	public int inDegree() {
		return inAdjacentVerticesArray().size();
	}
	
	public int outDegree() {
		return outAdjacentVerticesArray().size();
	}
	
	public Iterator<Vertex> inAdjacentVertices() {
		return inAdjacentVerticesArray().iterator();
	}
	
	private ArrayList<Vertex> inAdjacentVerticesArray() {
		Iterator<Edge> itr = adjacentEdges.iterator();
		ArrayList<Vertex> va = new ArrayList<Vertex>();
		
		while (itr.hasNext()) {
			// TODO: Clean this up.
			Edge e = itr.next();
			Vertex[] ev = e.endVertices();
			if ((ev[1] == this) && (ev[0] != this)) {
				va.add(ev[0]);
			}
		}
		
		return va;
	}

	// Returns an iterator over the vertices adjacent to this vertex by outgoing edges.
	public Iterator<Vertex> outAdjacentVertices() {
		return outAdjacentVerticesArray().iterator();
	}
	
	private ArrayList<Vertex> outAdjacentVerticesArray() {
		Iterator<Edge> itr = adjacentEdges.iterator();
		ArrayList<Vertex> va = new ArrayList<Vertex>();
		
		while (itr.hasNext()) {
			// TODO: Clean this up.
			Edge e = itr.next();
			Vertex[] ev = e.endVertices();
			if ((ev[0] == this) && (ev[1] != this)) {
				va.add(ev[0]);
			}
		}
		
		return va;
	}
	
	// Mutators
	
	public void insertAdjacentEdge(Edge edge) {
		// TODO Auto-generated method stub
		adjacentEdges.add(edge);
	}
	
	// Annotators
	
	public void setAnnotation(Object k, Object o) {
		annotations.put(k, o);
	}
	
	public Object getAnnotation(Object k) {
		return annotations.get(k);
	}
	
	public Object removeAnnotation(Object k) {
		return annotations.remove(k);
	}
 }