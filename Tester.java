package ics311;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		g.readFromFile();
		//g.printEdges();
		g.dfsPrep(g.vertices.get(0));
		g.printVertices();
	}

}
