package ics311;

import java.util.Comparator;

public class DescendingPathComparator implements Comparator<Path> {

	@Override
	//Compare based on the vertices degrees
	public int compare(Path p1, Path p2) {
		//if (v1.degree() > v2.degree()) return -1;
	    //  else if (v1.degree() < v2.degree()) return 1;
		if (p1.len > p2.len) return -1;
		else if (p1.len < p2.len) return 1;
		else {
			if (p1.firstId < p2.firstId) return -1;
			else if (p1.firstId > p2.firstId) return 1;
			return 0;
			
		}
	    
	}
	
}