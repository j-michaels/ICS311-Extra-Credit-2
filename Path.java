package ics311;

import java.util.ArrayList;
import java.util.Iterator;

public class Path {
	public int firstId;
	public String id;
	public boolean valid;
	public ArrayList<Vertex> sequence;
	public float len;
	public Path(ArrayList<Vertex> sequence) {
		this.sequence = sequence;
		if (sequence.size() > 0) {
			Vertex origin = sequence.get(0);
			Vertex destination = sequence.get(sequence.size()-1);
			if (origin != destination) {
				this.firstId = origin.id();
				this.id = origin.getName()+":"+destination.getName();
				// Length is stored because none of the vertices are clones
				// so if another run of dijkstra's is performed, the length values
				// will change, making the output of print() incorrect
				this.len = destination.getDist();
				valid = true;
			} else {
				System.out.print("Invalid seq:");
				this.print();
				this.id="";
				valid = false;
			}
		} else {
			System.out.println("Invalid: 0 length sequence");
			valid = false;
		}
	}
	
	public void print() {
		Iterator<Vertex> itr = sequence.iterator();
		
		while (itr.hasNext()) {
			Vertex v = itr.next();
			if (itr.hasNext()) {
				System.out.print(v.getName()+"->");
			} else {
				System.out.println(v.getName() + " ("+len+")");
				break;
			}
		}
		
	}
	
}
