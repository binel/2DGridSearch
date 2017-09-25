import java.util.LinkedList;

public class BreadthFirstTreeSearch {
	private static int expandedNodes = 0; 
	
	public static boolean bfts(Grid g, Coord goal) {
		LinkedList<Node> fringe = new LinkedList<Node>(); 
		
		fringe.add(g.getRoot());
		
		Node current; 
		while(true) {
			g.solutionDelay();
			
			if(fringe.isEmpty()) {
				return false; 
			}
			current = fringe.pop(); 
			current.isActive = true; 
			
			if(current.c.equals(goal)) {
				current.isGoal = true;
				g.solutionDelay();
				return true; 
			}
			
			g.expandNode(current);
			expandedNodes++; 
			current.isExpanded = true;
			
			for(Node n : current.getNeighbors()) {
				fringe.add(n);
			}
			
			g.solutionDelay();
			current.isActive = false; 
		}
	}
}
