import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Grid g = new Grid(); 
		GridDisplay gd = new GridDisplay(g);
		g.registerDisplay(gd);
		
		JFrame frame = new JFrame("2D Grid Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gd);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		BreadthFirstGraphSearch.search(g, new Coord(-1,-1));
		
	}
}
