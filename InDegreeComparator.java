package ics311;

import java.util.Comparator;

public class InDegreeComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex v1, Vertex v2) {
		if (v1.inDegree() < v2.inDegree()) return -1;
	      else if (v1.inDegree() > v2.inDegree()) return 1;
	      return 0; /*equal*/
	}

}
