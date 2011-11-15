package ics311;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class Graph {
	boolean[][] AdjacencyMatrix;
	ArrayList<Edge> edges;
	ArrayList<Vertex> vertices;
	ArrayList<Vertex> dfsArray;
	int time;
	private ArrayList<ArrayList<Vertex>> sccArray;
	private String fileName;
	private int minInDegree;
	private int minOutDegree;
	private double avgInDegree;
	private double avgOutDegree;
	private int maxInDegree;
	private int maxOutDegree;
	
	public Graph() {
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<Vertex>();
		time = 0;
		fileName = "";
	}
	
	public void readFromFile(String fileName) {
		this.fileName = fileName;  
		FileReader reader;
		
		try {
			reader = new FileReader("inputfile");
			BufferedReader br = new BufferedReader(reader);
			String nextLine;
			String mode = "";
			int i = 0;
			while ((nextLine = br.readLine()) != null) {
				String[] lines = nextLine.split(" ");
				i++;
				if (lines[0].equals("*Vertices")) {
					mode = "vertices";
				} else if (lines[0].equals("*Arcs")) {
					mode = "arcs";
				} else {
					if (mode.equals("vertices")) {
						Vertex v = new Vertex(lines[0]);
						v.setid(i-1);
						vertices.add(v);
					} else if (mode.equals("arcs")) {
						//System.out.print("Line: ");
						//System.out.println(nextLine);
						
						Vertex v1 = vertices.get(Integer.parseInt(lines[0])-1);
						Vertex v2 = vertices.get(Integer.parseInt(lines[1])-1);
						//System.out.println("v1: "+ v1.id() + ", v2: " + v2.id());
						boolean d = false;
						if (Float.parseFloat(lines[2]) == 1.0) {
							d = true;
						}
						
						Edge e = new Edge(v1, v2, null, d);
						edges.add(e);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
		return v.incidentEdges();
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
		Iterator<Edge> itr = v1.incidentEdges();
		
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
	
	public void dfsPrep(Stack<Vertex> stack) {
		dfsArray = new ArrayList<Vertex>();
		sccArray = new ArrayList<ArrayList<Vertex>>();
		
		Iterator<Vertex> itr = vertices.iterator();
		while (itr.hasNext()) {
			Vertex u = itr.next();
			u.setAnnotation("color", "white");
			u.setAnnotation("root", false);
			u.setAnnotation("pi", null);
		}
		time = 0;
		
		if (stack == null) {
			itr = vertices.iterator();
			while (itr.hasNext()) {
				Vertex u = itr.next();
				if (u.getAnnotation("color").equals("white")) {
					dfsArray.addAll(dfs(u));
				}
			}
		} else {
			// traverse the stack in reverse order
			while (stack.isEmpty() != true) {
				
				//System.out.print("Popping off ");
				Vertex u = stack.pop();
				u.setAnnotation("root", true);
				
				//System.out.println(u.id());
				ArrayList<Vertex> allVerts = dfs(u);
				Iterator<Vertex> it = allVerts.iterator();
				
				//System.out.print("DFS on that returns: ");
				while (it.hasNext()) {
					System.out.print(it.next().id() + " ");
				}
				DescendingComparator compr = new DescendingComparator();
				Collections.sort(allVerts, compr);
				//System.out.println();
				sccArray.add(allVerts);
				// remove all the vertices discovered by DFS
				stack.removeAll(allVerts);
			}
		}
	}
	
	public void printVertices(ArrayList<Vertex> av) {
		Iterator<Vertex> itr = av.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().id() + " ");
			
		}
		System.out.println();
	}	

	
	public ArrayList<Vertex> dfs(Vertex u) {
		ArrayList<Vertex> allVerts = new ArrayList<Vertex>();
		time = time + 1;
		u.setAnnotation("d", time);
		u.setAnnotation("color", "gray");
		System.out.println("Running dfs on " + u.id() + ", out degree: " + u.outDegree());
		//System.out.print("outbound vertices for " + u.id() + ": ");
		//printVertices(u.outAdjacentVerticesArray());
		
		Iterator<Vertex> itr = u.outAdjacentVertices();
		while (itr.hasNext()) {
			
			
			Vertex v = itr.next();
			
			//System.out.println("Foo: " + v.id() + "; color: " + v.getAnnotation("color"));
			if (v.getAnnotation("color").equals("white")) {
				//System.out.println("Setting "+u.id()+ "'s child " + v.id() + " to have pi=u.id");
				v.setAnnotation("pi", u);
				allVerts.addAll(dfs(v));
			}
		}
		u.setAnnotation("color", "black");
		time = time + 1;
		u.setAnnotation("finish", time);
		allVerts.add(u);
		
		return allVerts;
	}
	
	public ArrayList<ArrayList<Vertex>> scc() {
		// Run first pass of DFS
		System.out.println("First pass of DFS.");
		dfsPrep(null);
		
		// Make a stack based on the reverse output of the DFS algorithm.
		System.out.print("dfsArray output: ");
		Stack<Vertex> s = new Stack<Vertex>();
		Iterator<Vertex> itr = dfsArray.iterator();
		while (itr.hasNext()) {
			Vertex v = itr.next();
			System.out.print(v.id() + " ");
			s.add(v);
		}
		System.out.println();
		
		// Reverse all the edges in the graph.
		System.out.println("Reversing all the edges in the graph.");
		Iterator<Edge> itr2 = edges.iterator();
		while (itr2.hasNext()) {
			Edge e = itr2.next();
			e.reverse();
		}
		
		// Run DFS again with the stack to follow as a guide.
		System.out.println("Second pass of DFS.");
		dfsPrep(s);
		
		return sccArray;
	}

	public void printEdges() {
		// TODO Auto-generated method stub
		Iterator<Edge> itr=edges.iterator();
		while (itr.hasNext()) {
			Edge e = itr.next();
			Vertex[] va = e.endVertices();
			System.out.println("Edge: " + va[0].id() + ", " + va[1].id());
		}
	}

	// print for debugging
	public void printVertices() {
		// TODO Auto-generated method stub
		System.out.println("Time: "+time);
		Iterator<Vertex> itr = vertices.iterator();
		while (itr.hasNext()) {
			Vertex v = itr.next();
			Vertex pi = (Vertex)v.getAnnotation("pi");
			int pi_id = 0;
			if (pi != null) {
				pi_id = pi.id();
			}
			System.out.println("Vertex " + v.id() + ": color: " + "; time: " + v.getAnnotation("d") + "; finish: " + v.getAnnotation("finish") + "; id: " + v.getAnnotation("color") + "; pi: "+ pi_id);
		}
		Iterator<ArrayList<Vertex>> itr2 = sccArray.iterator();
		while (itr2.hasNext()) {
			ArrayList<Vertex> va = itr2.next(); // this represents a single connected comp.
			// sort it!
			DescendingComparator compr = new DescendingComparator();
			Collections.sort(va, compr);
			Iterator<Vertex> itr3 = va.iterator();
			System.out.print("{");
			while (itr3.hasNext()) {
				Vertex x = itr3.next();
				System.out.print(x.id() + ":" + x.degree() + " ");
			}
			System.out.print("}, ");
		}
		System.out.println(" fin.");
		
		
	}
	
	public void finalPrint() {
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Graph " + fileName);
		System.out.println("------------------------------------------------------------");
		System.out.println("|V| = " + vertices.size());
		System.out.println("|E| = " + edges.size());
		System.out.println("Degree distribution:	minimum		average		max");
		System.out.println("inDegree(v)		" + minInDegree + "		" + avgInDegree + "		" + maxInDegree);
		System.out.println("outDegree(v)		" + minOutDegree + "		" + avgOutDegree + "		" + maxOutDegree);
		
		System.out.println("Number of Strongly Connected Components: " + sccArray.size());
		System.out.println("Top 20 SCC by size:");
		System.out.println("ID	Root	Highest Degree Vertices");
		DoubleDescendingComparator compr = new DoubleDescendingComparator();
		Collections.sort(sccArray, compr);
		Iterator<ArrayList<Vertex>> itr = sccArray.iterator();
		for (int i = 0; itr.hasNext() && i <= 20; i++) {
			ArrayList<Vertex> av = itr.next();
			Vertex v = av.get(0);
			Vertex r = findRoot(av);
			String rid = "null";
			if (r != null) {
				rid = String.valueOf(r.id());
			}
			System.out.println(i + "	" + rid + "		deg(" + v.id() + ") = " + v.degree());
		}
		
	}
	
	private Vertex findRoot(ArrayList<Vertex> av) {
		// TODO Auto-generated method stub
		Iterator<Vertex> itr = av.iterator();
		while (itr.hasNext()) {
			Vertex v = itr.next();
			if (v.getAnnotation("root").equals(true)) return v;
		}
		return null;
	}

	// arraylist doesn't have these basic functions built in + no anonymous functions
	// = unhappy programmers
	public void calcMinsMaxAvg() {
		// TODO Auto-generated method stub
		Iterator<Vertex> itr = vertices.iterator();
		int minIn = -1;
		int minOut = -1;
		int maxIn = 0;
		int maxOut = 0;
		int sumIn = 0;
		int sumOut = 0;
		
		while (itr.hasNext()) {
			Vertex v = itr.next();
			
			if ((v.inDegree() < minIn) || (minIn == -1)) {
				minIn = v.inDegree();
			}
			if ((v.outDegree() < minOut) || (minOut == -1)) {
				minOut = v.outDegree();
			}
			if (v.inDegree() > maxIn) {
				maxIn = v.inDegree();
			}
			if (v.outDegree() > maxOut) {
				maxOut = v.outDegree();
			}
			sumIn += v.inDegree();
			sumOut += v.outDegree();
		}
		
		minInDegree = minIn;
		minOutDegree = minOut;
		maxInDegree = maxIn;
		maxOutDegree = maxOut;
		avgInDegree = (double)sumIn / vertices.size();
		avgOutDegree = (double)sumOut / vertices.size();
	}
}