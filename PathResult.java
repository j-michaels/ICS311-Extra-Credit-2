package ics311;

import java.util.ArrayList;
import java.util.Iterator;

public class PathResult {
	public String sourceId;
	public boolean success;
	
	public ArrayList<Integer> pathLengths;
	public ArrayList<String> pathNames;
	public ArrayList<ArrayList<Path>> results;
	
	public PathResult() {
		results = new ArrayList<ArrayList<Path>>();
	}
	
	public void addResult(ArrayList<Path> result) {
		results.add(result);
	}
	
	public void print() {
		
	}
}
