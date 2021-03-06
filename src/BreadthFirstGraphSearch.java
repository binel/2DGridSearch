import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class BreadthFirstGraphSearch {
	private static int expandedNodes = 0; 
	
	public static boolean search(Grid g, Coord goal) {
		LinkedList<Node> fringe = new LinkedList<Node>(); 
		HashSet<Node> closed = new HashSet<Node>();
		HashMap<Node, Node> parent = new HashMap<Node, Node>(); 
		
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
			current.isExpanded = true; 
			for(Node n : current.getNeighbors()) {
				n.isActive = true; 
				g.solutionDelay();
				
				if(n.c.equals(goal)) {
					parent.put(n, current);
					n.isGoal = true; 
					while(parent.containsKey(n)) {
						n = parent.get(n);
						n.isGoal = true; 
					}
					g.solutionDelay();
					return true; 
				}
				
				if(!closed.contains(n) && !fringe.contains(n)) {
					parent.put(n, current);
					fringe.add(n);
				}
				n.isActive = false; 
			}
			
		}
	}
}
