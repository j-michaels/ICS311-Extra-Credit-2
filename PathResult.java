package ics311;

import java.util.ArrayList;
import java.util.Iterator;

public class PathResult {
	public String sourceId;
	public boolean success;
	
	public ArrayList<Integer> pathLengths;
	public ArrayList<String> pathNames;
	public ArrayList<Vertex> vertices;
	
	public PathResult(String sourceId, boolean success, ArrayList<ArrayList<Vertex>> sequences) {
		this.sourceId = sourceId;
		this.success = success;
		this.vertices = vertices;
	}
	public String print() {
		String pres = "";
		Iterator<Vertex> itr = vertices.iterator();
		int i = 0;
		int avg = 0;
		String minName = "";
		boolean noMinYet = true;
		float min = 0;
		boolean noMaxYet = true;
		String maxName = "";
		float max = 0;
		
		while (itr.hasNext()) {
			Vertex v = itr.next();
			float length = v.getDist();
			String name = v.getName();
			avg += length;
			if ((noMinYet) || (length < min)) {
				noMinYet = false;
				min = length;
				minName = name;
			}
			if ((noMaxYet) || (length > max)) {
				noMaxYet = false;
				max = length;
				maxName = name;
			}
			i++;
		}
		pres = sourceId + "		" + minName + ":" + min + "	" + (avg/pathNames.size()) + "	" + maxName + ":"+max;
		return pres;
	}
	
	public void constructPaths() {
		
	}
}
