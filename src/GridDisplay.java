import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GridDisplay extends JPanel {
	private Grid grid; 
	public boolean nextStepReady = false; 
	
	public GridDisplay(Grid g) {
		super(); 
		grid = g; 
		Dimension size = new Dimension(500, 500);
		setMinimumSize(size);
		setPreferredSize(size);
		repaint(); 
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		
		
		final int radius = 5;
		final int gridDist = 20; 
		final int shift = 250; 
		
		for(Node n : grid.getGrid().values()) {
			if(n.isGoal) {
				g2.setPaint(Color.GREEN);
			} else if(n.isActive){
				g2.setPaint(Color.YELLOW);
			} else if(n.isExpanded) {
				g2.setPaint(Color.RED);
			} else {
				g2.setPaint(Color.BLUE);
			}
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
		nextStepReady = true; 
	}
	
	
	public void step() {
		nextStepReady = false; 
		repaint(); 
	}
}
