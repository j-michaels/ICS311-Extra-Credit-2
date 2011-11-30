package ics311;

import java.util.ArrayList;
import java.util.Iterator;

public class Path {
	public String id;
	public boolean valid;
	public ArrayList<Vertex> sequence;
	public Path(ArrayList<Vertex> sequence) {
		this.sequence = sequence;
		Vertex origin = sequence.get(0);
		Vertex destination = sequence.get(sequence.size()-1);
		if (origin != destination) {
			this.id = "";
			valid = true;
		} else {
			this.id="";
			valid = false;
			
		}
	}
	
}
