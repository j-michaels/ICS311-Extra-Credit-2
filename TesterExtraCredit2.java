package ics311;

import java.util.ArrayList;
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
		
		// Dijstra's Algorithm
		Iterator<Vertex> itr = g.vertices();
		g.dijkstra(g.vertices.get(0));
		while (itr.hasNext()) {
			Vertex v = itr.next();
			//System.out.println("Running Dijstra's Algorithm on vertex " +v.id());
			PathResult result = g.dijkstra(v);
			System.out.println();
		}
		//System.out.println("Finished.");
		
		// Iterated Bellman-Ford 
        /*
		Graph bf = new Graph();
		
		bf.readFromFile("bellmanfordinput");
		long beginTime = System.nanoTime();
		//itr = g.vertices();
		Iterator<Vertex> bf_itr = bf.vertices();
		while (bf_itr.hasNext()) {
			Vertex v = bf_itr.next();
			boolean neg = bf.bellmanford(v);
			if (!neg) {
				System.out.println("Error: Negative cycle from "+v.id());
			}
		}
		System.out.println("Iterated Bellman-Ford took: " + (System.nanoTime() - beginTime));
		*/
		// Johnson's Algorithm
		
	}
	
	public static void printPathResults(ArrayList<PathResult> results) {
		Iterator<PathResult> res_itr = results.iterator();
		ArrayList<String> pres = new ArrayList<String>();
		boolean success = true;
		while (res_itr.hasNext()) {
			PathResult result = res_itr.next();
			if (!result.success) {
				success = false;
				break;
			} else {
				pres.add(result.print());
			}
		}
		if (!success) {
			System.out.println("Error: Path contains a negative cycle.");
		} else {
			Iterator<String> pres_itr = pres.iterator();
			while (pres_itr.hasNext()) {
				System.out.println(pres_itr.next());
			}
		}
	}
}