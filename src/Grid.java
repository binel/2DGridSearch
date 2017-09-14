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
	
	public void displayGrid() {
		new Display(this); 
	}
	
	public class Display extends Frame {
		private Grid grid;
		private int shift = 250; 
		
		public Display(Grid grid) {
			this.grid = grid; 
			
			setSize(500, 500);
			setVisible(true); 
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose(); 
					System.exit(0);
				}
			});
		}
		
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			
			final int radius = 5;
			final int gridDist = 20; 
			
			for(Node n : grid.grid.values()) {
				g2.setPaint(Color.BLUE);
				g2.fillOval(shift + (n.c.x * gridDist) - radius, shift + (n.c.y * gridDist) - radius, radius * 2, radius*2);
				
				g2.setPaint(Color.BLACK);
				if(n.e_neigh != null) {
					g2.drawLine(shift + (n.c.x * gridDist), shift + (n.c.y * gridDist), shift + (n.e_neigh.c.x * gridDist), shift + (n.e_neigh.c.y * gridDist));
				}
				if(n.w_neigh != null) {
					g2.drawLine(shift + (n.c.x * gridDist), shift + (n.c.y * gridDist), shift + (n.w_neigh.c.x * gridDist), shift + (n.w_neigh.c.y * gridDist));
				}
				if(n.n_neigh != null) {
					g2.drawLine(shift + (n.c.x * gridDist), shift + (n.c.y * gridDist), shift + (n.n_neigh.c.x * gridDist), shift + (n.n_neigh.c.y * gridDist));
				}
				if(n.s_neigh != null) {
					g2.drawLine(shift + (n.c.x * gridDist), shift + (n.c.y * gridDist), shift + (n.s_neigh.c.x * gridDist), shift + (n.s_neigh.c.y * gridDist));
				}
			}
		}
	}
	
	public static void main(String args[]) {
		Grid g = new Grid(); 
		g.expandNode(g.root);
		g.expandNode(g.root.n_neigh);
		g.expandNode(g.root.w_neigh);
		g.displayGrid();
	}
}
