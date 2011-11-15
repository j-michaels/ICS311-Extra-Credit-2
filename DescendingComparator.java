package ics311;

import java.util.ArrayList;
import java.util.Comparator;

public class DescendingComparator implements Comparator<Vertex> {

	@Override
	//Compare based on the vertices degrees
	public int compare(Vertex v1, Vertex v2) {
		if (v1.degree() > v2.degree()) return -1;
	      else if (v1.degree() < v2.degree()) return 1;
	      return 0; /*equal*/
	}
	
}

class DoubleDescendingComparator implements Comparator<ArrayList<Vertex>> {

	@Override
	//Compare based on the vertices degrees
	public int compare(ArrayList<Vertex> va1, ArrayList<Vertex> va2) {
		if (va1.get(0).degree() > va2.get(0).degree()) return -1;
	      else if (va1.get(0).degree() < va2.get(0).degree()) return 1;
	      return 0; /*equal*/
	}
	
}