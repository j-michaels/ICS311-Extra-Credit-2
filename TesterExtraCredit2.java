package ics311;

import java.util.Iterator;

public class TesterExtraCredit2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Tester filename");
            System.out.println("Where filename is the data file to be read.");
            System.exit(1);
        }
		Graph g = new Graph();
		g.readFromFile(args[0]);
		//g.calcMinsMaxAvg();
		//g.scc();
		//g.printVertices();
		//g.finalPrint();
		
		// Dijstra's Algorithm
		Iterator<Vertex> itr = g.vertices();
		//g.dijkstra(g.vertices.get(0));
		while (itr.hasNext()) {
			Vertex v = itr.next();
			System.out.println("Running Dijstra's Algorithm on vertex" +v.id());
			g.dijkstra(v);
			System.out.println();
		}
		
		// Iterated Bellman-Ford
		
		
		// Johnson's Algorithm
	}

}
