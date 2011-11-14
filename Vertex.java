package ics311;

import java.util.AbstractList;
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
			if ((ev[1] == this) && (ev[0] != this) && (e.isDirected())) {
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
			if ((ev[0] == this) && (ev[1] != this) && (e.isDirected())) {
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
	
	// This function deletes the edges associated with itself from
	// ADJACENT vertices ONLY. It does not delete these edges from the
	// graph or itself from the graph.
	public void removeSelf() {
		// TODO Auto-generated method stub
		Iterator<Vertex> itr = adjacentVertices();
		
		while (itr.hasNext()) {
			Vertex v = itr.next();
			v.removeEdgesWith(this);
		}
	}

	// Deletes all edges associated with vertex v
	public void removeEdgesWith(Vertex v) {
		// TODO Auto-generated method stub
		Iterator<Edge> itr = adjacentEdges.iterator();
		while (itr.hasNext()) {
			Edge e = itr.next();
			Vertex[] va = e.endVertices();
			if ((va[0] == v) || (va[1] == v)) {
				removeEdge(e);
			}
		}
	}

	public void removeEdge(Edge e) {
		// TODO Auto-generated method stub
		adjacentEdges.remove(e);
	}

	public Iterator<Vertex> adjacentVertices() {
		// TODO Auto-generated method stub
		return adjacentVerticesArray().iterator();
	}

	private ArrayList<Vertex> adjacentVerticesArray() {
		Iterator<Edge> itr = adjacentEdges.iterator();
		ArrayList<Vertex> va = new ArrayList<Vertex>();
		
		while (itr.hasNext()) {
			// TODO: Clean this up.
			Edge e = itr.next();
			Vertex[] ev = e.endVertices();
			if (((ev[0] == this) && (ev[1] != this)) ||
				((ev[1] == this) && (ev[0] != this))) {
				va.add(ev[0]);
			}
		}
		
		return va;
	}
 }