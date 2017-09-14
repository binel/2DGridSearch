import java.util.HashSet;
import java.util.LinkedList;

public class BreadthFirstGraphSearch {
	private static int expandedNodes = 0; 
	
	public static boolean bfts(Grid g, Coord goal) {
		LinkedList<Node> fringe = new LinkedList<Node>(); 
		HashSet<Node> closed = new HashSet<Node>();
		
		fringe.add(g.getRoot());
		
		Node current; 
		while(true) {
			if(fringe.isEmpty()) {
				return false; 
			}
			
			current = fringe.pop(); 
			closed.add(current);
			
			g.expandNode(current);
			expandedNodes++; 
			for(Node n : current.getNeighbors()) {
				if(n.c.equals(goal)) {
					return true; 
				}
				
				if(!closed.contains(n)) {
					fringe.add(n);
				}
			}
			
		}
	}
	
	public static void main(String args[]) {
		Grid g = new Grid(); 
		for(int i = 0; i < 20; i++) {
			expandedNodes = 0; 
			bfts(g, new Coord(i, i));
			System.out.println("Goal = (" + i + "," + i + ") Nodes Expanded = " + expandedNodes);
		}
	}
}
