package ics311;

import java.util.Comparator;

public class AscendingPathComparator implements Comparator<Path> {

	@Override
	//Compare based on the vertices degrees
	public int compare(Path p1, Path p2) {
		if (p1.len < p2.len) return -1;
		else if (p1.len > p2.len) return 1;
		else { // They're even, so go off the id of the first vertex in the sequence
			if (p1.firstId < p2.firstId) return -1;
			else if (p1.firstId > p2.firstId) return 1;
			return 0;
		}
	}
	
}