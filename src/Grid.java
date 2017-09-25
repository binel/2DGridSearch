import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

//Simulates a boundless 2D grid with the root at (0,0) 
public class Grid {
	private HashMap<Coord, Node> grid;
	private Node root; 
	
	public Grid() {
		this.grid = new HashMap<Coord, Node>();
		this.root = new Node(new Coord(0,0));
		this.grid.put(this.root.c, this.root);
	}
	
	public Node getRoot() {
		return this.root; 
	}
	
	public HashMap<Coord, Node> getGrid() {
		return this.grid; 
	}
	
	public void addNode(Node n) {
		if(this.grid.containsKey(n.c)) {
			return;
		}
		if(this.grid.containsKey(new Coord(n.c.x + 1, n.c.y))) {
			Node neigh = this.grid.get(new Coord(n.c.x + 1, n.c.y));
			neigh.w_neigh = n; 
			n.e_neigh = neigh; 
		}
		if(this.grid.containsKey(new Coord(n.c.x - 1, n.c.y))) {
			Node neigh = this.grid.get(new Coord(n.c.x - 1, n.c.y));
			neigh.e_neigh = n; 
			n.w_neigh = neigh; 
			
		}
		if(this.grid.containsKey(new Coord(n.c.x, n.c.y + 1))) {
			Node neigh = this.grid.get(new Coord(n.c.x, n.c.y + 1));
			neigh.s_neigh = n; 
			n.n_neigh = neigh; 
		}
		if(this.grid.containsKey(new Coord(n.c.x, n.c.y - 1))) {
			Node neigh = this.grid.get(new Coord(n.c.x, n.c.y - 1));
			neigh.n_neigh = n; 
			n.s_neigh = neigh; 
		}
		grid.put(n.c, n);
	}
	
	public void expandNode(Node n) {
		this.addNode(new Node(new Coord(n.c.x + 1, n.c.y)));
		this.addNode(new Node(new Coord(n.c.x - 1, n.c.y)));
		this.addNode(new Node(new Coord(n.c.x, n.c.y + 1)));
		this.addNode(new Node(new Coord(n.c.x, n.c.y - 1)));
	}
}
