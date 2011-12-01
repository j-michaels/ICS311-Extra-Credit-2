package ics311;

import java.util.ArrayList;
import java.util.Collections;
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
		System.out.println("Beginning iterated Dijkstra on data from file '"+args[0] +"'");
		Iterator<Vertex> itr = g.vertices();
		ArrayList<Path> results = new ArrayList<Path>();
		long beginTime = System.nanoTime();
		while (itr.hasNext()) {
			Vertex v = itr.next();
			//System.out.println("Running Dijstra's Algorithm on vertex " +v.id());
			g.dijkstra(v);
			//System.out.println("Total valid paths discovered: "+pathsArr.size());
			results.addAll(g.constructPaths());
		}
		System.out.println("Iterated Dijkstra took: " + (System.nanoTime() - beginTime) + "ns");
		printPaths(results);
		System.out.println();
		
		if (args.length > 1) {
			// Iterated Bellman-Ford
			System.out.println("Beginning iterated Bellman-Ford on data from file '" + args[1]+"'");
			Graph bf = new Graph();
			results = new ArrayList<Path>();
			bf.readFromFile(args[1]);
			beginTime = System.nanoTime();
			Iterator<Vertex> bf_itr = bf.vertices();
			while (bf_itr.hasNext()) {
				Vertex v = bf_itr.next();
				boolean neg = bf.bellmanford(v);
				if (!neg) {
					System.out.println("Terminating due to a negative cycle.");
					System.exit(1);
				}
				results.addAll(bf.constructPaths());
			}
			System.out.println("Iterated Bellman-Ford took: " + (System.nanoTime() - beginTime) + "ns");
			printPaths(results);
		}
		// Johnson's Algorithm
		// no time to implement this
	}
	
	private static void printPaths(ArrayList<Path> allPaths) {
		Iterator<Path> path_itr = allPaths.iterator();
		int avg = 0;
		while (path_itr.hasNext()) {
			Path p = path_itr.next();
			float length = p.len;
			avg += length;
		}
		System.out.println("Average path length: "+ (avg/allPaths.size()));
		
		AscendingPathComparator pathComparator = new AscendingPathComparator();
		Collections.sort(allPaths, pathComparator);
		System.out.println("Top 10 shortest path lengths:");
		for (int i = 0; (i<10) && (i<allPaths.size()); i++) {
			Path p = allPaths.get(i);
			p.print();
		}
		
		System.out.println("Top 10 longest path lengths:");
		for (int i=allPaths.size()-1; (i>0) && (i>allPaths.size()-11); i--) {
			Path p = allPaths.get(i);
			p.print();
		}
	}
}