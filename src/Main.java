import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Grid g = new Grid(); 
		g.expandNode(g.getRoot());
		g.expandNode(g.getRoot().n_neigh);
		g.expandNode(g.getRoot().w_neigh);
		
		GridDisplay gd = new GridDisplay(g);
		
		JFrame frame = new JFrame("2D Grid Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gd);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
}
