import java.util.ArrayList;

public class Node {
	public Node n_neigh = null; 
	public Node s_neigh = null; 
	public Node w_neigh = null; 
	public Node e_neigh = null; 
	
	public boolean isExpanded = false; 
	public boolean isGoal = false; 
	public boolean isActive = false; 
	
	public Coord c; 
	
	public Node(Coord c) {
		this.c = c; 
	}
	
	public ArrayList<Node> getNeighbors() {
		ArrayList<Node> neigh = new ArrayList<Node>(); 
		neigh.add(n_neigh);
		neigh.add(s_neigh); 
		neigh.add(w_neigh);
		neigh.add(e_neigh);
		return neigh; 
	}
}
