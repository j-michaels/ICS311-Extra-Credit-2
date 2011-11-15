package ics311;

import java.util.ArrayList;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		g.readFromFile();
		//g.printEdges();
		//g.dfsPrep(null);
		ArrayList<ArrayList<Vertex>> sccarr = g.scc();
		g.printVertices();
	}

}
