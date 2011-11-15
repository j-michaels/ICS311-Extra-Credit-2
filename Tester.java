package ics311;

public class Tester {

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
		g.calcMinsMaxAvg();
		g.scc();
		//g.printVertices();
		g.finalPrint();
	}

}
