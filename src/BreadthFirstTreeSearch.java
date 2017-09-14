import java.util.LinkedList;

public class BreadthFirstTreeSearch {
	private static int expandedNodes = 0; 
	
	public static boolean bfts(Grid g, Coord goal) {
		LinkedList<Node> fringe = new LinkedList<Node>(); 
		
		fringe.add(g.getRoot());
		
		Node current; 
		while(true) {
			if(fringe.isEmpty()) {
				return false; 
			}
			
			current = fringe.pop(); 
			if(current.c.equals(goal)) {
				return true; 
			}
			
			g.expandNode(current);
			expandedNodes++; 
			for(Node n : current.getNeighbors()) {
				fringe.add(n);
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
